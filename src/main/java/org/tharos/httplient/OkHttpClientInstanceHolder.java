package org.tharos.httplient;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

public class OkHttpClientInstanceHolder {

    /** The simple singleton instance. */
    private static OkHttpClient _instance = null;

    /** The simple singleton timeout aware instance. */
    private static OkHttpClient _instanceTimeoutAware = null;

    /** The singleton unsafe instance. */
    private static OkHttpClient _unsafeInstance = null;

    /** The singleton unsafe proxy enabled instance. */
    private static OkHttpClient _proxyEnabledInstance = null;

    /** The Constant LOG. */
    private static final Logger LOG = Logger.getLogger("client.instance");

    // Create a trust manager that does not validate certificate chains
    private static final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[] {};
        }
    } };

    /**
     * Instantiates a new ok http client holder.
     */
    private OkHttpClientInstanceHolder() {
    }

    /**
     * Returns a simple singleton instance of OkHttpClient
     *
     * @return single instance of OkHttpClient
     */
    public static OkHttpClient getSimpleInstance() {
        LOG.fine("OkHttpClientInstanceHolder:getSimpleInstance - IN");
        if (_instance == null) {
            _instance = new OkHttpClient().newBuilder().build();
        }
        LOG.fine("OkHttpClientInstanceHolder:getSimpleInstance - OUT");
        return _instance;
    }

    public static OkHttpClient getSimpleTimeoutAwareInstance(int timeout) {
        LOG.fine("OkHttpClientInstanceHolder:getSimpleTimeoutAwareInstance - IN");
        if (_instanceTimeoutAware == null) {
            _instanceTimeoutAware = new OkHttpClient().newBuilder()
                    .connectTimeout(timeout, TimeUnit.SECONDS)
                    .readTimeout(timeout, TimeUnit.SECONDS)
                    .writeTimeout(timeout, TimeUnit.SECONDS).build();
        }
        LOG.fine("OkHttpClientInstanceHolder:getSimpleTimeoutAwareInstance - OUT");
        return _instanceTimeoutAware;
    }

    /**
     * Returns an unsafe singleton instance of OkHttpClient
     * It does not validates the certificate chains
     *
     * @return the unsafe singleton instance of OkHttpClient
     */
    public static OkHttpClient getUnsafeInstance(int timeout) {
        LOG.fine("OkHttpClientInstanceHolder:getUnsafeInstance - IN");
        if (_unsafeInstance == null) {
            try {
                // Install the all-trusting trust manager
                final SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                // Create an ssl socket factory with our all-trusting manager
                final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
                builder.hostnameVerifier(new DefaultHostnameVerifier());
            } catch (Exception ex) {
                throw new RuntimeException(
                        "OkHttpClientInstanceHolder:getUnsafeInstance - cannot build unsafe http client instance", ex);
            }
        }
        LOG.fine("OkHttpClientInstanceHolder:getUnsafeOkHttpClient - OUT");
        return _unsafeInstance;

    }

    /**
     * Gets the unsafe proxy enabled if available ok http client.
     *
     * @return the unsafe proxy enabled if available ok http client
     */
    public static OkHttpClient getProxyEnabledIfAvailableOkHttpClient(final String proxyHost,
            final int proxyPort) {
        LOG.info("OkHttpClientInstanceHolder:getProxyEnabledIfAvailableOkHttpClient - IN");
        if (_proxyEnabledInstance == null) {
            try {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                try {
                    LOG.info("Building client with proxy");
                    LOG.fine("Proxy Port [" + proxyPort + "]");
                    LOG.fine("Proxy URL [" + proxyHost + "]");
                    Proxy proxyInstance = new Proxy(Proxy.Type.HTTP,
                            new InetSocketAddress(proxyHost, proxyPort));
                    _proxyEnabledInstance = builder.proxy(proxyInstance).build();
                } catch (Exception ex) {
                    throw new RuntimeException(
                            "OkHttpClientInstanceHolder:getProxyEnabledIfAvailableOkHttpClient - cannot build proxy aware http client instance",
                            ex);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        LOG.info("OkHttpClientInstanceHolder:getProxyEnabledIfAvailableOkHttpClient - OUT");
        return _proxyEnabledInstance;
    }

}