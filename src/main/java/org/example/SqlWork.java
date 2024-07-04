package org.example;

import java.sql.*;

public class SqlWork {
    public String content;
    public boolean check(String Title) {
        boolean is_find = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * from paraInfo";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Spider?autoReconnect=true&useSSL=false";
            String username = "root";
            String password = "20041015liu";
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
                if (title.equals(Title)) {
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
    public void insert(String title, String content) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "INSERT into paraInfo(title,content) values(?,?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Spider?autoReconnect=true&useSSL=false";
            String username = "root";
            String password = "20041015liu";
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, content);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



