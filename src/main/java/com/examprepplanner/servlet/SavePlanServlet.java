package com.examprepplanner.servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.examprepplanner.dao.PlanDAO;

@WebServlet("/savePlan")
public class SavePlanServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.getWriter().println("Please login first.");
            return;
        }

        int userId = (int) session.getAttribute("userId");

        String name = request.getParameter("name");
        String[] items = request.getParameterValues("planItem");

        List<String> planList = Arrays.asList(items);

        PlanDAO dao = new PlanDAO();
        dao.savePlanWithUser(name, planList, userId);

        response.sendRedirect("viewPlans");
    }
}