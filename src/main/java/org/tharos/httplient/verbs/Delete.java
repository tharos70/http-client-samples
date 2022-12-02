package org.tharos.httplient.verbs;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.logging.Logger;

import org.tharos.httplient.OkHttpClientInstanceHolder;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

public class Delete {

    private static final Logger LOG = Logger.getLogger("put");

    /**
     * Performs a simple http POST call to the specified url with the specified json
     * payload
     * 
     * @param url         The request url
     * @param jsonPayload The json payload posted by this request
     * @throws IOException
     */
    public void simplePostCall(String url, String jsonPayload) throws IOException {
        LOG.info("Delete:simpleDeleteCall - IN");
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        Request request = new Request.Builder().url(urlBuilder.build()).delete().build();
        Call call = OkHttpClientInstanceHolder.getSimpleInstance().newCall(request);
        Response res = call.execute();
        String apiBodyResult = res.body().string();
        int responseCode = res.code();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            LOG.info("API call was successful. Response was:");
            LOG.info(apiBodyResult);
        } else {
            LOG.info("API call was not successful. Response status code was: " + responseCode);
        }
        LOG.info("Delete:simpleDeleteCall - OUT");
    }
}