package com.queuesystem.popStrategy;
import com.queuesystem.messageParser.Resource;
import com.queuesystem.queue.Task;

import java.util.List;

public interface PopStrategy {
    Task pop(List<Task> queue, Resource freeResources);
}
