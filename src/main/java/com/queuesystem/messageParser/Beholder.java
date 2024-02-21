package com.queuesystem.messageParser;

import com.queuesystem.queue.OrdersQueue;
import com.queuesystem.queue.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Beholder {
    private final OrdersQueue ordersQueue;
    private final MessageParser messageParser;

    @Autowired
    public Beholder(OrdersQueue ordersQueue, MessageParser messageParser) {
        this.ordersQueue = ordersQueue;
        this.messageParser = messageParser;
        ordersQueue.setMediator(this);
        messageParser.setMediator(this);
    }

    public void requestTaskExecution(Task task) {
        messageParser.requestTaskExecution(task);
    }

    public void popSuperComputerResources(SuperComputerResources superComputerResources) {
        ordersQueue.pop(superComputerResources);
    }
}
