package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jsoup.Jsoup;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.selector.Html;

import java.io.IOException;
import java.util.List;

public class UrlCrawlServlet extends HttpServlet {
    @Override
    public void init()throws ServletException{
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Conten-Type","text/html;charset=utf-8");
        response.setContentType("text/html");

        String url=request.getParameter("url");

        StartUrl startUrl=new StartUrl();
        Html html=startUrl.Start(url);

        //获取session对象
        HttpSession session=request.getSession();

        //处理页面内容
        UrlCrawl urlCrawl=new UrlCrawl();
        urlCrawl.Crawl(html,session);

        //
        request.getRequestDispatcher("NewsList.jsp").forward(request,response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        doPost(request,response);
    }
}
