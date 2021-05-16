package com.finalProject.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceUtil {


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
