package com.examprepplanner.model;

import java.time.LocalDate;
import java.util.List;

public class Subject {

    private String name;
    private LocalDate examDate;
    private List<String> topics;
    private String difficulty;

    public Subject(String name, LocalDate examDate, List<String> topics, String difficulty) {
        this.name = name;
        this.examDate = examDate;
        this.topics = topics;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public List<String> getTopics() {
        return topics;
    }

    public String getDifficulty() {
        return difficulty;
    }
}