package com.queuesystem.queue;

import com.queuesystem.request.Order;

public class Task {
    private final int id;
    private int priority;
    public Task(int id, int priority) {
        this.id = id;
        this.priority = priority;
    }
    public Task(Order order) {
        this.id = order.getRequestId();
        this.priority = order.getPriority();
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

    /**
     * this method gets complete order info from database based on TaskID
     * @return Order matching ID in task
     */
    public Order getOrderFromDB(/*DBConnection dbConnection OR DBAdapter bdAdapter*/) {
        return null;
    }
}
