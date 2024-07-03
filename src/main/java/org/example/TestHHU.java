package org.example;


import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import javax.swing.text.html.HTML;
import java.util.List;

public class TestHHU implements PageProcessor {
    private Site site = Site.me().setRetryTimes(1).setSleepTime(100);
    private Page page;
    public Page getPage() {
        return this.page;
    }
    @Override
    public void process(Page page) {
        this.page = page;
         //page.putField("title", page.getHtml().xpath("/html/head/title"));
    }
    @Override
    public Site getSite() {
        return site;
    }
}


