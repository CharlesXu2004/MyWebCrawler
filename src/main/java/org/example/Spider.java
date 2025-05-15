package org.example;


import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

//实现PageProcessor接口
public class Spider implements PageProcessor {
    //设置爬取间隔和时间
    private Site site = Site.me().setRetryTimes(1).setSleepTime(100);
    private Page page;
    public Page getPage() {
        return this.page;
    }
    //提供get方法
    @Override
    public void process(Page page) {
        this.page = page;
         //page.putField("title", page.getHtml().xpath("/html/head/title"));
        //可选择存取信息
    }
    @Override
    public Site getSite() {
        return site;
    }
}

