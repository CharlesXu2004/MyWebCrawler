<%@ page import="java.io.PrintWriter" %>
<%@ page import="org.example.TestHHU" %>
<%@ page import="us.codecraft.webmagic.Spider" %>
<%@ page import="us.codecraft.webmagic.pipeline.ConsolePipeline" %>
<%@ page import="us.codecraft.webmagic.pipeline.JsonFilePipeline" %>
<%@ page import="us.codecraft.webmagic.Page" %>
<%@ page import="java.util.List" %>
<%@ page import="us.codecraft.webmagic.selector.Html" %>
<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.regex.Matcher" %>
<%@ page import="org.apache.pdfbox.pdmodel.PDDocument" %>
<%@ page import="org.apache.pdfbox.pdmodel.PDPage" %>
<%@ page import="java.io.File" %>
<%@ page import="org.apache.pdfbox.pdmodel.PDPageContentStream" %>
<%@ page import="org.apache.pdfbox.pdmodel.font.PDFont" %>
<%@ page import="org.apache.pdfbox.pdmodel.font.PDType0Font" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
<%
    PrintWriter writer=response.getWriter();
    String url=request.getParameter("url");
    TestHHU testHHU=new TestHHU();
    Spider.create(testHHU).addUrl(url).addPipeline(new JsonFilePipeline("D:\\idea\\projects\\SpiderTest\\data"))
            .addPipeline(new ConsolePipeline())
            .run();
    Page p=testHHU.getPage();
    Html html=p.getHtml();

    List<String> item=html.xpath("//li[@class='menu-item i2']/ul/li").regex("(?<=<a.*>).*(?=</a>)").all();
    List<String> itemlinks=html.xpath("//li[@class='menu-item i2']/ul/li").links().all();
    //.css("ul.sub-menu  li").css("li").
    //String item=html.xpath("//li[@class='sub-item i2-"+i+"']").toString();
    //for( String item1:item){
    //writer.println(item1);
    //}
    for(int k=0;k<item.size();k++){
        writer.println("<a href=\""+ itemlinks.get(k) +"\" target=\"_self\">"+item.get(k)+"</a><br>");
    }

    String ProjectPath="D:/0_Programming/__LANGUAGE__JSP/MyWebCrawler";                  //项目根节点
    //writer.println(ProjectPath);
    //writer.println("111");
    PDDocument document = new PDDocument();                             //创建一个空文档
    document.addPage(new PDPage());
    PDPage pdfpage = document.getPage(0);                      //下标从0开始
    PDPageContentStream contentStream=new PDPageContentStream(document,pdfpage);

    PDFont font= PDType0Font.load(document, new File(ProjectPath+"/Fonts/HarmonyOS_Sans_SC_Bold.ttf"));
    contentStream.setFont(font,12);
    contentStream.beginText();
    contentStream.newLineAtOffset(80,750);

    List<String>pdftext=html.xpath("//li[@class='menu-item i2']/ul/li").regex("(?<=<a.*>).*(?=</a>)").all();

    for(String text:pdftext){
        contentStream.showText(text);
        contentStream.newLineAtOffset(0,-20);
    }

    contentStream.endText();
    contentStream.close();
    document.save(ProjectPath+"/data/pdf.pdf"); //保存
    document.close();
%>
</body>
</html>
