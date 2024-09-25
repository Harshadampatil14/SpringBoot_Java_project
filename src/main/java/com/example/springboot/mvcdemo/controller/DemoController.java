package com.example.springboot.mvcdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class DemoController {
    @GetMapping("/")
    public String showHome() {
        return "home";
    }
    @GetMapping("/leaders")
    public String showHomeLeaders() {
        return "leaders";
    }
    @GetMapping("/systems")
    public String showHomeSystems() {
        return "systems";
    }
}
