package org.example;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.selector.Html;

public class StartUrl {

    public Html Start(String url){

        Spider spider = new Spider();

        us.codecraft.webmagic.Spider.create(spider).addUrl(url).addPipeline(new JsonFilePipeline(Path.PATH + "data"))
                .addPipeline(new ConsolePipeline())
                .run();
        Page p = spider.getPage();

        return p.getHtml();

    }
}
