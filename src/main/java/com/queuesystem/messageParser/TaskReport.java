package com.queuesystem.messageParser;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskReport {
    private int taskId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String resultFolderPath;
    private LocalDateTime resultFolderRemovalDate;
}
