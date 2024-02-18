package com.queuesystem.controller;

import com.queuesystem.dbAdapter.DBAdapter;
import com.queuesystem.request.Order;
import com.queuesystem.request.Request;
import com.queuesystem.request.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.queuesystem.security.SecurityUtils.getCurrentUserId;

@Controller
public class FrontController {
    private final DBAdapter dbAdapter;

    public FrontController(DBAdapter dbAdapter) {
        this.dbAdapter = dbAdapter;
    }

    @GetMapping("/console")
    public String console(Model model) {
        Order order = new Order();
        List<Request> requests = dbAdapter.findOnlyRequests();
        List<Order> orders = dbAdapter.findAllOrders();
        model.addAttribute("orders", orders);
        model.addAttribute("order", order);
        model.addAttribute("requests", requests);
        return "console";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("request", new Request());
        return "home";
    }

    @GetMapping("/service")
    public String service() {
        return "service";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("/customer-list")
    public String customerList(Model model) {
        List<Request> requests = dbAdapter.findRequestsByUserId(getCurrentUserId());
        model.addAttribute("requests", requests);
        return "customer-list";
    }
}