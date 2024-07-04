package org.example;

import jakarta.servlet.http.HttpSession;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1CFont;
import org.example.Datas;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.selector.Html;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CrawlerServlet extends HttpServlet {
    @Override
    public void init()throws ServletException{
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Conten-Type","text/html;charset=utf-8");
        response.setContentType("text/html");

        String url=request.getParameter("url");
        if(url==null||"".equals(url)){
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
        else{
            PrintWriter out=response.getWriter();
            TestHHU testHHU=new TestHHU();
            Spider.create(testHHU).addUrl(url).addPipeline(new JsonFilePipeline(Datas.PATH+"data"))
                    .addPipeline(new ConsolePipeline())
                    .run();
            Page p=testHHU.getPage();
            Html html=p.getHtml();
            Datas.html=html;


            List<String> item=html.xpath("//div[@class='wp_articlecontent']").all();
            List<String> itemlinks=html.xpath("//li[@class='menu-item i2']/ul/li").links().all();

            CrawlerBean bean=new CrawlerBean();
            bean.setHyberlink(itemlinks);
            bean.setText(item);
            HttpSession session=request.getSession();
            session.setAttribute("result",bean);

            //.css("ul.sub-menu  li").css("li").
            //String item=html.xpath("//li[@class='sub-item i2-"+i+"']").toString();
            //for( String item1:item){
            //writer.println(item1);
            //}
            request.getRequestDispatcher("Main.jsp").forward(request,response);

        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        doPost(request,response);
    }
}
