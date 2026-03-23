package com.examprepplanner.servlet;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.examprepplanner.database.DBConnection;

@WebServlet("/viewPlans")
public class ViewPlansServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 🔐 SESSION CHECK
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.html");
            return;
        }

        int userId = (int) session.getAttribute("userId");

        // 📦 Data structure: planId → list of day plans
        Map<Integer, List<String>> plans = new LinkedHashMap<>();

        try (Connection con = DBConnection.getConnection()) {

            String query = "SELECT pd.plan_id, pd.day_number, pd.content " +
                           "FROM plan_details pd " +
                           "JOIN study_plan sp ON pd.plan_id = sp.id " +
                           "WHERE sp.user_id = ? " +
                           "ORDER BY pd.plan_id, pd.day_number";

            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int planId = rs.getInt("plan_id");
                String content = rs.getString("content");

                // Group by planId
                plans.computeIfAbsent(planId, k -> new ArrayList<>()).add(content);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 📤 Send to JSP
        request.setAttribute("plans", plans);

        request.getRequestDispatcher("viewPlans.jsp").forward(request, response);
    }
}