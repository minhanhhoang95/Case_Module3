package com.codegym.wine_manager.connect_MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMySQL {
    private String jdbcURL = "jdbc:mysql://localhost:3306/case_m3?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "230795";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
