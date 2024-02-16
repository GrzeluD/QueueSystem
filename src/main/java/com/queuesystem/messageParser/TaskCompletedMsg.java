package com.queuesystem.messageParser;

import java.time.LocalDateTime;

public class TaskCompletedMsg implements Message {
    private int taskId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String resultFolderPath;
    private LocalDateTime resultFolderRemovalDate;
}
