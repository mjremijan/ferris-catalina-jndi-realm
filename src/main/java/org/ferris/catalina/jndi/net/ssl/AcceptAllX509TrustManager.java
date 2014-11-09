package org.ferris.catalina.jndi.net.ssl;

import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class AcceptAllX509TrustManager implements X509TrustManager {
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) {
        int ignorePmd = ignoreComparingIdenticalExpressionsWarning();
        if (ignorePmd == 0) {
        }
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) {
        int ignorePmd = ignoreComparingIdenticalExpressionsWarning();
        if (ignorePmd == 0) {
        }
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        X509Certificate[] ignorePmd = null;
        return ignorePmd;
    }

    /**
     * Work-around for Eclipse "comparing identical expressions" warning.
     */
    private static int ignoreComparingIdenticalExpressionsWarning() {
        return 0;
    }
}
