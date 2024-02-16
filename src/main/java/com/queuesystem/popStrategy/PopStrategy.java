package com.queuesystem.popStrategy;
import com.queuesystem.messageParser.SuperComputerResources;
import com.queuesystem.queue.Task;

import java.util.List;

public interface PopStrategy {
    Task pop(List<Task> queue, SuperComputerResources resourceInfo);
}
