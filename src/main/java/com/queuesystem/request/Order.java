package com.queuesystem.request;

import com.queuesystem.user.User;
import com.queuesystem.user.UserRole;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Order extends Request {

    private LocalDateTime approvedAt;
    private LocalDateTime executionStartedAt;
    private LocalDateTime executionEndedAt;
    private String logPath;

    public Order(String filePath,
                 String requestStatus,
                 LocalDateTime requestedAt,
                 Integer userId,
                 Integer priority,
                 LocalDateTime approvedAt,
                 LocalDateTime executionStartedAt,
                 LocalDateTime executionEndedAt,
                 String logPath) {
        super(filePath, requestStatus, requestedAt, userId, priority);
        this.approvedAt = approvedAt;
        this.executionStartedAt = executionStartedAt;
        this.executionEndedAt = executionEndedAt;
        this.logPath = logPath;
    }
}
