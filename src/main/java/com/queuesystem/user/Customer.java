package com.queuesystem.user;

import com.queuesystem.dbAdapter.DBAdapter;
import com.queuesystem.request.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

import static com.queuesystem.security.SecurityUtils.getCurrentUserId;

@RequestMapping("/api/request")
@Getter
@Setter
@AllArgsConstructor
@Controller
public class Customer {
    private final CustomerEntity customerEntity;
    private final DBAdapter dbAdapter;
    private final Administrator administrator;

    @PostMapping("/create")
    public String createRequest(@ModelAttribute Request request) {
        Integer userId = getCurrentUserId();

        if (userId != null) {
            request.setUserId(userId);
        }

        request.setRequestStatus("New");
        request.setRequestedAt(LocalDateTime.now());
        request.setPriority(request.getPriority() != null ? request.getPriority() : 4);
        request.setCpu(request.getCpu() != null ? request.getCpu() : 1);
        request.setGpu(request.getGpu() != null ? request.getGpu() : 0);
        request.setRam(request.getRam() != null ? request.getRam() : 512);

        Administrator.addRequest(request);
        dbAdapter.saveRequest(request);
        return "redirect:/customer-list";
    }
}
