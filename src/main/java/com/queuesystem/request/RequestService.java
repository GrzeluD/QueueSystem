package com.queuesystem.request;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Request> findRequestsByUserId(Integer userId) {
        return requestRepository.findByUserId(userId);
    }

    public List<Request> findOnlyRequests() { return findAllRequests().stream().filter(request -> !(request instanceof Order)).collect(Collectors.toList()); }

    public List<Order> findAllOrders() { return requestRepository.findAll().stream().filter(request -> request instanceof Order).map(request -> (Order) request).collect(Collectors.toList()); }

    public List<Request> findAllRequests() {
        return requestRepository.findAll();
    }
}
