package org.example;

import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1CFont;
import org.example.Datas;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import us.codecraft.webmagic.selector.Html;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class CreatePdfServlet extends HttpServlet {
    @Override
    public void init()throws ServletException{
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String path=Datas.PATH;
        Html html=Datas.html;
        PDDocument document = new PDDocument();
        PDFont font= PDType0Font.load(document, new File(path+"Fonts/"+request.getParameter("font")));
        int fontSize=Integer.parseInt(request.getParameter("fontSize"));

        document.addPage(new PDPage());
        PDPage pdfPage = document.getPage(0);                      //下标从0开始
        PDPageContentStream contentStream=new PDPageContentStream(document,pdfPage);

        contentStream.setFont(font,fontSize);
        contentStream.beginText();
        contentStream.newLineAtOffset(80,750);

        List<String> pdfText=html.xpath("//li[@class='menu-item i2']/ul/li").regex("(?<=<a.*>).*(?=</a>)").all();

        for(String text:pdfText){
            contentStream.showText(text);
            contentStream.newLineAtOffset(0,-20);
        }

        contentStream.endText();
        contentStream.close();
        document.save(path+"data/Result.pdf"); //保存
        document.close();
        response.setContentType("application/x-download");
        response.addHeader("Content-Disposition","attachment;filename=Result.pdf");
        IOUtils.copy(new FileInputStream(path+"data/Result.pdf"),response.getOutputStream());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        doPost(request,response);
    }
}
