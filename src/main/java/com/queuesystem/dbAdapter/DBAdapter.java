package com.queuesystem.dbAdapter;

import com.queuesystem.messageParser.Resource;
import com.queuesystem.messageParser.TaskReport;
import org.springframework.stereotype.Service;

@Service
public class DBAdapter {

    public Resource getTotalResources() {
        return new Resource(1000, 1000, 4194304);
    }

    public boolean updateFinishedOrder(TaskReport report) {
        return false;
    }
}
