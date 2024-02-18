package com.queuesystem.user;

import com.queuesystem.dbAdapter.DBAdapter;
import com.queuesystem.request.Request;
import com.queuesystem.request.RequestService;
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
public class CustomerController {
    private final RequestService requestService;
    private final Customer customer;
    private DBAdapter dbAdapter;

    @PostMapping("/create")
    public String createRequest(@ModelAttribute Request request) {
        Integer userId = getCurrentUserId();

        Request newRequest = customer.createRequest();

        if (userId != null) {
            newRequest.setUserId(userId);
        }

        newRequest.setFilePath(request.getFilePath());
        newRequest.setRequestedAt(LocalDateTime.now());
        newRequest.setRequestStatus("New");
        newRequest.setPriority(request.getPriority() != null ? request.getPriority() : 4);
        newRequest.setCpu(request.getCpu() != null ? request.getCpu() : 1);
        newRequest.setGpu(request.getGpu() != null ? request.getGpu() : 0);
        newRequest.setRam(request.getRam() != null ? request.getRam() : 512);

        dbAdapter.saveRequest(newRequest);
        return "redirect:/customer-list";
    }
}
