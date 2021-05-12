package csc478.group9.analyticsdashboard.service;

import csc478.group9.analyticsdashboard.mapper.AssetMapper;
import csc478.group9.analyticsdashboard.model.Asset;
import io.confluent.ksql.api.client.BatchedQueryResult;
import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.ClientOptions;
import io.confluent.ksql.api.client.Row;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

    public Integer getVisitsBySite(String site) throws IOException, ExecutionException, InterruptedException {
        int page_views = 0;

        ClientOptions options = ClientOptions.create()
                .setBasicAuthCredentials("GQZ6B7EX2ILXXVND", "5mSXwq54zdv7HHSRkTXRVSAfe97pbsQupud3g3FdFTSNDY5T4qIjLZax1dADwViM")
                .setHost("pksqlc-nwvrk.us-west4.gcp.confluent.cloud")
                .setPort(443)
                .setUseTls(true)
                .setUseAlpn(true);
        Client client = Client.create(options);

        String pullQuery = "SELECT * FROM PAGE_VIEWS WHERE site='"+site+"';";
        System.out.print(pullQuery);
        BatchedQueryResult batchedQueryResult = client.executeQuery(pullQuery);

        // Wait for query result
        List<Row> resultRows = batchedQueryResult.get();

        System.out.println("Received results. Num rows: " + resultRows.size());
        for (Row row : resultRows) {
            page_views = row.getInteger(2);
            System.out.println("Site " + row.getString(1) + " has " + row.getInteger(2) + " views.");
        }
        System.out.println(page_views);
        return page_views;
    }
}
