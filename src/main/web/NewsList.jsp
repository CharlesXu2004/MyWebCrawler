<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.List" %>
<%@ page pageEncoding="GBK"%>
<jsp:useBean id="URLs" class="org.example.UrlBean" scope="session"/>
<!DOCTYPE html>
<%
    PrintWriter writer=response.getWriter();

    writer.println("<html>");
    writer.println("<head>");
    writer.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"NewsListCSS.css\">");//引入样式表
    writer.println("<title>NewsList</title>");
    writer.println("</head><body>");

    writer.println("<main>");

    writer.print("<div  class=\"ButtonBox\" id=\"ButtonBox\">");//按钮框

    List<String> t1=URLs.getText();//获取标题文本
    List<String> t2=URLs.getHyberlink();
    for(int i=0;i<t1.size();i++){
        String t=t1.get(i);//第i个标题
        writer.println("<form  action=\"buttonEvent\" method=\"post\">");
        //定义按钮名称
        writer.println("<input type=\"submit\" class=\"button\" name=\"button"+i+"\" value=\""+t+"\"  >");
        writer.println("</input>");
        writer.println("</form>");
        //onclick=\"window.location.href='Main.jsp'\">"
    }
    //右侧标题和副标题部分
    writer.println(" </div>");
    //内嵌定义了样式
    writer.print("<div style=\" text-align:center; display: flex; justify-content: center; align-items: center; flex-direction:column \">");
    writer.println("<div class=\"title\" >"+"选择要保存的文章"+"</div><br>");
    writer.println(" </div>");

    writer.println("</main> ");

    %>
<%--动画脚本--%>
<script>
    let title = document.querySelector(".title");
    let subTitle = document.querySelector(".subtitle");

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
    let subTitleTiming = {
        duration: titleChange.effect.getComputedTiming().duration/2,
        easing: "ease-in-out",
    };

    const subTitleChange = subTitle.animate(fadeAndMove, subTitleTiming);

</script>

<%
    writer.print("</body> </html>");
%>





