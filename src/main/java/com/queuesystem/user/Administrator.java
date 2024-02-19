package com.queuesystem.user;

import com.queuesystem.dbAdapter.DBAdapter;
import com.queuesystem.queue.OrdersQueue;
import com.queuesystem.request.Order;
import com.queuesystem.request.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/request")
@Getter
@Setter
@AllArgsConstructor
@Controller
public class Administrator {

    private static List<Request> allRequests = new ArrayList<>();
    private final AdministratorEntity administratorEntity;
    private final DBAdapter dbAdapter;
    private final OrdersQueue ordersQueue;

    @RequestMapping("/reject/{requestId}")
    public String rejectRequest(@PathVariable("requestId") Integer requestId ) {
        Request request = dbAdapter.findRequestById(requestId);

        request.setRequestStatus("Rejected");
        request.setRejectedAt(LocalDateTime.now());

        dbAdapter.saveRequest(request);
        return "redirect:/console";
    }

    @RequestMapping("/approve")
    public String approveRequest(@ModelAttribute Request request, RedirectAttributes redirectAttributes) {
        Order order = new Order(request.getFilePath(),
                "Approved",
                request.getRequestedAt(),
                request.getRejectedAt(),
                request.getUserId(),
                request.getPriority(),
                request.getCpu(),
                request.getGpu(),
                request.getRam(),
                LocalDateTime.now(), null, null, null);


        try {
            redirectAttributes.addFlashAttribute("successMessage", "Request zaakceptowany");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Coś poszło nie tak: " + e.getMessage());
        }

        dbAdapter.saveOrder(order);

        ordersQueue.addToQueue(order);
        return "redirect:/console";
    }

    public static void addRequest(Request request) {
        allRequests.add(request);
    }
}
