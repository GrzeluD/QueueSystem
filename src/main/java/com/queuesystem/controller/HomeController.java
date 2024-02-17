package com.queuesystem.controller;

import com.queuesystem.request.Request;
import com.queuesystem.request.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private RequestService requestService;

    @Autowired
    public HomeController(RequestService requestService) {
        this.requestService = requestService;
    }
    @GetMapping("/console")
    public String console(Model model) {
        List<Request> requests = requestService.findAllRequests();
        model.addAttribute("requests", requests);
        return "console";
    }

    @GetMapping("/home")
    public String home() {
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
}