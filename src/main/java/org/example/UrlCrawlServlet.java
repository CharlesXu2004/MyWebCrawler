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
        Spider spider = new Spider();
        us.codecraft.webmagic.Spider.create(spider).addUrl(url).addPipeline(new JsonFilePipeline(Datas.PATH + "data"))
                .addPipeline(new ConsolePipeline())
                .run();
        Page p = spider.getPage();
        Html html=p.getHtml();

        List<String> itemlinks=html.xpath("//span[@class='news_title']").links().all();
        List<String> linktexts=html.xpath("//span[@class='news_title']").links().all();
        for(String linktext:linktexts){
            Jsoup.parse(linktext).text();
        }
        UrlBean urlBean=new UrlBean();
        urlBean.setText(linktexts);
        urlBean.setHyberlink(itemlinks);
        HttpSession session=request.getSession();
        session.setAttribute("URLs",urlBean);

        request.getRequestDispatcher("NewsList.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        doPost(request,response);
    }
}
