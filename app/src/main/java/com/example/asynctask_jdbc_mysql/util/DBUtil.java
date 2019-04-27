package com.example.asynctask_jdbc_mysql.util;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by 蒲公英之流 on 2019-04-27.
 */

public class DBUtil {

    private static Connection conn;
    private static Statement st;
    private static String driver = "com.mysql.jdbc.Driver";
    //    private static String url = "jdbc:mysql://127.0.0.1:3306/db_0425_01";
    private static String url = "jdbc:mysql://10.6.194.113:3306/db_0425_01?useUnicode=true&characterEncoding=utf-8&useSSL=false&autoReconnect=true&failOverReadOnly=false&testOnBorrow=true&validationQuery=select 1";
    private static String usr = "root";

    //password for mysql
    private static String psw = "root";

    public static String find() {
        conn = getcon();
        String sql = "select * from t_0425_01 where name like '%a%'";
        try {
            st = conn.createStatement();
            ResultSet rt = st.executeQuery(sql);
            StringBuilder builder = new StringBuilder();
            while(rt.next()){
                String name = rt.getString("name");
                String birthaddr = rt.getString("birthaddr");
                builder.append(name+" "+birthaddr+"\n");
                Log.d("jdbc",name+" "+birthaddr);
            }
            conn.close();
            return builder.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Connection getcon() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e1) {
            Log.d("jdbc","加载驱动失败.");
            e1.printStackTrace();
        }
        Log.d("jdbc","MySQL JDBC Driver Registered!");

        try {
            conn = DriverManager.getConnection(url, usr, psw);
        } catch (SQLException e) {
            Log.d("jdbc","connection failed .");
            e.printStackTrace();
        }
        Log.d("jdbc","connected！");
        return conn;
    }

}
