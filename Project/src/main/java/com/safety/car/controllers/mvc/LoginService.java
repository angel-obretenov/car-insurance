package com.safety.car.controllers.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginService {

    @GetMapping
    public String showLoginPage() {
        return "login";
    }
}
