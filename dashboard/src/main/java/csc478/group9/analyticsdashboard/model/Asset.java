package csc478.group9.analyticsdashboard.model;


import java.sql.Date;

public class Asset {

    private Integer assetId;
    private String username;
    private String apiToken;
    private String assetName;
    private String assetUrl;
    private String assetDescription;
    private java.sql.Date created;
    private java.sql.Date updated;

    //no-arg constructor
    public Asset(){

    }

    public Asset(Integer assetId, String username, String apiToken, String assetName, String assetUrl, String assetDescription, Date created, Date updated) {
        this.assetId = assetId;
        this.username = username;
        this.apiToken = apiToken;
        this.assetName = assetName;
        this.assetUrl = assetUrl;
        this.assetDescription = assetDescription;
        this.created = created;
        this.updated = updated;
    }

    public String getUsername() {
        return username;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetUrl() {
        return assetUrl;
    }

    public void setAssetUrl(String assetUrl) {
        this.assetUrl = assetUrl;
    }

    public String getAssetDescription() {
        return assetDescription;
    }

    public void setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }


}

