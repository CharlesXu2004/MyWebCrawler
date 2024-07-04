<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.UrlBean" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="URLs" class="org.example.UrlBean" scope="session"/>
<%          PrintWriter writer=response.getWriter();

    writer.println("<html>");
    writer.println("<head>");
    writer.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"NewsListCSS.css\">");
    writer.println("<title>NewsList</title>");
    writer.println("</head><body>");
    writer.println("<jsp:useBean id=\"resultString\" class=\"org.example.TextStringBean\" scope=\"session\"/>");
    writer.println("<main>");

    writer.print("<div  class=\"ButtonBox\" id=\"ButtonBox\">");
    List<String> t1=URLs.getText();
    for(int i=0;i<t1.size();i++){
        String t=t1.get(i);
        writer.println("<button class=\"button\" onclick=\"window.location.href='Main.jsp'\">"+i+"</button>");
    }
    writer.println(" </div>");
   // writer.println("</div> </div><div style=\"display: flex\" class>");
    writer.println("</main> </body> </html>");
%>
<script>

</script>





