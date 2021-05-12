package csc478.group9.analyticsdashboard.controller;

import csc478.group9.analyticsdashboard.model.Asset;
import csc478.group9.analyticsdashboard.service.AssetService;
import csc478.group9.analyticsdashboard.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

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

    @GetMapping("/tracking")
    public String assetVisitsPage(Model model, Asset asset, @RequestParam(value="id", required = false)String assetID) throws InterruptedException, ExecutionException, IOException {
        if (assetID == null){
            model.addAttribute("asset",  asset);
        }
        else{
            Asset requestedAsset = assetService.getAssetById(assetID);
            model.addAttribute("asset",  requestedAsset);
            model.addAttribute("pageViews",  assetService.getVisitsBySite(requestedAsset.getAssetUrl()));
        }
        model.addAttribute("assetID",  assetID);
        return "tracking";
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







}
