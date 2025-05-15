package org.example;

import java.util.List;

public class UrlBean {
    private List<String>hyberlink,text;
    //hyberlink存链接
    //text存链接文本
    public List<String> getHyberlink() {
        return hyberlink;
    }

    public void setHyberlink(List<String> list){
        hyberlink=list;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> list){
        text=list;
    }
}
