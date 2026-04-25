package com.examprepplanner.servlet;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.examprepplanner.database.DBConnection;
import com.examprepplanner.model.Task;

@WebServlet("/viewTasks")
public class ViewTasksServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String planIdStr = request.getParameter("planId");

        if (planIdStr == null) {
            response.sendRedirect("viewPlans");
            return;
        }

        int planId = Integer.parseInt(planIdStr);

        List<Task> tasks = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "SELECT * FROM tasks WHERE plan_id=? ORDER BY task_date"
             )) {

            ps.setInt(1, planId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Task t = new Task(
                        rs.getInt("id"),
                        rs.getInt("plan_id"),
                        rs.getString("subject"),
                        rs.getString("topic"),
                        rs.getDate("task_date").toLocalDate(),
                        rs.getString("status")
                );

                tasks.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("tasks", tasks);

        // reuse your existing page
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}
