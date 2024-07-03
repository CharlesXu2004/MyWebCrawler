<%@ page import="java.io.PrintWriter" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>主界面</title>
</head>
    <body>
        <jsp:useBean id="jsp_urlbean" class="org.example.UrlBean" scope="session"/>

        <form action="" method="post">
            url:<input type="text" name="url"><br>
            <input type="submit" value="submit">
        </form>
    <%!public int cnt=0;%>
    <%
        String url=request.getParameter("url");
        if(url==null||"".equals(url)){
            PrintWriter writer=response.getWriter();
            if(cnt>0)writer.print("请重新输入url");
            cnt++;
        }
        else{
            request.setAttribute("url", url);

            request.getRequestDispatcher("Main.jsp").forward(request,response);
            cnt=0;
        }
    %>
    </body>
</html>
