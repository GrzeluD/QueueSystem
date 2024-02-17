package com.queuesystem.dbAdapter;

import com.queuesystem.messageParser.Resource;
import com.queuesystem.messageParser.TaskReport;
import com.queuesystem.queue.Task;
import com.queuesystem.request.Order;
import org.springframework.stereotype.Service;

@Service
public class DBAdapter {

    public static Resource getTotalResources() {
        return new Resource(1000, 1000, 4194304);
    }

    public static boolean updateFinishedOrder(TaskReport report) {
        //return false only if updating record in DB failed, then
        //report should land in waiting queue and system tries to re-write it
        //upon successful storing of next incoming report.
        return false;
    }

    public static Resource getRequiredResources(Task task) {
        int requestId = task.getId();
        //search in DB and get required cpu, gpu, ram
        // return new Resource(cpu, gpu, ram);
        return new Resource (10, 2, 256);
    }

    public static Order getOrderForTask(Task task) {
        return new Order();
    }
}
