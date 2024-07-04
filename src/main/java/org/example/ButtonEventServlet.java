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
        List<String> titlelist=urlbean.getText();

        System.out.println("进了servlet");
       // String button = request.getParameter(textlist.get(0));
        String url;
        String title;
        for(int i=0;i<linklist.size();i++){

            String b="button"+i;
            if(request.getParameter(b)!=null) {
                url=linklist.get(i);
                title=titlelist.get(i);

                SqlWork sqlWork= new SqlWork();
                boolean check=sqlWork.check(title);

                StartUrl starturl = new StartUrl();
                Html html = starturl.Start(url);

                if(!check) {
                    //处理并存好文章
                    PassageCrawl passageCrawl = new PassageCrawl();
                    passageCrawl.Crawl(html, session);

                    PassageCrawlBean bean=(PassageCrawlBean) session.getAttribute("Passage");
                    sqlWork.insert(bean.getTitle(),bean.getText());
                }
                else {
                    PassageCrawlBean bean = new PassageCrawlBean();
                    bean.setTitle(title);
                    bean.setText(sqlWork.content);
                    bean.setHtml(html);
                    session.setAttribute("Passage",bean);
                }
                response.sendRedirect("Main.jsp");
                break;
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        doPost(request,response);
    }
}
