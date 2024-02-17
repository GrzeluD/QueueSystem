package com.queuesystem.request;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<Request> findAllRequests() {
        return requestRepository.findAll();
    }
}
