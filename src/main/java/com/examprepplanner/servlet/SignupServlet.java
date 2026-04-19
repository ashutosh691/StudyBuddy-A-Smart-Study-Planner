package com.examprepplanner.servlet;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.examprepplanner.database.DBConnection;

@SuppressWarnings("serial")
@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection con = DBConnection.getConnection()) {

            // Check if user exists
            String checkQuery = "SELECT * FROM users WHERE username=?";
            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setString(1, username);

            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // User already exists
                response.sendRedirect("signup.html?error=exists");
                return;
            }

            // Insert new user
            String sql = "INSERT INTO users(username, password) VALUES (?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, username);
            stmt.setString(2, password);

            stmt.executeUpdate();

            response.sendRedirect("login.html?success=1");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
