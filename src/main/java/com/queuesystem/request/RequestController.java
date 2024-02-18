package com.queuesystem.request;

import com.queuesystem.registration.RegistrationRequest;
import com.queuesystem.user.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.queuesystem.security.SecurityUtils.getCurrentUserId;

@Controller
@AllArgsConstructor
@RequestMapping("/api/request")
public class RequestController {

    private final RequestService requestService;

    @PostMapping
    public String createRequest(@ModelAttribute Request request) {
        Integer userId = getCurrentUserId();

        if (userId != null) {
            request.setUserId(userId);
        }

        if (request.getRequestedAt() == null) {
            request.setRequestedAt(LocalDateTime.now());
        }

        if (request.getRequestStatus() == null) {
            request.setRequestStatus("New");
        }

        if (request.getPriority() == null) {
            request.setPriority(4);
        }

        if (request.getCpu() == null) {
            request.setCpu(1);
        }

        if (request.getGpu() == null) {
            request.setGpu(0);
        }

        if (request.getRam() == null) {
            request.setRam(512);
        }

        requestService.createRequest(request);
        return "redirect:/customer-list";
    }
}
