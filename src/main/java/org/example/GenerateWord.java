package org.example;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.Jsoup;
import us.codecraft.webmagic.selector.Html;

import java.io.FileOutputStream;
import java.util.List;

public class GenerateWord {
    public static GenerateWord generateWord=new GenerateWord();

    public void create(int fontSize,String fontName,Html html) {
        String path= Path.PATH;

        List<String> wordText=html.xpath("//div[@class='wp_articlecontent']/p").all();
        String title=html.xpath("//h1[@class='arti_title']/text()").toString();
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setText(title);
        run.setFontSize(fontSize);
        run.setFontFamily(fontName);
        for (String text:wordText) {
            text=text.replace("</p>","").replace("\u00A0","");
            String textOnly = Jsoup.parse(text).text().trim();
            if(textOnly.length()==0)continue;;
            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.setText("\t"+textOnly);
            run.setFontSize(fontSize);
            run.setFontFamily(fontName);
        }
        FileOutputStream out= null;
        try {
            out = new FileOutputStream(path+"data/Result.docx");
            document.write(out);
            document.close();
        } catch (Exception e) {}
    }
}
