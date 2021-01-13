package com.dao.homework.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can`t find MySQL Driver ", e);
        }
    }

    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() {
        Properties dbProperties = new Properties();
        dbProperties.put("user", USER);
        dbProperties.put("password", PASSWORD);

        String url = "jdbc:mysql://localhost:3306/"
                + "taxi_service?UTC";
        try {
            return DriverManager.getConnection(url, dbProperties);
        } catch (SQLException e) {
            throw new RuntimeException("Can`t established connection to DB ", e);
        }
    }
}
