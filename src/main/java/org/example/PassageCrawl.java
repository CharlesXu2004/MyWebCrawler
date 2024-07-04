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
    public void Crawl(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String url=request.getParameter("url");
        if(url==null||"".equals(url))request.getRequestDispatcher("index.jsp").forward(request,response);
        else {
            Spider spider = new Spider();
            us.codecraft.webmagic.Spider.create(spider).addUrl(url).addPipeline(new JsonFilePipeline(Datas.PATH + "data"))
                    .addPipeline(new ConsolePipeline())
                    .run();
            Page p = spider.getPage();
            Html html=p.getHtml();

            String textString=html.xpath("//div[@class='wp_articlecontent']").toString();
            String titleString=html.xpath("//h1[@class='article_title']").regex("(?<=<h1.*>).*(?=</h1>)").toString();

            PassageCrawlBean passageCrawlBean=new PassageCrawlBean();
            passageCrawlBean.setTitle(titleString);
            passageCrawlBean.setText(textString);

            HttpSession session=request.getSession();
            session.setAttribute("Passage",passageCrawlBean);

//            String fullText=html.xpath("//div[@class='wp_articlecontent']").toString();
//            fullText=fullText.replace("</p>","^");
//            String textOnly = Jsoup.parse(fullText).text();
//            Bean.setText(textOnly);
        }
    }
}
