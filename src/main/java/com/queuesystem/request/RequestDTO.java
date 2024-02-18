package com.queuesystem.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class RequestDTO {
    private final String filePath;
    private final String requestStatus;
    private final LocalDateTime requestedAt;
    private final Integer userId;
    private final Integer cpu;
    private final Integer gpu;
    private final Integer ram;
}
