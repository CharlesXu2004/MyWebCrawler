package org.example;

import java.sql.*;

public class SqlWork {
    //！！！需要输入自己数据库的用户和密码！！！
    public String username="root",password="123456";
    //！！！需要输入自己数据库的用户和密码！！！

    public String content;  //存储查询结果

    //以标题在书库中查找
    public boolean check(String Title) {
        boolean is_find = false; //标记是否在数据库中查找到文章标题
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * from paraInfo"; //查询sql语句

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  //装载驱动
            String url = "jdbc:mysql://localhost:3306/Spider?autoReconnect=true&useSSL=false"; //数据库url
            conn = DriverManager.getConnection(url, username, password);   //建立连接
            ps = conn.prepareStatement(sql); //获取预编译语句
            rs = ps.executeQuery(); //获取结果集
            while (rs.next()) { //遍历结果集
                String title = rs.getString("title");
                if (title.equals(Title)) {  //如果查找找到，设置is_find为true
                    content = rs.getString("content");
                    is_find = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return is_find;
    }

    //插入新行
    public void insert(String title, String content) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "INSERT into paraInfo(title,content) values(?,?)"; //插入sql语句
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //装在驱动
            String url = "jdbc:mysql://localhost:3306/Spider?autoReconnect=true&useSSL=false"; //数据库url
            conn = DriverManager.getConnection(url, username, password);    //建立连接
            ps = conn.prepareStatement(sql);    //获取预编译语句
            ps.setString(1, title);  //设定title
            ps.setString(2, content);   //设定content
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将title标题和content文章内容加入到数据库中
    }
}



