package com.queuesystem.request;

import com.queuesystem.registration.RegistrationRequest;
import com.queuesystem.user.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("/api/request")
public class RequestController {

    private final RequestRepository requestRepository;
    private final RequestService requestService;

    @PostMapping
    public Request createRequest(@RequestBody Request request) {
        if (request.getRequestedAt() == null) {
            request.setRequestedAt(LocalDateTime.now());
        }

        if (request.getPriority() == null) {
            request.setPriority(4);
        }

        requestService.createRequest(request);
        return request;
    }
}
