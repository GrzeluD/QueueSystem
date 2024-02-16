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


    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private String requestStatus;

    @Column(nullable = false)
    private LocalDateTime requestedAt;

    @Column(nullable = false)
    private Integer userId;

    private Integer priority;
    public Request(String filePath,
                 String requestStatus,
                 LocalDateTime requestedAt,
                 Integer userId,
                 Integer priority) {
        this.filePath = filePath;
        this.requestStatus = requestStatus;
        this.requestedAt = requestedAt;
        this.userId = userId;
        this.priority = priority;
    }
}
