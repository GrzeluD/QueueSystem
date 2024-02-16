package com.queuesystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/console")
    public String console() {
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