<%@ page import="java.io.PrintWriter" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>urlInputPage</title>
    <%--引入样式表--%>
    <link rel="stylesheet" type="text/css" href="BackCSS.css">
    <style>
        .backbutton{
            align-items: center;
            appearance: none;
            background-color: #05060f0a;
            border-radius: 24px;
            border-style: none;
            box-shadow: rgba(0, 0, 0, .2) 0 3px 5px -1px,rgba(0, 0, 0, .14) 0 6px 10px 0,rgba(0, 0, 0, .12) 0 1px 18px 0;
            box-sizing: border-box;
            color: hsl(0, 2%, 77%);
            cursor: pointer;
            display: inline-flex;
            fill: currentcolor;
            font-size: 20px;
            font-weight: bold;
            height: 50px;
            justify-content: center;
            letter-spacing: 0;
            line-height: normal;
            max-width: 100%;
            overflow: visible;
            padding: 2px 24px;
            position: relative;
            text-align: center;
            text-transform: none;
            transition: box-shadow 280ms cubic-bezier(.4, 0, .2, 1),opacity 15ms linear 30ms,transform 270ms cubic-bezier(0, 0, .2, 1) 0ms;
            user-select: none;
            -webkit-user-select: none;
            touch-action: manipulation;
            width: auto;
            will-change: transform,opacity;
            z-index: 0;
        }

        .backbutton:hover {
            background: #05060f0a;
            color: hsl(0, 0%, 100%);
            transform: scale(1.05);
            -ms-transform: scale(1.05);
        }
    </style>
    <%--内嵌覆盖样式--%>
</head>
<jsp:useBean id="URLs" class="org.example.UrlBean" scope="session"/>
<%--应用javabean存url--%>
    <body>
    <main>
        <%--提示信息--%>
        <div class="container">
            <h1 class="title">输入与条件不符或暂时不支持此类查找</h1>
            <br>
            <br>
            <br>
        </div>
        <br>
        <br>
            <%--返回按钮--%>
        <form action="UrlCrawl" method="post">
            <input name="backbutton" class="backbutton" type="submit"  value="点击返回输入界面" >
        </form>
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
