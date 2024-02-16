package com.queuesystem.messageParser;

public class TaskCompletedObserver implements MessageObserver {
    @Override
    public void update(Message message) {
        if (message instanceof TaskCompletedMsg) {
            System.out.println("Processing task completion...");
        }
    }
}
