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
import java.net.URLEncoder;

@WebServlet("/CreatePdf")
public class CreatePdfServlet extends HttpServlet {
    @Override
    public void init()throws ServletException{
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        //获取项目根路径
        String path=request.getSession().getServletContext().getRealPath("/");
        path=path.substring(0,path.indexOf("out"));

        //获取session中存储文章相关内容的PassageCrawlBean
        PassageCrawlBean Bean=(PassageCrawlBean) request.getSession().getAttribute("Passage");
        PDDocument document=new PDDocument();
        //获取表单指定字体
        PDFont font= PDType0Font.load(document, new File(path+"Fonts/"+request.getParameter("font")));
        //获取表单指定字号
        float fontSize=Integer.parseInt(request.getParameter("fontSize"));

        try {//调用java文件实现新建pdf的具体逻辑
            GeneratePdf.create(font,fontSize,Bean.getHtmlText(),Bean.getTitle(),path);
        } catch (Exception ignored) {}

        document.close();


        //文件名为表单指定
        //UTF-8解码，使得中文文件名下载不会报错
        String filename = URLEncoder.encode(request.getParameter("filename"),"UTF-8")+".pdf";
        response.addHeader("Content-Disposition","attachment;filename="+filename);
        //设置响应内容类型为下载文件
        response.setContentType("application/x-download");
        IOUtils.copy(new FileInputStream(path+"data/Result.pdf"),response.getOutputStream());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        doPost(request,response);
    }
}
