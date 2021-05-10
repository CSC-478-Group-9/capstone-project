package csc478.group9.analyticsdashboard.controller;

import csc478.group9.analyticsdashboard.model.Asset;
import csc478.group9.analyticsdashboard.model.User;
import csc478.group9.analyticsdashboard.service.AssetService;
import csc478.group9.analyticsdashboard.service.UserService;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 */

@Controller
public class AssetController {
    private AssetService assetService;
    private UserService userService;

    public AssetController(AssetService assetService, UserService userService) {
        this.assetService = assetService;
        this.userService = userService;
    }

    @GetMapping("/add-asset")
    public String assetPage(Model model, Asset asset){
        model.addAttribute("asset",  asset);
        return "asset";
    }

    @PostMapping("/add-asset")
    public String signupUser(@ModelAttribute Asset asset, Model model) {
        String error = null;

        model.addAttribute("asset", asset);

        if (error == null) {
            int rowsAdded = assetService.createAsset(asset);
            if (rowsAdded < 0) {
                error = "There was an error creating the asset. Please try again.";
            }
        }

        if (error == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("error", error);
        }
        return "home";
    }

    @RequestMapping(value="asset", method = RequestMethod.GET)
    public @ResponseBody Asset getAsset(@RequestParam("id") String id, Model model, @ModelAttribute Asset asset){
        assetService.getAssetById(id);
        model.addAttribute("asset", asset);

        return asset;
    }







}
