package com.examprepplanner.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/examprepplanner";
    private static final String USER = "root";
    private static final String PASSWORD = "Ashutosh280705"; 

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database Connected Successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection Failed.");
            e.printStackTrace();
        }
        return connection;
    }
    
    public static void main(String[] args) {
        getConnection();
    }
}