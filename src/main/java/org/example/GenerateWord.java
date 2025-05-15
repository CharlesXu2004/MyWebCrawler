package org.example;
import org.apache.poi.xwpf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.apache.poi.util.Units;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.jsoup.Jsoup;
import us.codecraft.webmagic.selector.Html;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

public class GenerateWord {
    public static void create(int fontSize,String fontName,String title,String content,String path) {
        StringBuilder path_picture= new StringBuilder("https://www.hhu.edu.cn/");//图片路径的前半部分，与后半的路径拼起来为图片绝对路径

        try {
            String [] wordText=content.split("</p>");// 从HTML中提取内容段落
            XWPFDocument document = new XWPFDocument();// 创建一个新的XWPFDocument对象
            XWPFParagraph paragraph_title = document.createParagraph();// 创建标题段落
            paragraph_title.setAlignment(ParagraphAlignment.CENTER);// 设置段落居中对齐
            XWPFRun run_title = paragraph_title.createRun();// 创建一个新的运行（run）对象
            run_title.setText(title);// 设置运行对象的文本为标题
            run_title.setFontSize(fontSize);// 设置字体大小
            run_title.setFontFamily(fontName);// 设置字号

            for (String text:wordText) {
                text=text.replace("\u00A0","");// 移除</p>标签和不间断空格
                int srcurl=text.indexOf("img");// 查找图像标签的位置
                //表示该段落为图片
                if(srcurl!=-1){
                    path_picture= new StringBuilder("https://www.hhu.edu.cn/");// 重置图片路径的前半部分
                    System.out.println(srcurl);
                    int index=srcurl+28;// 获取图片相对路径的位置
                    while(text.charAt(index)!='\"'){
                        path_picture.append(text.charAt(index));//得到图片的绝对路径
                        index++;
                    }
                    // 添加图片到文档
                    addImageToDocument(document,path_picture.toString());
                    continue;
                }
                // 提取纯文本
                String textOnly = Jsoup.parse(text).text().trim();//Jsoup解析txet,得到html中的文本内容，并把两端多余空格去掉
                if(textOnly.length()==0)continue;//表示该文本为纯空格，可跳过
                XWPFParagraph paragraph = document.createParagraph();// 创建文本段落
                XWPFRun run = paragraph.createRun();// 创建一个新的运行（run）对象
                run.setText("\t"+textOnly);//设置每一段内容
                run.setFontSize(fontSize);// 设置字体大小
                run.setFontFamily(fontName);// 设置字号
            }

            // 将文档写入文件
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(path + "data/Result.docx");
                document.write(out);
                document.close();
            } catch (Exception e) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //添加图片到文件的方法
    private static void addImageToDocument(XWPFDocument document, String imgUrl) {
        try (InputStream is = new URL(imgUrl).openStream()) {
            XWPFParagraph paragraph = document.createParagraph();//创建图片段落
            paragraph.setAlignment(ParagraphAlignment.CENTER);//设置图片居中
            XWPFRun run = paragraph.createRun();// 创建一个新的运行（run）对象
            // 添加图片到Word文档中
            run.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, imgUrl, Units.toEMU(230), Units.toEMU(150));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
