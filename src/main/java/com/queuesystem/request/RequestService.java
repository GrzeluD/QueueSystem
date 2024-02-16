package com.queuesystem.request;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;

    public Request createRequest(Request request) {
        if(request.getRequestedAt() == null) {
            request.setRequestedAt(LocalDateTime.now());
        }

        return requestRepository.save(request);
    }
}
