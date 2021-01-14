package com.dao.homework.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can`t find MySQL Driver ", e);
        }
    }

    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static final String url = "jdbc:mysql://localhost:3306/"
            + "taxi_service?serverTimezone=UTC";

    public static Connection getConnection() {
        Properties dbProperties = new Properties();
        dbProperties.put("user", USER);
        dbProperties.put("password", PASSWORD);
        try {
            return DriverManager.getConnection(url, dbProperties);
        } catch (SQLException e) {
            throw new RuntimeException("Can`t established connection to DB ", e);
        }
    }
}
