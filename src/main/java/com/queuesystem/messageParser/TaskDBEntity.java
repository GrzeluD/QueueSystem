package com.queuesystem.messageParser;
import com.queuesystem.queue.Task;
public class TaskDBEntity {
    private int taskID;
    private String filesPath;
    private String programName;
    private Resource requestedResources;
    /**
     * This method collects from DB information on Task required by SuperComputer
     * @param task Task as it comes from task queue
     */
    public TaskDBEntity(Task task) {

    }
}
