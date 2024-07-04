package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

public class GeneratePdf {
    public static GeneratePdf generatePdf;


    private float margin,interval,width,startX,startY;

    public PDPage createPage(){
        PDPage page=new PDPage();
        PDRectangle trimBox=page.getTrimBox();
        margin=72;
        width= trimBox.getWidth()-2*margin;
        startX=trimBox.getLowerLeftX()+margin;
        startY=trimBox.getUpperRightY()-margin;
        return page;
    }

    public void create(PDDocument document,PDFont font,float fontSize,String fullText)throws Exception{
        String path=Datas.PATH;

        interval=1.5f*fontSize;

        PDPage page=createPage();
        document.addPage(page);

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

        PDPageContentStream contentStream=new PDPageContentStream(document,page);
        contentStream.setFont(font,fontSize);
        contentStream.beginText();
        contentStream.newLineAtOffset(startX,startY);
        for(String text:lines){
            if(text.trim().length()==0)continue;
            contentStream.showText(text);
            contentStream.newLineAtOffset(0,-interval);
        }
        contentStream.endText();
        contentStream.close();
        document.save(path+"data/Result.pdf");
        document.close();
    }

}
