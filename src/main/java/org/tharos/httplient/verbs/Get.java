package org.tharos.httplient.verbs;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.AbstractMap;
import java.util.logging.Logger;

import org.tharos.httplient.OkHttpClientInstanceHolder;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

public class Get {

    /**
     * The local Logger instance
     */
    private static final Logger LOG = Logger.getLogger("get");

    /**
     * The Get Class constructor
     */
    public Get() {

    }

    /**
     * Performs a simple http GET call to the specified url with the specified query
     * parameter
     * 
     * @param url            The request url
     * @param queryParameter The query parameter to be added to this request
     * @return The response body of the input requested url
     * @throws IOException
     */
    public void simpleGetCall(String url, AbstractMap.SimpleEntry<String, String> queryParameter) throws IOException {
        LOG.info("GetCalls:simpleGetCall - IN");
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter(queryParameter.getKey(), queryParameter.getValue());
        Request request = new Request.Builder().url(urlBuilder.build()).build();
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
        LOG.info("GetCalls:simpleGetCall - OUT");
    }

    /**
     * Performs a simple http GET call to the specified url with the specified query
     * parameter with a specified timeout expressed in millisecond. When the timeout
     * occurs the call will be canceled.
     * 
     * @param url            The request url
     * @param queryParameter The query parameter to be added to this request
     * @param timeout
     * @return The response body of the input requested url
     * @throws IOException
     */
    public void simpleGetCallTimeoutAware(String url, AbstractMap.SimpleEntry<String, String> queryParameter,
            int timeout) throws IOException {
        LOG.info("GetCalls:simpleGetCallTimeoutAware - IN");
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter(queryParameter.getKey(), queryParameter.getValue());
        Request request = new Request.Builder().url(urlBuilder.build()).build();
        Call call = OkHttpClientInstanceHolder.getSimpleTimeoutAwareInstance(timeout).newCall(request);
        Response res = call.execute();
        String apiBodyResult = res.body().string();
        int responseCode = res.code();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            LOG.info("API call was successful. Response was:");
            LOG.info(apiBodyResult);
        } else {
            LOG.info("API call was not successful. Response status code was: " + responseCode);
        }
        LOG.info("GetCalls:simpleGetCallTimeoutAware - OUT");
    }

    /**
     * Performs a simple unsafe http GET call to the specified url with the
     * specified query
     * parameter with a specified timeout expressed in millisecond. When the timeout
     * occurs the call will be canceled. It is unsafe beacause in case of an https
     * url call, the certificates chain
     * will not be checked. It is not a recommended usage, though sometimes it may
     * be useful for troubleshooting purpose.
     * 
     * @param url            The request url
     * @param queryParameter The query parameter to be added to this request
     * @param timeout
     * @return The response body of the input requested url
     * @throws IOException
     */
    public void simpleUnsafeGetCallTimeoutAware(String url, AbstractMap.SimpleEntry<String, String> queryParameter,
            int timeout) throws IOException {
        LOG.info("GetCalls:simpleGetCallTimeoutAware - IN");
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter(queryParameter.getKey(), queryParameter.getValue());
        Request request = new Request.Builder().url(urlBuilder.build()).build();
        Call call = OkHttpClientInstanceHolder.getUnsafeInstance(timeout).newCall(request);
        Response res = call.execute();
        String apiBodyResult = res.body().string();
        int responseCode = res.code();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            LOG.info("API call was successful. Response was:");
            LOG.info(apiBodyResult);
        } else {
            LOG.info("API call was not successful. Response status code was: " + responseCode);
        }
        LOG.info("GetCalls:simpleGetCallTimeoutAware - OUT");
    }

}
