package csc478.group9.analyticsdashboard.service;

import csc478.group9.analyticsdashboard.mapper.AssetMapper;
import csc478.group9.analyticsdashboard.model.Asset;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {
    public AssetMapper assetMapper;

    public AssetService(AssetMapper assetMapper) {
        this.assetMapper = assetMapper;
    }

    public List<Asset> getUserAssets() {

        String currentUserName = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        return assetMapper.getUserAssets(currentUserName);
    }

    public int createAsset(Asset asset) {

        String currentUserName = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            asset.setUsername(currentUserName);
        }
        asset.setApiToken(getApiToken());
        return assetMapper.createAsset(asset);
    }

    public List<Asset> getAllAssets() {
        return assetMapper.getAllAssets();
    }

    public String getApiToken() {
        return assetMapper.getApiToken();
    }

    public Asset getAssetById(String id) {
        return assetMapper.getAssetbyId(id);
    }
}
