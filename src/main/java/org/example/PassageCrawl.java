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

public class PassageCrawl {
    public static PassageCrawl passageCrawl;
    public void Crawl(Html html,HttpSession session){

        //获取标题和文本
        String title=html.xpath("//h1[@class='arti_title']").regex("(?<=<h1.*>).*(?=</h1>)").toString();
        String text=html.xpath("//div[@class='wp_articlecontent']").toString();
        text=Jsoup.parse(text).text();

        //存PassageCrawlBean
        PassageCrawlBean passageCrawlBean=new PassageCrawlBean();
        passageCrawlBean.setTitle(title);
        passageCrawlBean.setText(text);
        passageCrawlBean.setHtml(html);

        //存到session的URLs里
        session.setAttribute("Passage",passageCrawlBean);
    }
}
