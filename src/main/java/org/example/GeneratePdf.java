package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.jsoup.Jsoup;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GeneratePdf {
    public static void create(PDDocument document, PDFont font, float fontSize, String fullText,String title)throws Exception{
        String path= Path.PATH;

        PDPage p=new PDPage();
        p.setTrimBox(PDRectangle.A4);
        PDRectangle trimBox=p.getTrimBox();
        float margin=72;
        float width= trimBox.getWidth()-2*margin;
        float startX=trimBox.getLowerLeftX()+margin;
        float startY=trimBox.getUpperRightY()-margin;
        float interval=1.5f*fontSize;
        float rows=(trimBox.getHeight()-2*margin)/(interval+font.getAverageFontWidth()/1000);

        fullText=fullText.replace("</p>","^");
        String textOnly = Jsoup.parse(fullText).text();
        List<String> lines=new ArrayList<String>();
        String line="    ";
        for(int i=0;i<textOnly.length();i++){
            char character=textOnly.charAt(i);
            if(fontSize*font.getStringWidth(line+character)/1000>width){
                lines.add(line);
                line="";
            }
            else if(character=='^'){
                lines.add(line);
                line="    ";
                continue;
            }
            line+=character;
        }
        if(!line.equals(""))lines.add(line);

        title=title.trim();
        PDPageContentStream contentStream=null;
        PDPage page=new PDPage();
        document.addPage(page);
        contentStream=new PDPageContentStream(document,page);
        contentStream.setFont(font,fontSize);
        contentStream.beginText();
        contentStream.newLineAtOffset((width-font.getStringWidth(title)/1000*fontSize)/2+margin,startY);
        contentStream.showText(title);
        contentStream.newLineAtOffset(-(width-font.getStringWidth(title)/1000*fontSize)/2,-interval);

        int index=0;
        while(index<lines.size()){
            if(index>0) {
                page = new PDPage();
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                contentStream.setFont(font, fontSize);
                contentStream.beginText();
                contentStream.newLineAtOffset(startX,startY);
            }
            for(int i=(index==0?1:0);i<rows;i++){
                if(index>=lines.size())break;
                if(lines.get(index).trim().length()==0){
                    index++;continue;
                }
                contentStream.showText(lines.get(index++));
                contentStream.newLineAtOffset(0,-interval);
            }
            contentStream.endText();
            contentStream.close();
        }
        document.save(path+"data/Result.pdf");
        document.close();
    }

}
