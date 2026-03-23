package com.examprepplanner.dao;

import java.sql.*;
import java.util.List;

import com.examprepplanner.database.DBConnection;

public class PlanDAO {

	public void savePlanWithUser(String name, List<String> planList, int userId) {

	    try (Connection con = DBConnection.getConnection()) {

	        String planQuery = "INSERT INTO study_plan(name, created_date, user_id) VALUES (?, ?, ?)";
	        PreparedStatement stmt = con.prepareStatement(planQuery, Statement.RETURN_GENERATED_KEYS);

	        stmt.setString(1, name);
	        stmt.setDate(2, Date.valueOf(java.time.LocalDate.now()));
	        stmt.setInt(3, userId);

	        stmt.executeUpdate();

	        ResultSet rs = stmt.getGeneratedKeys();
	        int planId = 0;

	        if (rs.next()) {
	            planId = rs.getInt(1);
	        }

	        String detailQuery = "INSERT INTO plan_details(plan_id, day_number, content) VALUES (?, ?, ?)";
	        PreparedStatement detailStmt = con.prepareStatement(detailQuery);

	        int day = 1;
	        for (String item : planList) {
	            detailStmt.setInt(1, planId);
	            detailStmt.setInt(2, day++);
	            detailStmt.setString(3, item);
	            detailStmt.executeUpdate();
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}