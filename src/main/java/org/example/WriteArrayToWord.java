//package org.example;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.poi.xwpf.usermodel.XWPFRun;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
//public class WriteArrayToWord {
//    private List<String> list;
//
//    public WriteArrayToWord() {}
//    public WriteArrayToWord(List<String> list) {this.list = list;}
//
//    public void setList(List<String> list) {
//        this.list = list;
//    }
//
//    public List<String> getList() {
//        return list;
//    }
//
//    public void WriteToWord()throws IOException
//    {
//        // 创建一个新的 XWPFDocument
//
//        System.out.println("Document written successfully!");
//        // 遍历数组并将每个元素添加到 Word 文档中
//
//
//        String ProjectPath="D:\\simpleWrite.docx";
//        // 将文档写入文件
//        try (FileOutputStream out = new FileOutputStream(ProjectPath)) {
//            document.write(out);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        finally {
//            try {
//                document.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
//
