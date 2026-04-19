package com.examprepplanner.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/examprepplanner"
            + "?useUnicode=true"
            + "&characterEncoding=UTF-8"
            + "&connectionCollation=utf8mb4_unicode_ci"
            + "&serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "Ashutosh280705";

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found.", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}