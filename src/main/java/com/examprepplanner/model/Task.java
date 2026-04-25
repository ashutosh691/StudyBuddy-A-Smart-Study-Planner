package com.examprepplanner.model;

import java.time.LocalDate;

public class Task {

    private int id;          // Required for DB updates
    private int planId;      // Required for grouping tasks

    private String subject;
    private String topic;
    private LocalDate date;
    private String status;

    // Constructor used by Scheduler
    public Task(String subject, String topic, LocalDate date) {
        this.subject = subject;
        this.topic = topic;
        this.date = date;
        this.status = "PENDING";
    }

    // Full constructor for DB retrieval
    public Task(int id, int planId, String subject, String topic, LocalDate date, String status) {
        this.id = id;
        this.planId = planId;
        this.subject = subject;
        this.topic = topic;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getPlanId() {
        return planId;
    }

    public String getSubject() {
        return subject;
    }

    public String getTopic() {
        return topic;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
