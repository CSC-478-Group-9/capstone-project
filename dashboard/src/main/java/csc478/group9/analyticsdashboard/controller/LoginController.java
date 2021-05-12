package csc478.group9.analyticsdashboard.controller;

import csc478.group9.analyticsdashboard.model.User;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @PostMapping("/login")
    public String processLogin(@ModelAttribute User user, Model model){
        return "login";
    }


    @GetMapping("/login")
    public String getLogin(@ModelAttribute User user, Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/login-error")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = "Wrong username or password. Please review and try again.";
            }
        }
        model.addAttribute("user", new User());
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }
}
