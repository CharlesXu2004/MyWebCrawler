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
            <p>title--</p>
            <jsp:getProperty name="Passage" property="title"/>
            <p>--</p>
            <jsp:getProperty name="Passage" property="text"/>
        </div>
    </div>
    <div style="display: flex" class>
        <form action="CreatePdf" method="post">
            <fieldset>
                <legend>使用pdf保存</legend>
                字体：
                <select name="font">
                    <option value="Kai.ttf">楷体</option>
                    <option value="Song.ttf">宋体</option>
                    <option value="Fang.ttf">仿宋</option>
                </select><br/>
                字号：<input type="text" name="fontSize"/><br/>
                <input type="submit"/>
            </fieldset>
        </form>

        <div style="width:200px"></div>

        <form action="CreateWord" method="post">
            <fieldset>
                <legend>使用word保存</legend>
                字体：
                <select name="font">
                    <option value="楷体">楷体</option>
                    <option value="宋体">宋体</option>
                    <option value="仿宋">仿宋</option>
                </select><br/>
                字号：<input type="text" name="fontSize"/><br/>
                <input type="submit"/>
            </fieldset>
        </form>
    </div>
</main>

</body>
</html>
