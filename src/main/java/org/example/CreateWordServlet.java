package org.example;

import jakarta.servlet.annotation.WebServlet;
import org.apache.pdfbox.io.IOUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/CreateWordServlet")
public class CreateWordServlet extends HttpServlet {
    @Override
    public void init()throws ServletException{
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path= Path.PATH;

        PassageCrawlBean Bean=(PassageCrawlBean)request.getSession().getAttribute("Passage");
        String fontName = request.getParameter("font");
        int fontSize=Integer.parseInt(request.getParameter("fontSize"));
        GenerateWord.generateWord.create(fontSize,fontName,Bean.getHtml());


        response.setContentType("application/x-download");
        String filename=request.getParameter("filename")+".docx";
        response.addHeader("Content-Disposition","attachment;filename="+filename);
        IOUtils.copy(new FileInputStream(path+"data/Result.docx"),response.getOutputStream());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        doPost(request,response);
    }
}
