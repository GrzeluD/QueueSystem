package com.queuesystem.messageParser;

public class FreeResourceMessageObserver implements MessageObserver {
    @Override
    public void update(Message message) {
        if (message instanceof FreeResourcesReportMsg) {
            System.out.println("Processing free resources report...");
        }
    }
}
