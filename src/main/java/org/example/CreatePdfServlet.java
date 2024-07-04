package org.example;

import jakarta.servlet.annotation.WebServlet;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CreatePdfServlet")
public class CreatePdfServlet extends HttpServlet {
    @Override
    public void init()throws ServletException{
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String path= Path.PATH;
        PDDocument document=new PDDocument();
        PDFont font= PDType0Font.load(document, new File(path+"Fonts/"+request.getParameter("font")));
        float fontSize=Integer.parseInt(request.getParameter("fontSize"));
        PassageCrawlBean Bean=(PassageCrawlBean) request.getSession().getAttribute("Passage");
        try {
            GeneratePdf.create(document,font,fontSize,Bean.getText(),Bean.getTitle());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.setContentType("application/x-download");
        response.addHeader("Content-Disposition","attachment;filename=Result.pdf");
        IOUtils.copy(new FileInputStream(path+"data/Result.pdf"),response.getOutputStream());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        doPost(request,response);
    }
}
