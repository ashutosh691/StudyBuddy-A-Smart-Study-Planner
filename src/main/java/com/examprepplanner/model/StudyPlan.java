package com.examprepplanner.model;

import java.util.ArrayList;
import java.util.List;

public class StudyPlan {

    private int daysLeft;
    private int dailyHours;
    private String difficulty;
    private String pace;

    public StudyPlan(int daysLeft, int dailyHours, String difficulty, String pace) {
        this.daysLeft = daysLeft;
        this.dailyHours = dailyHours;
        this.difficulty = difficulty;
        this.pace = pace;
    }

    public List<String> generatePlan() {

        List<String> schedule = new ArrayList<>();

        if (daysLeft <= 0) {
            schedule.add("Exam date has already passed or is today.");
            return schedule;
        }

        int topicsPerDay;

        // Pace logic
        if ("fast".equalsIgnoreCase(pace)) {
            topicsPerDay = 3;
        } else if ("medium".equalsIgnoreCase(pace)) {
            topicsPerDay = 2;
        } else {
            topicsPerDay = 1;
        }

        // Difficulty adjustment
        if ("hard".equalsIgnoreCase(difficulty)) {
            topicsPerDay -= 1;
            if (topicsPerDay < 1) topicsPerDay = 1;
        }

        for (int i = 1; i <= daysLeft; i++) {

            if (i > daysLeft - 2) {
                schedule.add("Day " + i + ": Revision + Mock Test (" + dailyHours + " hrs)");
            } else {
                schedule.add("Day " + i + ": Study " + topicsPerDay +
                        " topics + Practice problems (" + dailyHours + " hrs)");
            }
        }

        return schedule;
    }
}
