package org.example;

import jakarta.servlet.annotation.WebServlet;
import org.apache.pdfbox.io.IOUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.zip.CheckedOutputStream;

/*
 * CreateWordServlet类
 * 这个Servlet处理基于用户输入生成Word文档的功能，其中GenerateWord是具体生成word的工具类
 */
@WebServlet("/CreateWord")
public class CreateWordServlet extends HttpServlet {
    @Override
    public void init()throws ServletException{
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        // 设置请求和响应的字符编码为UTF-8
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        //获取项目根路径
        String path=request.getSession().getServletContext().getRealPath("/");
        path=path.substring(0,path.indexOf("out"));
        // 从会话中获取PassageCrawlBean对象
        PassageCrawlBean Bean=(PassageCrawlBean)request.getSession().getAttribute("Passage");
        // 从请求参数中获取字体名称和字体大小
        String fontName = request.getParameter("font");
        int fontSize=Integer.parseInt(request.getParameter("fontSize"));
        // 调用GenerateWord功能类，使用指定的字体和大小生成Word文档
        GenerateWord.create(fontSize,fontName,Bean.getTitle(),Bean.getHtmlText(),path);


        // 从请求参数中获取文件名，响应名由用户设置，并设置响应头以便下载文件
        //UTF-8解码，使得中文文件名下载不会报错
        String filename = URLEncoder.encode(request.getParameter("filename"),"UTF-8")+".docx";
        response.addHeader("Content-Disposition","attachment;filename="+filename);
        // 设置响应内容类型为下载文件
        response.setContentType("application/x-download");
        IOUtils.copy(new FileInputStream(path+"data/Result.docx"),response.getOutputStream());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        doPost(request,response);
    }
}
