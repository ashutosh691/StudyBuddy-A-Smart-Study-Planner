package com.examprepplanner.servlet;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.examprepplanner.logic.Scheduler;
import com.examprepplanner.model.Task;
import com.examprepplanner.database.DBConnection;

@WebServlet("/plan")
public class PlanServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.getWriter().println("Please login first.");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        int subjectCount = Integer.parseInt(request.getParameter("subjectCount"));

        Scheduler scheduler = new Scheduler();

        // 🔹 Collect subjects
        for (int i = 1; i <= subjectCount; i++) {

            String subject = request.getParameter("sub" + i);
            String topics = request.getParameter("topics" + i);
            String difficulty = request.getParameter("diff" + i);
            String dateStr = request.getParameter("date" + i);

            if (subject == null || dateStr == null || topics == null) continue;

            LocalDate examDate = LocalDate.parse(dateStr);
            scheduler.addSubject(subject, topics, examDate, difficulty);
        }

        // 🔹 Generate tasks
        List<Task> tasks = scheduler.generatePlan();

        try {
            Connection con = DBConnection.getConnection();

            // 🔥 Step 1: Insert Plan
            PreparedStatement psPlan = con.prepareStatement(
                "INSERT INTO plans(user_id, plan_name) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            psPlan.setInt(1, userId);
            psPlan.setString(2, "My Plan"); // can be dynamic later
            psPlan.executeUpdate();

            ResultSet rs = psPlan.getGeneratedKeys();

            int planId = 0;
            if (rs.next()) {
                planId = rs.getInt(1);
            }

            // Step 2: Insert Tasks
            PreparedStatement psTask = con.prepareStatement(
                "INSERT INTO tasks(plan_id, subject, topic, task_date, status) VALUES (?, ?, ?, ?, ?)"
            );

            for (Task t : tasks) {
                psTask.setInt(1, planId);
                psTask.setString(2, t.getSubject());
                psTask.setString(3, t.getTopic());
                psTask.setDate(4, java.sql.Date.valueOf(t.getDate()));
                psTask.setString(5, "PENDING");
                psTask.executeUpdate();
            }

            con.close();

            // Redirect to plans dashboard
            response.sendRedirect("viewPlans");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error saving plan.");
        }
    }
}