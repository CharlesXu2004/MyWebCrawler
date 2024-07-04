package org.example;

import java.util.List;

public class CrawlerBean {
    private List<String> hyberlink,text;

    public List<String> getHyberlink() {
        return this.hyberlink;
    }

    public void setHyberlink(List<String> list){
        this.hyberlink=list;
    }

    public List<String> getText() {
        return this.text;
    }

    public void setText(List<String> list){
        this.text=list;
    }
}
