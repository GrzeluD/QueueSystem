package com.queuesystem.queue;

import com.queuesystem.dbAdapter.DBAdapter;
import com.queuesystem.messageParser.MessageParser;
import com.queuesystem.messageParser.SuperComputerResources;
import com.queuesystem.messageParser.Resource;
import com.queuesystem.popStrategy.PerPriorityPopFactory;
import com.queuesystem.popStrategy.PerResourcesPopFactory;
import com.queuesystem.popStrategy.PopStrategy;
import com.queuesystem.popStrategy.PopStrategyFactory;
import com.queuesystem.request.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds record keys in sort of queue, from which
 * they are delivered to SuperComputer's interface.
 */
@Service
public class OrdersQueue
{

    private final List<Task> queue;
    private final MessageParser parser;

    public OrdersQueue(MessageParser parser)
    {
        this.queue = new ArrayList<>();
        this.parser = parser;
    }

    public void addToQueue(Order order)
    {
        Task task = new Task(order);
        queue.add(task);
    }

    public void pop(SuperComputerResources resourcesInfo)
    {
        Task task;
        Resource freeResources = resourcesInfo.getFreeResources();
        do {
            PopStrategyFactory factory = selectFactory(resourcesInfo);
            PopStrategy strategy = factory.createStrategy();
            task = strategy.pop(queue, freeResources);
            if (task != null) {
                parser.requestTaskExecution(task);
                freeResources = resourcesInfo.getFreeResources();
                freeResources.reduceBy(DBAdapter.getRequiredResources(task));
                resourcesInfo.setFreeResources(freeResources);
            }
        } while (task != null);
    }


    private PopStrategyFactory selectFactory(SuperComputerResources resourcesInfo) {
        int cpuFreePercent = resourcesInfo.getFreeResources().getCpuCount() / resourcesInfo.getTotalResources().getCpuCount();
        int gpuFreePercent = resourcesInfo.getFreeResources().getGpuCount() / resourcesInfo.getTotalResources().getGpuCount();
        int ramFreePercent = resourcesInfo.getFreeResources().getRamMegabytes() / resourcesInfo.getTotalResources().getRamMegabytes();
        int freePercentage = resourcesInfo.getFreeResources().getResourceWeight() / resourcesInfo.getTotalResources().getResourceWeight();
        if (freePercentage < 0.1 ||
                cpuFreePercent < 0.01 ||
                gpuFreePercent < 0.01 ||
                ramFreePercent < 0.05) return new PerResourcesPopFactory();
        return new PerPriorityPopFactory();
    }
}
