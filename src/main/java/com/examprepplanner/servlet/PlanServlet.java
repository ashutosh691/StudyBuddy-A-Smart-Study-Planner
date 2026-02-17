package com.examprepplanner.servlet;
import java.util.List;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.examprepplanner.model.StudyPlan;

@WebServlet("/plan")
public class PlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String name = request.getParameter("name");
	    String subject = request.getParameter("subject");
	    String pace = request.getParameter("pace");
	    String difficulty = request.getParameter("difficulty");
	    int dailyHours = Integer.parseInt(request.getParameter("hours"));

	    LocalDate examDate = LocalDate.parse(request.getParameter("examDate"));
	    LocalDate today = LocalDate.now();

	    int daysLeft = (int) ChronoUnit.DAYS.between(today, examDate);

	    StudyPlan plan = new StudyPlan(daysLeft, dailyHours, difficulty, pace);

	    List<String> planList = plan.generatePlan();

	    request.setAttribute("name", name);
	    request.setAttribute("subject", subject);
	    request.setAttribute("daysLeft", daysLeft);
	    request.setAttribute("planList", planList);

	    request.getRequestDispatcher("result.jsp").forward(request, response);
	}

}
