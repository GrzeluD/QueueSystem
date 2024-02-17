package com.queuesystem.popStrategy;

import com.queuesystem.dbAdapter.DBAdapter;
import com.queuesystem.messageParser.Resource;
import com.queuesystem.queue.Task;
import java.util.List;

public class PerFreeResourcesPoppingStrategy implements PopStrategy {
    public PerFreeResourcesPoppingStrategy()
    {

    }

    public Task pop(List<Task> queue, Resource freeResources)
    {
        Task proposedTask = null;
        Resource proposedTaskResources = new Resource(0, 0, 0);
        for (Task task : queue) {
            Resource taskResources = DBAdapter.getRequiredResources(task);
            if (taskResources.fitIn(freeResources) && !taskResources.fitIn(proposedTaskResources)) {
                proposedTask = task;
            }
        }
        return queue.remove(queue.indexOf(proposedTask));
    }


}
