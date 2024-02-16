package com.queuesystem.queue;

public class Task {
    private final int id;
    private int priority;
    public Task(int id, int priority) {
        this.id = id;
        this.priority = priority;
    }
    public void changePriority(int priority) {
        this.priority = priority;
    }
    public int getPriority() {
        return priority;
    }
    public int getId() {
        return id;
    }
}
