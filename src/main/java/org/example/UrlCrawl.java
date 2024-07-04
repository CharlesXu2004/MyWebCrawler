package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jsoup.Jsoup;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.selector.Html;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class UrlCrawl {
    public static UrlCrawl urlCrawl=new UrlCrawl();
    public void Crawl(Html html,HttpSession session) {

        //获取链接和链接文本
        List<String> itemlinks=html.xpath("//span[@class='news_title']").links().all();
        List<String> linktexts=html.xpath("//span[@class='news_title']").regex("(?<=<a.*>).*(?=</a>)").all();

        for(String linktext:linktexts){
            Jsoup.parse(linktext).text();
        }

        //存urlBean
        UrlBean urlBean=new UrlBean();
        urlBean.setHyberlink(itemlinks);
        urlBean.setText(linktexts);
        //存到session的URLs里
        session.setAttribute("URLs",urlBean);
    }
}
