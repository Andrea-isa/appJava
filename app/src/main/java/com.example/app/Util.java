package com.example.app;

import java.sql.*;

public class Util {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/hola_mundo_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    public static void closeConnection(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}
