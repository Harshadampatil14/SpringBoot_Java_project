package com.empolyees.springboot.mvcdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class LoginController {
    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {
       return "fancy-login";
       // return "plain-login";
    }

    //add request mapping for /access-denied
    @GetMapping("/accessDenied")
    public String showAccessDenied() {
        return "access-denied";
    }
}
