package csc478.group9.analyticsdashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LogoutController {
    @PostMapping("/logout")
    public String logoutUser() {
        return "home";
    }
}


