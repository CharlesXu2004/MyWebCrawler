
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
</head>
<body>
<jsp:useBean id="URLs" class="org.example.UrlBean" scope="session"/>
<jsp:useBean id="Passage" class="org.example.PassageCrawlBean" scope="session"/>
<main>

    <div  class="textbox01" id="textbox01">

        <div class="text01" id="text01">
            <div class="myh1" >
                <h1><jsp:getProperty name="Passage" property="title"/></h1>
            </div>
            <div class="deepfont">
                <jsp:getProperty name="Passage" property="text"/>
            </div>
        </div>
    </div>

    <div  class="buttonbox">

        <form action="CreatePdf" method="post">

            <div class="myh1" style="text-align: center">
                <h1>使用pdf保存</h1>
            </div>
            <br>
            <div class="font">字体：&nbsp;&thinsp;&thinsp;
                <select class="selectbox" name="font">
                    <option value="Kai.ttf">楷体</option>
                    <option value="Sung.ttf">宋体</option>
                    <option value="Fang.ttf">仿宋</option>
                </select>
                字号：<input  class="fontsizebox" type="text" name="fontSize"/><br>
            </div>
                <br>
            <div class="font">文件名：<input  class="filename" type="text" name="filename"/><br></div>
            <br>
            <div style="text-align: center">
                <input class="submitbutton" type="submit" value="download"/>
            </div>
        </form>

        <br>

        <form action="CreateWord" method="post">
            <div class="myh1" style="text-align: center">
                <h1>使用word保存</h1>
            </div>
            <br>
            <div class="font">字体：&nbsp;&thinsp;&thinsp;
            <select class="selectbox" name="font">
                    <option value="楷体">楷体</option>
                    <option value="宋体">宋体</option>
                    <option value="仿宋">仿宋</option>
                </select>
                字号：<input class="fontsizebox" type="text" name="fontSize"/><br></div>
                <br>
            <div class="font">文件名：<input  class="filename" type="text" name="filename"/><br></div>
            <br>
            <div style="text-align: center">
                <input class="submitbutton" type="submit" value="download"/>
            </div>
        </form>
    </div>
</main>
</body>
</html>
