package org.example;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.selector.Html;

import java.io.IOException;

public class StartUrl {

    //传入爬取页面的url
    public Html Start(String url,String path) throws IOException {

        Spider spider = new Spider();//新建spider实例

        us.codecraft.webmagic.Spider.create(spider).addUrl(url).addPipeline(new JsonFilePipeline(path + "data"))
                .addPipeline(new ConsolePipeline())
                .run();
        //启动线程并存指定信息到指定路径和输出到控制台，可选择关闭
        Page p = spider.getPage();

        return p.getHtml();//返回当前url页面的html信息

    }
}
