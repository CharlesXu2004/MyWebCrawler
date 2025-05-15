<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main</title>
    <link rel="stylesheet" type="text/css" href="PDF_WORDCSS.css">
    <style>
        h1{
            color: white!important;
            font-size: 50px!important;
        }
    </style>
    <%--引入样式表--%>
</head>
<body>
<jsp:useBean id="URLs" class="org.example.UrlBean" scope="session"/>
<jsp:useBean id="Passage" class="org.example.PassageCrawlBean" scope="session"/>
<main>
    <%--文本背景--%>
    <div  class="textbox01" id="textbox01">
        <%--文本--%>
        <div class="text01" id="text01">
            <%--标题--%>
            <div class="myh1" >
                <h1><jsp:getProperty name="Passage" property="title"/></h1>
            </div>
                <%--正文--%>
            <div class="deepfont">
                <jsp:getProperty name="Passage" property="text"/>
            </div>
        </div>
    </div>
        <%--按钮框--%>
    <div  class="buttonbox">
        <%--pdf部分--%>
        <form action="CreatePdf" method="post">
            <%--提示--%>
            <div class="myh1" style="text-align: center">
                <h1>使用pdf保存</h1>
            </div>
            <br>
                <%--字体字号选择框--%>
            <div class="font">字体：&nbsp;&thinsp;&thinsp;
                <select class="selectbox" name="font">
                    <option value="Kai.ttf">楷体</option>
                    <option value="Sung.ttf">宋体</option>
                </select>
                字号：<input  class="fontsizebox" type="text" name="fontSize" value="12"/><br>
            </div>
                <br>
                <%--文件名输入框--%>
            <div class="font">文件名：<input  class="filename" type="text" name="filename" value="请输入文件名"/><br></div>
            <br>
                <%--下载按钮--%>
            <div style="text-align: center">
                <input class="submitbutton" type="submit" value="download"/>
            </div>
        </form>

        <br>
            <%--word部分--%>
        <form action="CreateWord" method="post">
            <%--提示--%>
            <div class="myh1" style="text-align: center">
                <h1>使用word保存</h1>
            </div>
            <br>
                <%--字体字号选择框--%>
            <div class="font">字体：&nbsp;&thinsp;&thinsp;
            <select class="selectbox" name="font">
                    <option value="楷体">楷体</option>
                    <option value="宋体">宋体</option>
                    <option value="黑体">黑体</option>
                </select>
                字号：<input class="fontsizebox" type="text" name="fontSize" value="12"/><br></div>
                <br>
                <%--文件名输入框--%>
            <div class="font">文件名：<input  class="filename" type="text" name="filename" value="请输入文件名"/><br></div>
            <br>
                <%--下载按钮--%>
            <div style="text-align: center">
                <input class="submitbutton" type="submit" value="download"/>
            </div>
        </form>
    </div>
</main>
</body>
</html>
