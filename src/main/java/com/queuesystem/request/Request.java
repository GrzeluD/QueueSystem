package com.queuesystem.request;

import com.queuesystem.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "requests")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Request {

    @SequenceGenerator(
            name = "request_sequence",
            sequenceName = "request_sequence_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "request_sequence_sequence"
    )

    private Integer requestId;
    private String filePath;
    private String requestStatus;
    private LocalDateTime requestedAt;
    private Integer userId;
    private Integer priority;
    private Integer cpu;
    private Integer gpu;
    private Integer ram;
    public Request(String filePath,
                   String requestStatus,
                   LocalDateTime requestedAt,
                   Integer userId,
                   Integer priority,
                   Integer cpu,
                   Integer gpu,
                   Integer ram) {
        this.filePath = filePath;
        this.requestStatus = requestStatus;
        this.requestedAt = requestedAt;
        this.userId = userId;
        this.priority = priority;
        this.cpu = cpu;
        this.gpu = gpu;
        this.ram = ram;
    }
}
