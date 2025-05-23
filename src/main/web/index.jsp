<%@ page import="java.io.PrintWriter" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>urlInputPage</title>
    <link rel="stylesheet" type="text/css" href="urlInputPageCSS.css">
    <%--引入样式表--%>
</head>
<jsp:useBean id="URLs" class="org.example.UrlBean" scope="session"/>
<%--应用javabean存url--%>
    <body>
    <main>
        <%--标题和副标题--%>
        <div class="container">
            <h1 class="title">Welcome</h1>
            <h2 class="subtitle">输入url以开始爬取<br>请输入以“https://www.hhu.edu.cn/13914/list”为前缀的url</h2>
            
            <br>
            <br>
            <br>
        </div>
        <div class="subcontainer">
            <%--url输入框和提交按钮--%>
            <form action="UrlCrawl" method="post">
                <input class="box" type="text" name="url" value="https://www.hhu.edu.cn/13914/list.htm"><br>
                <br>
                <br>
                <input class="button" type="submit"  value="submit" >
            </form>
        </div>

    </main>
    <%--动画脚本--%>
    <script>
        let title = document.querySelector(".title");
        let subTitle = document.querySelector(".subtitle");

        let subcontainer=document.querySelector(".subcontainer");

        let fadeAndMove = [
            {
                opacity: 0,
                transform: `translateY(-20px)`,
            },
            {
                opacity: 1,
                transform: `translateY(0px)`,
            },
        ];

        let titleTiming = {
            duration: 900,
            easing: "ease-in-out",
        };

        const titleChange = title.animate(fadeAndMove, titleTiming);

        let expand = [
            {
                letterSpacing: "-0.5em",
                opacity: 0,
            },
            {
                letterSpacing: "initial",
                opacity: 1,
            },
        ];

        let subTitleTiming = {
            duration: titleChange.effect.getComputedTiming().duration*4/5,
            easing: "ease-in-out",
        };


        const subTitleChange = subTitle.animate(fadeAndMove, subTitleTiming);
        subTitleChange.pause();


        let subContainerTiming = {
            duration: subTitleChange.effect.getComputedTiming().duration / 2,
            easing: "ease-in-out",
        };
        const subContainerChange=subcontainer.animate(expand,subContainerTiming);
        subContainerChange.pause();
        var x=0;
        document.addEventListener("click", () => {
            // idle, running, paused, finished
            if (subTitleChange.playState !== "finished") {
                subTitleChange.play();
            }
            if(subTitleChange.playState === "finished"&&x===0){
                x++;
                subContainerChange.play();
            }
        });
    </script>

    </body>
</html>
