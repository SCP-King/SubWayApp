package com.example.subway.utils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class SqlUtils {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static int row;
    private static ResultSet resultSet;

    public static void getConnection(){
        try {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    driver = "com.mysql.jdbc.Driver";
                    url = "jdbc:mysql://192.168.15.1:3307/subway";
                    username = "bill";
                    password = "123456";
                    try {
                        Class.forName(driver);
                        connection = DriverManager.getConnection(url, username, password);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            thread.join();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static int update(String sql,Object[] s){
        row=0;
        try {
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    if(s==null){
                        row=preparedStatement.executeUpdate();
                        return;
                    }
                    for (int i = 0; i < s.length; i++){
                        if(s[i]==null){
                            row=0;
                            return;
                        }
                        preparedStatement.setObject(i+1,s[i]);
                    }
                    row= preparedStatement.executeUpdate();
                    return;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            thread.join();
        }catch (Exception e){
            e.printStackTrace();
        }
        return row;
    }
    public static ResultSet query(String sql,Object s[]){
       resultSet=null;
        try {
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        if(s==null){
                           resultSet = preparedStatement.executeQuery();
                            return;
                        }
                        for (int i = 0; i < s.length; i++){
                            if(s[i]==null){
                                resultSet =null;
                                return;
                            }
                            preparedStatement.setObject(i+1,s[i]);
                        }
                        resultSet = preparedStatement.executeQuery();
                        return;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            thread.join();
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultSet;
    }
    public static void closeConnection(){
        if(true) return;
        try {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
