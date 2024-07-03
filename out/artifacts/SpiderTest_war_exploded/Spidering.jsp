<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024/7/3
  Time: 下午2:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spidering</title>
</head>
<body>
正在跳转
<%
    String url=request.getParameter("url");
    PrintWriter writer=response.getWriter();
    request.setAttribute("url", url);
    response.sendRedirect("Main.jsp");
%>

</body>
</html>
