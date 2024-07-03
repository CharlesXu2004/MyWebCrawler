package org.example;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;

import java.io.IOException;
import java.io.PrintWriter;

public class SpiderServlet extends HttpServlet {

    @Override
    public void init() throws ServletException{super.init();}
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");

        String url=req.getParameter("url");
        UrlBean urlBean=new UrlBean();

        //HttpSession session=req.getSession(true);
        //session.setAttribute("urlBean",urlBean);
        if(url.isEmpty()){
            resp.sendRedirect("test01.jsp");
        }
        else{
            urlBean.setBeanUrl(url);
            req.setAttribute("url",urlBean.getBeanUrl());
            req.getRequestDispatcher("../Main.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
