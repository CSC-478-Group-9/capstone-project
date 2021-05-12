package csc478.group9.analyticsdashboard.controller;

import csc478.group9.analyticsdashboard.service.AssetService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private AssetService assetService;

    public HomeController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/home")
    public String getHome(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            model.addAttribute("username", currentUserName);
        }

        model.addAttribute("userAssets", assetService.getUserAssets());
        return "home";
    }
}
