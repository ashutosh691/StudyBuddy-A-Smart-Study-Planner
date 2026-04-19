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

    // Plan model (with getters for JSP)
    public static class Plan {
        private int id;
        private String name;
        private int total;
        private int completed;
        private int percent;

        public Plan(int id, String name, int total, int completed) {
            this.id = id;
            this.name = name;
            this.total = total;
            this.completed = completed;
            this.percent = (total == 0) ? 0 : (completed * 100) / total;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public int getTotal() { return total; }
        public int getCompleted() { return completed; }
        public int getPercent() { return percent; }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.html");
            return;
        }

        int userId = (int) session.getAttribute("userId");

        List<Plan> plans = new ArrayList<>();

        String query =
            "SELECT p.id, p.plan_name, " +
            "COUNT(t.id) AS total, " +
            "SUM(CASE WHEN t.status='COMPLETED' THEN 1 ELSE 0 END) AS completed " +
            "FROM plans p " +
            "LEFT JOIN tasks t ON p.id = t.plan_id " +
            "WHERE p.user_id = ? " +
            "GROUP BY p.id, p.plan_name " +
            "ORDER BY p.id DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("plan_name");
                int total = rs.getInt("total");
                int completed = rs.getInt("completed");

                plans.add(new Plan(id, name, total, completed));
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error loading plans.");
        }

        // Send to your existing JSP
        request.setAttribute("plans", plans);
        request.getRequestDispatcher("viewPlans.jsp").forward(request, response);
    }
}