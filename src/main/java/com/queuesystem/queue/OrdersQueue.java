package com.queuesystem.queue;

import com.queuesystem.dbAdapter.DBAdapter;
import com.queuesystem.messageParser.Beholder;
import com.queuesystem.messageParser.MessageParser;
import com.queuesystem.messageParser.SuperComputerResources;
import com.queuesystem.popStrategy.PerPriorityPopFactory;
import com.queuesystem.popStrategy.PerResourcesPopFactory;
import com.queuesystem.popStrategy.PopStrategy;
import com.queuesystem.popStrategy.PopStrategyFactory;
import com.queuesystem.request.Order;
import com.queuesystem.resources.Resources;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds record keys in sort of queue, from which
 * they are delivered to SuperComputer's interface.
 */
@Service
@Component
@Getter
@Setter
public class OrdersQueue
{

    private List<Task> queue;

    private Beholder beholder;
    private final DBAdapter dbAdapter;

    public OrdersQueue(DBAdapter dbAdapter)
    {
        System.out.println("OrdersQueue Konstruktor");
        this.queue = new ArrayList<>();
        this.dbAdapter = dbAdapter;
    }

    public void setMediator(Beholder beholder) {
        this.beholder = beholder;
    }

    public void addToQueue(Order order)
    {
        Task task = new Task(order);
        System.out.println("Adding task: " + task.getId());
        queue.add(task);
    }

    public void pop(SuperComputerResources resourcesInfo) {
        System.out.println("Start pop");
        if (queue.isEmpty()) return;
        System.out.println("Queue not empty");
        Task task;
        Resources freeResources = resourcesInfo.getFreeResources();
        System.out.println("Free respurces: CPU=" + freeResources.getCpuCount() + " GPU=" + freeResources.getCpuCount() + " RAM=" + freeResources.getRamMegabytes());
        do {
            PopStrategyFactory factory = selectFactory(resourcesInfo);
            PopStrategy strategy = factory.createStrategy();
            task = strategy.pop(queue, freeResources);
            if (task != null) {
                System.out.println("Popping task: " + task.getId());
                beholder.requestTaskExecution(task);
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

    @PostConstruct
    private void initTasks() {
        List<Order> orders = dbAdapter.findAllOrders();
        orders.forEach(this::addToQueue);
    }
}
