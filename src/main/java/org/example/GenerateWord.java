package org.example;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import us.codecraft.webmagic.selector.Html;

import java.io.FileOutputStream;
import java.util.List;

public class GenerateWord {
    public static GenerateWord generateWord;

    public void create(int fontSize,String fontName,Html html) {
        String path=Datas.PATH;


        List<String> wordText=html.xpath("//div[@class='wp_articlecontent']/p").all();
        XWPFDocument document = new XWPFDocument();

        for (String text:wordText) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(text);
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
