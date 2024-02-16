package com.queuesystem.queue;

import com.queuesystem.messageParser.MessageParser;
import com.queuesystem.messageParser.SuperComputerResources;
import com.queuesystem.popStrategy.PopStrategy;
import com.queuesystem.popStrategy.PopStrategyFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds record keys in sort of queue, from which
 * they are delivered to SuperComputer's interface.
 */
public class OrdersQueue
{
    private List<Task> queue;
    private MessageParser parser;

    public OrdersQueue()
    {
        this.queue = new ArrayList<>();
    }

    public void addTask(Task task)
    {
        addTask(task);
    }

    public Task pop(SuperComputerResources resourcesInfo)
    {
        PopStrategyFactory factory = selectFactory(resourcesInfo);
        PopStrategy strategy = factory.createStrategy();
        return strategy.pop(queue, resourcesInfo);
    }

    private PopStrategyFactory selectFactory(SuperComputerResources resourcesInfo) {
        // select factory based on resources available
        return null;
    }
}
