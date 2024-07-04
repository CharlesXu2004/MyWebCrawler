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
    public static UrlCrawl urlCrawl;
    public void Crawl(String url,HttpSession session) {
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
            session.setAttribute("URLs",urlBean);
    }
}
