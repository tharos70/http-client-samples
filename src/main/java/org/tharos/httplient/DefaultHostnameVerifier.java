package org.tharos.httplient;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class DefaultHostnameVerifier implements HostnameVerifier {

    @Override
    public boolean verify(String hostname, SSLSession session) {
        return true;
    }

}