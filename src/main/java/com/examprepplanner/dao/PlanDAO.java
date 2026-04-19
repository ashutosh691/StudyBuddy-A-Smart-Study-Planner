package com.examprepplanner.dao;

import java.sql.*;
import java.util.List;

import com.examprepplanner.database.DBConnection;

public class PlanDAO {

    public void savePlanWithUser(String name, List<String> planList, int userId) {

        String planQuery = "INSERT INTO study_plan(name, created_date, user_id) VALUES (?, ?, ?)";
        String detailQuery = "INSERT INTO plan_details(plan_id, day_number, content) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(planQuery, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement detailStmt = con.prepareStatement(detailQuery)) {

            // Ensure connection uses UTF-8 (extra safety)
            try (Statement s = con.createStatement()) {
                s.execute("SET NAMES utf8mb4");
            }

            // Insert main plan
            stmt.setString(1, name);
            stmt.setDate(2, Date.valueOf(java.time.LocalDate.now()));
            stmt.setInt(3, userId);
            stmt.executeUpdate();

            // Get generated plan_id
            int planId = 0;
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    planId = rs.getInt(1);
                }
            }

            // Insert plan details
            int day = 1;
            for (String item : planList) {
                detailStmt.setInt(1, planId);
                detailStmt.setInt(2, day++);
                detailStmt.setString(3, item); // ← encoding depends on connection
                detailStmt.addBatch();
            }

            detailStmt.executeBatch(); // more efficient

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}