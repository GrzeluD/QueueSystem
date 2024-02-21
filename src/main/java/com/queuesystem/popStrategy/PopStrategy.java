package com.queuesystem.popStrategy;
import com.queuesystem.queue.Task;
import com.queuesystem.resources.Resources;

import java.util.List;

public interface PopStrategy {
    Task pop(List<Task> queue, Resources freeResources);
}
