package com.examprepplanner.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.examprepplanner.logic.Scheduler;

@WebServlet("/plan")
public class PlanServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 🔐 SESSION CHECK (IMPORTANT)
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.getWriter().println("Please login first.");
            return;
        }

//        int userId = (int) session.getAttribute("userId");

        // 👤 USER INPUT
        String name = (String) session.getAttribute("username");
        int subjectCount = Integer.parseInt(request.getParameter("subjectCount"));

        // 🧠 SCHEDULER OBJECT
        Scheduler scheduler = new Scheduler();

        // 📚 COLLECT MULTIPLE SUBJECT DATA
        for (int i = 1; i <= subjectCount; i++) {

            String subject = request.getParameter("sub" + i);
            String topics = request.getParameter("topics" + i);
            String difficulty = request.getParameter("diff" + i);
            String dateStr = request.getParameter("date" + i);

            // ⚠️ NULL CHECK (prevents crash)
            if (subject == null || dateStr == null || topics == null) continue;

            LocalDate examDate = LocalDate.parse(dateStr);

            scheduler.addSubject(subject, topics, examDate, difficulty);
        }

        // 🚀 GENERATE PLAN
        List<String> planList = scheduler.generatePlan();

        // 📤 SEND TO JSP
        request.setAttribute("name", name);
        request.setAttribute("planList", planList);

        

        // 🔄 FORWARD TO RESULT PAGE
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}