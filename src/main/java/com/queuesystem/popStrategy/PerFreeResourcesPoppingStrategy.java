package com.queuesystem.popStrategy;

import com.queuesystem.dbAdapter.DBAdapter;
import com.queuesystem.queue.Task;
import com.queuesystem.resources.Resources;

import java.util.List;

public class PerFreeResourcesPoppingStrategy implements PopStrategy {
    public PerFreeResourcesPoppingStrategy() {}

    public Task pop(List<Task> queue, Resources freeResources)
    {
        Task proposedTask = null;
        Resources proposedTaskResources = new Resources(0, 0, 0);
        for (Task task : queue) {
            Resources taskResources = DBAdapter.getRequiredResources(task);
            if (taskResources.fitIn(freeResources) && !taskResources.fitIn(proposedTaskResources)) {
                proposedTask = task;
            }
        }
        if (proposedTask != null) {
            System.out.println("PerFreeResourcesPoppingStrategy Popped task: " + proposedTask.getId());
        } else {
            System.out.println("PerFreeResourcesPoppingStrategy End of Task, didnt pop: ");
        }
        return queue.remove(queue.indexOf(proposedTask));
    }
}
