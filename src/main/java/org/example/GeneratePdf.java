package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.jsoup.Jsoup;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GeneratePdf {

    public static float margin,width,startX,startY,interval,averageFontWidth,rows;

    //初始化页面布局相关参数
    public static void setFormat(float fontSize,PDFont font){
        PDPage p=new PDPage();
        p.setTrimBox(PDRectangle.A4);
        PDRectangle trimBox=p.getTrimBox(); //TrimBox
        margin=72;  //边缘宽度定位72
        width= trimBox.getWidth()-2*margin; //可书写宽度
        startX=trimBox.getLowerLeftX()+margin;  //X坐标偏移起点
        startY=trimBox.getUpperRightY()-margin; //Y坐标偏移起点
        interval=1.5f*fontSize; //行距（设为1.5倍行距）
    }

    //预处理pdf每一行的内容（实现自动换行），此外若存在图片资源则保存其URL地址
    public static List<String> process(String passage,float fontSize,PDFont font) throws Exception {
        String[] fullText=passage.split("</p>");    //对于页面中许多<p></p>组成的字符串按</p>分割
        List<String> lines=new ArrayList<String>();
        for(String str:fullText){
            if(str.trim().length()==0)continue; //舍弃空行
            //若是图片资源
            if(str.contains("img")){
                int index=str.indexOf("src")+5; //定位URL
                StringBuilder img= new StringBuilder("<img>https://www.hhu.edu.cn");
                while(str.charAt(index)!='\"'){ //遍历字符，读入URL
                    img.append(str.charAt(index));
                    index++;
                }
                lines.add(img.toString());  //存储URL
            }
            //若是文本资源
            else {
                str= Jsoup.parse(str).text();   //去除html标记
                String line = "    ";   //段前缩进
                for (int i = 0; i < str.length(); i++) {
                    char character = str.charAt(i);
                    //若读入下一字符导致跃出到右侧margin，则应当换行
                    if (fontSize * font.getStringWidth(line + character) / 1000 > width) {
                        lines.add(line);
                        line = "";
                    }
                    line += character;
                }
                if (!line.equals("")) lines.add(line);
            }
        }
        return lines;
    }

    //PdfBox中只能插入本地图片，在线图片资源需先下载到本地
    public static String downloadImage(String url,String path) throws Exception {
        URLConnection conn = new URL(url).openConnection();
        String contentType = conn.getContentType();
        InputStream is = conn.getInputStream();
        //下载到 "[项目根路径]/data/" 下
        String fileName=path+"data/imgTemp";
        //处理文件后缀名
        switch (contentType) {
            case "image/jpeg":fileName += ".jpeg"; break;
            case "image/gif": fileName += ".gif"; break;
            case "image/webp":
            case "image/png": fileName += ".png"; break;
        }
        FileOutputStream fos = new FileOutputStream(fileName);
        byte[] data = new byte[1024];
        int len;
        while ((len = is.read(data)) != -1) {
            fos.write(data, 0, len);
        }
        fos.close();
        is.close();
        return fileName;
    }

    //生成pdf的核心方法
    public static void create(PDFont font, float fontSize, String passage,String title,String path)throws Exception{
        PDDocument document=new PDDocument();
        //（1）初始化页面布局
        setFormat(fontSize,font);
        //（2）自动换行预处理（并存储了图片资源的URL）
        List<String>lines=process(passage,fontSize,font);

        //（3）居中输出文章标题
        PDPage page=new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream=new PDPageContentStream(document,page);
        contentStream.setFont(font,fontSize);
        contentStream.beginText();
            //居中对齐输出
        contentStream.newLineAtOffset((width-font.getStringWidth(title)/1000*fontSize)/2+margin,startY);
        contentStream.showText(title);
            //偏移量取消居中，并移到下一行
        contentStream.newLineAtOffset(-(width-font.getStringWidth(title)/1000*fontSize)/2,-interval);

        //（4）接下来输出正文内容（文本+图片）
        int index=0;
            //由于第一页输出了标题，所以从第二行startY-interval开始
        float position=startY-interval;
            //通过维护当前输入位置position，进入到下margin时需要"自动换页"
        while(index<lines.size()){
            //index==0是标题所在页，该页已建立，不新建，index>0时才需要新建页
            if(index>0) {
                page = new PDPage();
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                contentStream.setFont(font, fontSize);
                contentStream.beginText();
                //非标题页的新建页从startX和startY开始
                contentStream.newLineAtOffset(startX,startY);
                position=startY;
            }
            //当前输入位置position>margin，本页还未结束，继续写本页
            while(position>margin){
                if (index >= lines.size()) break;   //若正文内容输出完，直接结束
                String text=lines.get(index);
                //当存储了图片URL时，处理图片输出
                if(text.startsWith("<img>")){
                    if(position-150<=margin)break;  //如果图片越出到margin，则退出本while，新建一页，从新页开始插入
                    PDImageXObject img=PDImageXObject.createFromFile(downloadImage(text.substring(5),path),document);
                    contentStream.endText();    //图片不能再text块中插入，需要先结束text块
                    contentStream.drawImage(img,margin+(width-230)/2,position- 150,230,150);    //图片居中对齐，大小定为230*150
                    contentStream.beginText();  //重新开始text块
                    position-=150+interval;  //更新position
                    contentStream.newLineAtOffset(startX, position);    //新text块从position偏移量开始
                    index++;
                }
                //当时文本信息，处理文本输出
                else {
                    contentStream.showText(text);   //输出文本
                    contentStream.newLineAtOffset(0, -interval);    //移到下一行
                    position-=interval; //更新position
                    index++;
                }
            }
            //关闭ContentStream
            contentStream.endText();
            contentStream.close();
        }
        //（5）暂存文件，在servlet中推送给用户
        document.save(path+"data/Result.pdf");
        document.close();
    }

}
