package com.examprepplanner.logic;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import com.examprepplanner.model.Task;

public class Scheduler {

    // Inner class
    static class Subject {
        String name;
        List<String> topics;
        LocalDate examDate;
        String difficulty;

        Subject(String name, String topicsStr, LocalDate examDate, String difficulty) {
            this.name = name;
            this.examDate = examDate;
            this.difficulty = (difficulty == null) ? "easy" : difficulty;

            // ✅ Clean + safe topic parsing
            this.topics = new ArrayList<>();
            for (String t : topicsStr.split(",")) {
                String cleaned = t.trim();
                if (!cleaned.isEmpty()) {
                    this.topics.add(cleaned);
                }
            }
        }
    }

    private List<Subject> subjects = new ArrayList<>();

    public void addSubject(String name, String topics, LocalDate examDate, String difficulty) {
        subjects.add(new Subject(name, topics, examDate, difficulty));
    }

    public List<Task> generatePlan() {

        List<Task> tasks = new ArrayList<>();
        LocalDate today = LocalDate.now();

        Map<String, Integer> diffWeight = Map.of(
                "easy", 1,
                "medium", 2,
                "hard", 3
        );

        int day = 0;

        while (true) {

            boolean workLeft = false;
            LocalDate currentDate = today.plusDays(day);

            // ✅ Stop if all exams are passed
            boolean allExpired = true;
            for (Subject s : subjects) {
                if (!s.examDate.isBefore(currentDate)) {
                    allExpired = false;
                    break;
                }
            }
            if (allExpired) break;

            // Priority Queue
            PriorityQueue<Subject> pq = new PriorityQueue<>((a, b) -> {
                long da = Math.max(1, ChronoUnit.DAYS.between(currentDate, a.examDate));
                long db = Math.max(1, ChronoUnit.DAYS.between(currentDate, b.examDate));

                double pa = diffWeight.getOrDefault(a.difficulty.toLowerCase(), 1) / (double) da;
                double pb = diffWeight.getOrDefault(b.difficulty.toLowerCase(), 1) / (double) db;

                return Double.compare(pb, pa);
            });

            for (Subject s : subjects) {
                if (!s.topics.isEmpty() && !s.examDate.isBefore(currentDate)) {
                    pq.offer(s);
                    workLeft = true;
                }
            }

            if (!workLeft) break;

            int used = 0;
            int MAX_DAILY_CAP = 6;

            while (!pq.isEmpty() && used < MAX_DAILY_CAP) {

                Subject s = pq.poll();

                int remainingTopics = s.topics.size();
                int remainingDays = (int) ChronoUnit.DAYS.between(currentDate, s.examDate);
                remainingDays = Math.max(1, remainingDays);

                int quota = (int) Math.ceil((double) remainingTopics / remainingDays);

                for (int i = 0; i < quota && !s.topics.isEmpty() && used < MAX_DAILY_CAP; i++) {

                    String topic = s.topics.remove(0);

                    // ✅ Task creation
                    tasks.add(new Task(s.name, topic, currentDate));

                    used++;
                }
            }

            day++;
        }

        return tasks;
    }
}