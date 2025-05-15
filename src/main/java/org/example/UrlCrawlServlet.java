package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jsoup.Jsoup;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.selector.Html;

import java.io.IOException;
import java.util.List;

@WebServlet("/UrlCrawl")
public class UrlCrawlServlet extends HttpServlet {
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

        //获取项目根路径
        String path=request.getSession().getServletContext().getRealPath("/");
        path=path.substring(0,path.indexOf("out"));

        // 点返回按键回到输入界面
        if(request.getParameter("backbutton")!=null){
            response.sendRedirect("index.jsp");
            return;
        }


        String url;
        if(request.getParameter("url")!=null){//读到url进入判断
            url=request.getParameter("url");//获取输入的url到字符串
            String patternUrl="https://www.hhu.edu.cn/13914/list";//模式串

            if(!url.startsWith(patternUrl)){//url前缀与模式串不匹配时进入
                //url输入不符则跳转到back.jsp
                response.sendRedirect("back.jsp");
                return;
            }

            //获取到指定url的html信息
            StartUrl startUrl=new StartUrl();
            Html html=startUrl.Start(url,path);

            //获取session对象
            HttpSession session=request.getSession();

            //处理页面内容
            UrlCrawl urlCrawl=new UrlCrawl();
            urlCrawl.Crawl(html,session);

            //转发到列表选择界面
            request.getRequestDispatcher("NewsList.jsp").forward(request,response);
        }else {
            url="null";
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        doPost(request,response);
    }
}
