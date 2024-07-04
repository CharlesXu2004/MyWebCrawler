package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import us.codecraft.webmagic.selector.Html;

import java.io.IOException;
import java.util.List;

@WebServlet(name="/buttonEvent")
public class ButtonEventServlet extends HttpServlet {
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

        HttpSession session = request.getSession();

        UrlBean urlbean = (UrlBean) session.getAttribute("URLs");
        List<String> linklist=urlbean.getHyberlink();

        System.out.println("进了servlet");
       // String button = request.getParameter(textlist.get(0));
        String url;
        if(request.getParameter("button0")!=null) {
            url=linklist.get(0);

            StartUrl starturl=new StartUrl();
            Html html=starturl.Start(url);
            //处理并存好文章
            PassageCrawl passageCrawl=new PassageCrawl();
            passageCrawl.Crawl(html,session);

            response.sendRedirect("Main.jsp");
        }
        else if(request.getParameter("button1")!=null){

            url=linklist.get(1);

            StartUrl starturl=new StartUrl();
            Html html=starturl.Start(url);
            //处理并存好文章
            PassageCrawl passageCrawl=new PassageCrawl();
            passageCrawl.Crawl(html,session);

            response.sendRedirect("Main.jsp");
        }
        else if(request.getParameter("button2")!=null){

            url=linklist.get(2);

            StartUrl starturl=new StartUrl();
            Html html=starturl.Start(url);
            //处理并存好文章
            PassageCrawl passageCrawl=new PassageCrawl();
            passageCrawl.Crawl(html,session);

            response.sendRedirect("Main.jsp");
        }
        else if(request.getParameter("button3")!=null){

            url=linklist.get(3);

            StartUrl starturl=new StartUrl();
            Html html=starturl.Start(url);
            //处理并存好文章
            PassageCrawl passageCrawl=new PassageCrawl();
            passageCrawl.Crawl(html,session);

            response.sendRedirect("Main.jsp");
        }
        else if(request.getParameter("button4")!=null){
            url=linklist.get(4);

            StartUrl starturl=new StartUrl();
            Html html=starturl.Start(url);
            //处理并存好文章
            PassageCrawl passageCrawl=new PassageCrawl();
            passageCrawl.Crawl(html,session);

            response.sendRedirect("Main.jsp");
        }
        else if(request.getParameter("button5")!=null){

            url=linklist.get(5);

            StartUrl starturl=new StartUrl();
            Html html=starturl.Start(url);
            //处理并存好文章
            PassageCrawl passageCrawl=new PassageCrawl();
            passageCrawl.Crawl(html,session);

            response.sendRedirect("Main.jsp");
        }
        else if(request.getParameter("button6")!=null){

            url=linklist.get(6);

            StartUrl starturl=new StartUrl();
            Html html=starturl.Start(url);
            //处理并存好文章
            PassageCrawl passageCrawl=new PassageCrawl();
            passageCrawl.Crawl(html,session);

            response.sendRedirect("Main.jsp");
        }
        else if(request.getParameter("button7")!=null){

            url=linklist.get(7);

            StartUrl starturl=new StartUrl();
            Html html=starturl.Start(url);
            //处理并存好文章
            PassageCrawl passageCrawl=new PassageCrawl();
            passageCrawl.Crawl(html,session);

            response.sendRedirect("Main.jsp");
        }
        else if(request.getParameter("button8")!=null){

            url=linklist.get(8);

            StartUrl starturl=new StartUrl();
            Html html=starturl.Start(url);
            //处理并存好文章
            PassageCrawl passageCrawl=new PassageCrawl();
            passageCrawl.Crawl(html,session);

            response.sendRedirect("Main.jsp");
        }
        else if(request.getParameter("button9")!=null){

            url=linklist.get(9);

            StartUrl starturl=new StartUrl();
            Html html=starturl.Start(url);
            //处理并存好文章
            PassageCrawl passageCrawl=new PassageCrawl();
            passageCrawl.Crawl(html,session);

            response.sendRedirect("Main.jsp");
        }
        else if(request.getParameter("button10")!=null){

            url=linklist.get(10);

            StartUrl starturl=new StartUrl();
            Html html=starturl.Start(url);
            //处理并存好文章
            PassageCrawl passageCrawl=new PassageCrawl();
            passageCrawl.Crawl(html,session);

            response.sendRedirect("Main.jsp");
        }
        else if(request.getParameter("button11")!=null){

            url=linklist.get(11);

            StartUrl starturl=new StartUrl();
            Html html=starturl.Start(url);
            //处理并存好文章
            PassageCrawl passageCrawl=new PassageCrawl();
            passageCrawl.Crawl(html,session);

            response.sendRedirect("Main.jsp");
        }
        else if(request.getParameter("button12")!=null){

            url=linklist.get(12);

            StartUrl starturl=new StartUrl();
            Html html=starturl.Start(url);
            //处理并存好文章
            PassageCrawl passageCrawl=new PassageCrawl();
            passageCrawl.Crawl(html,session);

            response.sendRedirect("Main.jsp");
        }
        else if(request.getParameter("button13")!=null){

            url=linklist.get(13);

            StartUrl starturl=new StartUrl();
            Html html=starturl.Start(url);
            //处理并存好文章
            PassageCrawl passageCrawl=new PassageCrawl();
            passageCrawl.Crawl(html,session);

            response.sendRedirect("Main.jsp");
        }
        else if(request.getParameter("button14")!=null){

            url=linklist.get(14);

            StartUrl starturl=new StartUrl();
            Html html=starturl.Start(url);
            //处理并存好文章
            PassageCrawl passageCrawl=new PassageCrawl();
            passageCrawl.Crawl(html,session);

            response.sendRedirect("Main.jsp");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        doPost(request,response);
    }
}
