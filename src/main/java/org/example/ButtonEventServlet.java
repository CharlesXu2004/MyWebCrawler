package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jsoup.Jsoup;
import us.codecraft.webmagic.selector.Html;

import java.io.IOException;
import java.util.List;

@WebServlet("/buttonEvent")
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

        //获取对应session的URLs信息到新建实例urlBean
        UrlBean urlbean = (UrlBean) session.getAttribute("URLs");
        List<String> linklist=urlbean.getHyberlink();//获取链接
        List<String> titlelist=urlbean.getText();//获取链接文本

        //System.out.println("进了servlet"); //测试用

        //获取项目根路径
        String path=request.getSession().getServletContext().getRealPath("/");
        path=path.substring(0,path.indexOf("out"));
        String url;
        String title;
        for(int i=0;i<linklist.size();i++){

            String b="button"+i;
            //循环对button事件响应
            if(request.getParameter(b)!=null) {
                url=linklist.get(i);
                title=titlelist.get(i);
                //获取第i个url、title

                //判断第i篇文章是否在数据库里已经存在
                SqlWork sqlWork= new SqlWork();
                boolean check=sqlWork.check(title);

                //开启爬取页面信息
                StartUrl starturl = new StartUrl();
                Html html = starturl.Start(url,path);

                //如果数据库中不存在
                if(!check) {
                    //处理并存好文章
                    PassageCrawl passageCrawl = new PassageCrawl();
                    passageCrawl.Crawl(html, session);

                    //获取存好的文章信息并存入数据库
                    PassageCrawlBean bean=(PassageCrawlBean) session.getAttribute("Passage");
                    sqlWork.insert(bean.getTitle(),bean.getHtmlText());
                }
                else {
                    //取出文章信息
                    PassageCrawlBean bean = new PassageCrawlBean();
                    bean.setTitle(title);
                    bean.setHtmlText(sqlWork.content);
                    bean.setText(Jsoup.parse(sqlWork.content).text());
                    //文章信息存入对应session的bean中
                    session.setAttribute("Passage",bean);
                }
                //重定向到下载页面
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
