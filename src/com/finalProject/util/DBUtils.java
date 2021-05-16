package com.finalProject.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    //делает соеденение с базой данных
    //видео 15 смотри 1-40

    // открываем соединение
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/library?useSSL=false","root",
                    "sirin");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }

    // закрываем соединение

    public void CloseConnection(Connection connection){
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


}
// в случае если не заработает, то посмотреть видео 1.33 установка старого драйвера