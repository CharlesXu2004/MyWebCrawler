
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
    <link rel="stylesheet" type="text/css" href="PDF_WORDCSS.css">
</head>
<body>
<jsp:useBean id="URLs" class="org.example.UrlBean" scope="session"/>
<jsp:useBean id="Passage" class="org.example.PassageCrawlBean" scope="session"/>
<main>

    <div  class="textbox01" id="textbox01">
        <div class="text01" id="text01">
            <h1>
            <jsp:getProperty name="Passage" property="title"/>
            </h1>
            <jsp:getProperty name="Passage" property="text"/>
        </div>
    </div>

    <div style="display: flex" class>
        <form action="CreatePdf" method="post">

                <legend>使用pdf保存</legend>
                字体：<select class="selectbox" name="font">
                    <option value="Kai.ttf">楷体</option>
                    <option value="Sung.ttf">宋体</option>
                    <option value="Fang.ttf">仿宋</option>
                </select><br/>
                <br>
                字号：<input  class="fontsizebox" type="text" name="fontSize"/><br/>
                <input class="submitbutton" type="submit" value="download"/>

        </form>

        <div style="width:200px"></div>

        <form action="CreateWord" method="post">
                <legend>使用word保存</legend>
                字体：<select class="selectbox" name="font">
                    <option value="楷体">楷体</option>
                    <option value="宋体">宋体</option>
                    <option value="仿宋">仿宋</option>
                </select><br/>
                <br>
                字号：<input class="fontsizebox" type="text" name="fontSize"/><br/>
                <input class="submitbutton" type="submit" value="download"/>
        </form>
    </div>
</main>
</body>
</html>
