package com.examprepplanner.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.examprepplanner.database.DBConnection;

@WebServlet("/updateTask")
public class UpdateTaskServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.getWriter().println("Please login first.");
            return;
        }

        String[] completedTasks = request.getParameterValues("task");

        if (completedTasks == null) {
            response.sendRedirect("viewPlans");
            return;
        }

        try {
            Connection con = DBConnection.getConnection();

            // Correct query :ID based
            PreparedStatement ps = con.prepareStatement(
                "UPDATE tasks SET status='COMPLETED' WHERE id=?"
            );

            for (String id : completedTasks) {

                int taskId = Integer.parseInt(id);

                ps.setInt(1, taskId);
                ps.executeUpdate();
            }

            con.close();

            // Redirect back to plans
            response.sendRedirect("viewPlans");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error updating tasks.");
        }
    }
}
