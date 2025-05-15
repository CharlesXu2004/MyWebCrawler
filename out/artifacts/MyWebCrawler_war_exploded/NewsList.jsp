<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.List" %>
<%@ page pageEncoding="GBK"%>
<jsp:useBean id="URLs" class="org.example.UrlBean" scope="session"/>
<!DOCTYPE html>
<%
    PrintWriter writer=response.getWriter();

    writer.println("<html>");
    writer.println("<head>");
    writer.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"NewsListCSS.css\">");//������ʽ��
    writer.println("<title>NewsList</title>");
    writer.println("</head><body>");

    writer.println("<main>");

    writer.print("<div  class=\"ButtonBox\" id=\"ButtonBox\">");//��ť��

    List<String> t1=URLs.getText();//��ȡ�����ı�
    List<String> t2=URLs.getHyberlink();
    for(int i=0;i<t1.size();i++){
        String t=t1.get(i);//��i������
        writer.println("<form  action=\"buttonEvent\" method=\"post\">");
        //���尴ť����
        writer.println("<input type=\"submit\" class=\"button\" name=\"button"+i+"\" value=\""+t+"\"  >");
        writer.println("</input>");
        writer.println("</form>");
        //onclick=\"window.location.href='Main.jsp'\">"
    }
    //�Ҳ����͸����ⲿ��
    writer.println(" </div>");
    //��Ƕ��������ʽ
    writer.print("<div style=\" text-align:center; display: flex; justify-content: center; align-items: center; flex-direction:column \">");
    writer.println("<div class=\"title\" >"+"ѡ��Ҫ���������"+"</div><br>");
    writer.println(" </div>");

    writer.println("</main> ");

    %>
<%--�����ű�--%>
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





