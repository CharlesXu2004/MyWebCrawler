package org.example;

import us.codecraft.webmagic.selector.Html;

import java.util.List;

public class PassageCrawlBean {
    private String title,text;
    private Html html;

    public Html getHtml() {
        return html;
    }

    public void setHtml(Html html) {
        this.html = html;
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
