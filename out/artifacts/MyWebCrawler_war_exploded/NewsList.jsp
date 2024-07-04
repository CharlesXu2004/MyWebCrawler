<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="URLs" class="org.example.UrlBean" scope="session"/>
<%
    PrintWriter writer=response.getWriter();

    writer.println("<html>");
    writer.println("<head>");
    writer.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"NewsListCSS.css\">");
    writer.println("<title>NewsList</title>");
    writer.println("</head><body>");

    writer.println("<main>");

    writer.print("<div  class=\"ButtonBox\" id=\"ButtonBox\">");

    List<String> t1=URLs.getText();
    List<String> t2=URLs.getHyberlink();
    for(int i=0;i<t1.size();i++){
        String t=t1.get(i);
        writer.println("<form  action=\"buttonEvent\" method=\"post\">");
        writer.println("<input type=\"submit\" class=\"button\" name=\"button"+i+"\" value=\""+t+"\"  >");
        writer.println("</input>");
        writer.println("</form>");
        //onclick=\"window.location.href='Main.jsp'\">"
    }
    writer.println(" </div>");

    writer.println("</main> </body> </html>");
%>
<script>

</script>





