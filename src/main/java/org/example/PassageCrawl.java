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
        String title=html.xpath("//h1[@class='arti_title']").regex("(?<=<h1.*>).*(?=</h1>)").toString();//匹配class名为arti_title的h1标签下的所有文本
        String text=html.xpath("//div[@class='wp_articlecontent']").toString();//匹配class名为wp_articlecontent的div标签下的所有文本信息

        //存相应文章标题、文章文本内容、文章源码内容信息到PassageCrawlBean
        PassageCrawlBean passageCrawlBean=new PassageCrawlBean();
        passageCrawlBean.setHtmlText(text);
        passageCrawlBean.setTitle(title);
        text=Jsoup.parse(text).text();//去掉html标签
        passageCrawlBean.setText(text);

        //存PassageCrawlBean到session的URLs里
        session.setAttribute("Passage",passageCrawlBean);
    }
}
