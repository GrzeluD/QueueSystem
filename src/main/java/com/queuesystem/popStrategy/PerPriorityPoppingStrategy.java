package com.queuesystem.popStrategy;

import com.queuesystem.dbAdapter.DBAdapter;
import com.queuesystem.messageParser.Resource;
import com.queuesystem.queue.Task;

import java.util.ArrayList;
import java.util.List;

public class PerPriorityPoppingStrategy implements PopStrategy {
    public PerPriorityPoppingStrategy() {

    }
    public Task pop(List<Task> queue, Resource freeResources) {
        List<Task> prioQueue;
        int priority = 1;
        while(priority < 5) {
            prioQueue = filterTasksByPriority(queue, priority);
            if (prioQueue.isEmpty()) continue;
            for (Task task : prioQueue) {
                if (DBAdapter.getRequiredResources(task).fitIn(freeResources)) {
                    return queue.remove(queue.indexOf(task));
                }
            }
            ++priority;
        }
        return null;
    }

    private List<Task> filterTasksByPriority(List<Task> queue, int priority) {
        ArrayList<Task> prioQueue = new ArrayList<>();
        for (Task task : queue) {
            if (task.getPriority() == priority) prioQueue.add(task);
        }
        return prioQueue;
    }
}
