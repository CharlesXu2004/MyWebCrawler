package org.example;

import org.apache.pdfbox.io.IOUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.example.Datas;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import us.codecraft.webmagic.selector.Html;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CreateWordServlet extends HttpServlet {
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

        String ttf_path=path+"Fonts/"+request.getParameter("font");
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(ttf_path));
        } catch (FontFormatException e) {}
        String fontName = font.getFontName();
        int fontSize=Integer.parseInt(request.getParameter("fontSize"));

        List<String> wordText=html.xpath("//div[@class='wp_articlecontent']").all();
        XWPFDocument document = new XWPFDocument();
        for (String text:wordText) {
            text=text.replace("</p>","");
            StringBuilder builder=new StringBuilder(text);
            String textOnly = Jsoup.parse(builder.toString()).text();

            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(textOnly);
            run.setFontSize(fontSize);
            run.setFontFamily(fontName);
        }
        FileOutputStream out=new FileOutputStream(path+"data/Result.docx");
        document.write(out);
        document.close();
        response.setContentType("application/x-download");
        response.addHeader("Content-Disposition","attachment;filename=Result.docx");
        IOUtils.copy(new FileInputStream(path+"data/Result.docx"),response.getOutputStream());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        doPost(request,response);
    }
}
