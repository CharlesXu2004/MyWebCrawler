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
    public static GenerateWord generateWord=new GenerateWord();

    public void create(int fontSize,String fontName,Html html) {
        String path = Path.PATH;
        StringBuilder path_picture= new StringBuilder("https://www.hhu.edu.cn/");

        try {
            String title = html.xpath("//h1[@class='arti_title']/text()").toString();
            List<String> wordText=html.xpath("//div[@class='wp_articlecontent']/p").all();
            Document doc = Jsoup.parse(html.toString());
            XWPFDocument document = new XWPFDocument();
            XWPFParagraph paragraph_title = document.createParagraph();
            paragraph_title.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun run_title = paragraph_title.createRun();
            run_title.setText(title);
            run_title.setFontSize(fontSize);
            run_title.setFontFamily(fontName);

            for (String text:wordText) {
                System.out.println(text);
                text=text.replace("</p>","").replace("\u00A0","");
                int srcurl=text.indexOf("img");
                if(srcurl!=-1){
                    path_picture= new StringBuilder("https://www.hhu.edu.cn/");
                    System.out.println(srcurl);
                    int index=srcurl+28;
                    while(text.charAt(index)!='\"'){
                        path_picture.append(text.charAt(index));
                        index++;
                    }
                    addImageToDocument(document,path_picture.toString());
                    continue;
                }
                String textOnly = Jsoup.parse(text).text().trim();
                if(textOnly.length()==0)continue;
                XWPFParagraph paragraph = document.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.setText("\t"+textOnly);
                run.setFontSize(fontSize);
                run.setFontFamily(fontName);
            }

            // 获取网页body中的所有子节点
            //List<String> wordText=html.xpath("//div[@class='wp_articlecontent']/p").all();
            //String title=html.xpath("//h1[@class='arti_title']/text()").toString();
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

    private static void addImageToDocument(XWPFDocument document, String imgUrl)
    {
        try (InputStream is = new URL(imgUrl).openStream()) {
            XWPFParagraph paragraph = document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun run = paragraph.createRun();
            // 添加图片到Word文档中
            run.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, imgUrl, Units.toEMU(200), Units.toEMU(200));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
