package org.example;

import us.codecraft.webmagic.selector.Html;

import java.util.List;

public class PassageCrawlBean {
    //title存文章标题
    //text存文章正文文本
    //htmlText存文章部分的源码
    private String title,text,htmlText;

    public String getHtmlText() {
        return htmlText;
    }

    public void setHtmlText(String htmlText) {
        this.htmlText = htmlText;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
