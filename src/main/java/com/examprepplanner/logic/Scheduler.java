package com.examprepplanner.logic;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Scheduler {

    // Inner class to store subject data
    static class Subject {
        String name;
        List<String> topics;
        LocalDate examDate;
        String difficulty;

        Subject(String name, String topicsStr, LocalDate examDate, String difficulty) {
            this.name = name;
            this.topics = Arrays.asList(topicsStr.split(","));
            this.examDate = examDate;
            this.difficulty = difficulty;
        }
    }

    private List<Subject> subjects = new ArrayList<>();

    // ✅ ADD SUBJECT METHOD (FIXED)
    public void addSubject(String name, String topics, LocalDate examDate, String difficulty) {
        subjects.add(new Subject(name, topics, examDate, difficulty));
    }

    // ✅ GENERATE PLAN METHOD (FIXED)
    public List<String> generatePlan() {

        List<String> result = new ArrayList<>();
        LocalDate today = LocalDate.now();

        // Sort subjects by nearest exam first
        subjects.sort(Comparator.comparing(s -> s.examDate));

        int day = 1;

        for (Subject s : subjects) {

            long daysLeft = ChronoUnit.DAYS.between(today, s.examDate);
            if (daysLeft <= 0) daysLeft = 1;

            int topicsPerDay = Math.max(1, s.topics.size() / (int) daysLeft);

            int index = 0;

            for (int d = 0; d < daysLeft && index < s.topics.size(); d++) {

                StringBuilder sb = new StringBuilder();
                sb.append("Day ").append(day++).append(": ");

                int count = 0;

                while (count < topicsPerDay && index < s.topics.size()) {
                    sb.append(s.name).append(" → ").append(s.topics.get(index).trim());

                    index++;
                    count++;

                    if (count < topicsPerDay && index < s.topics.size()) {
                        sb.append(" | ");
                    }
                }

                result.add(sb.toString());
            }
        }

        return result;
    }
}