<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>2</title>
</head>
<body>
<jsp:useBean id="result" class="org.example.CrawlerBean" scope="session"/>
<%
    List<String>item=result.getText(),itemlinks=result.getHyberlink();
    for(int k=0;k<item.size();k++){
        out.print("<a href=\""+ itemlinks.get(k) +"\" target=\"_self\">"+item.get(k)+"</a><br>");
    }
%>

<form action="CreatePdf" method="post">
    <fieldset>
        <legend>使用pdf保存</legend>
        字体：
        <select name="font">
            <option value="KaiTi.ttf">楷体</option>
            <option value="SongTi.ttf">宋体</option>
        </select><br/>
        字号：<input type="text" name="fontSize"/><br/>
        <input type="submit"/>
    </fieldset>
</form>
<form>
    <fieldset>
        <legend>使用word保存</legend>
    </fieldset>
</form>
</body>
</html>
