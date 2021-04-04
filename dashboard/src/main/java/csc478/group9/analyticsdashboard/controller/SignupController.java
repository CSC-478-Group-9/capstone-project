package csc478.group9.analyticsdashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/signup")
public class SignupController {

    @RequestMapping
    public String getSignup(){
        return "signup";
    }

}
