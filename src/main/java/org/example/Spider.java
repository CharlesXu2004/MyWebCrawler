package org.example;


import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class Spider implements PageProcessor {
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

