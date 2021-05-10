package csc478.group9.analyticsdashboard.mapper;

import csc478.group9.analyticsdashboard.model.Asset;
import csc478.group9.analyticsdashboard.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface AssetMapper {

    @Select("SELECT * FROM assets WHERE username = #{username}")
    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "apiToken", column = "api_token"),
            @Result(property = "assetName", column = "asset_name"),
            @Result(property = "assetUrl", column = "asset_url"),
            @Result(property = "assetDescription", column = "asset_description"),
            @Result(property = "assetId", column = "asset_id"),
            @Result(property = "created", column = "created"),
            @Result(property = "updated", column = "updated"),
    })
    public List<Asset> getUserAssets(String username);

    @Insert("INSERT INTO assets (username, api_token, asset_name, asset_url, asset_description, created, updated) VALUES(#{username}, #{apiToken}, #{assetName}, #{assetUrl}, #{assetDescription}, now(), now())")
    @Options(useGeneratedKeys = true, keyProperty = "assetId")
    int createAsset(Asset asset);

    @Select("Select * from assets")
    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "apiToken", column = "api_token"),
            @Result(property = "assetName", column = "asset_name"),
            @Result(property = "assetUrl", column = "asset_url"),
            @Result(property = "assetDescription", column = "asset_description"),
            @Result(property = "assetId", column = "asset_id"),
            @Result(property = "created", column = "created"),
            @Result(property = "updated", column = "updated"),
    })
    public List<Asset> getAllAssets();

    @Select("select get_api_token()")
     String getApiToken();

    @Select("select * from assets where asset_id =#{assetId}")
    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "apiToken", column = "api_token"),
            @Result(property = "assetName", column = "asset_name"),
            @Result(property = "assetUrl", column = "asset_url"),
            @Result(property = "assetDescription", column = "asset_description"),
            @Result(property = "assetId", column = "asset_id"),
            @Result(property = "created", column = "created"),
            @Result(property = "updated", column = "updated"),
    })
    public Asset getAssetbyId(String assetId);

}
