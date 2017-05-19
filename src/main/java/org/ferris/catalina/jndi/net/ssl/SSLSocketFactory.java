package org.ferris.catalina.jndi.net.ssl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

/**
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class SSLSocketFactory extends javax.net.ssl.SSLSocketFactory {

    private static Log LOG = LogFactory.getLog(SSLSocketFactory.class);

    private javax.net.ssl.SSLSocketFactory trustAllSSLSocketFactory;

    /**
     * Custom ssl socket factory which accepts all certificates
     */
    public SSLSocketFactory() {

        try {
            LOG.info("[SSLSocketFactory constructor]");
            // Create TrustManager array
            TrustManager[] trustAllCerts = new TrustManager[] {new AcceptAllX509TrustManager()};
            // Install the all-trusting trust manager
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            trustAllSSLSocketFactory = sslContext.getSocketFactory();
            LOG.info(String.format("%s", trustAllSSLSocketFactory.getClass().getName()));
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * (non-Javadoc)
     * @see javax.net.SocketFactory#createSocket()
     */
    @Override
    public Socket createSocket() throws IOException {
        return trustAllSSLSocketFactory.createSocket();
    }

    /*
     * (non-Javadoc)
     * @see javax.net.SocketFactory#createSocket(java.net.InetAddress, int)
     */
    @Override
    public Socket createSocket(InetAddress paramInetAddress, int paramInt) throws IOException {
        return trustAllSSLSocketFactory.createSocket(paramInetAddress, paramInt);
    }

    /*
     * (non-Javadoc)
     * @see javax.net.SocketFactory#createSocket(java.net.InetAddress, int, java.net.InetAddress, int)
     */
    @Override
    public Socket createSocket(InetAddress paramInetAddress1, int paramInt1,
        InetAddress paramInetAddress2, int paramInt2) throws IOException {
        return trustAllSSLSocketFactory.createSocket(paramInetAddress1, paramInt1, paramInetAddress2, paramInt2);
    }

    /*
     * (non-Javadoc)
     * @see javax.net.ssl.SSLSocketFactory#createSocket(java.net.Socket, java.lang.String, int, boolean)
     */
    @Override
    public Socket createSocket(Socket paramSocket, String paramString, int paramInt,
        boolean paramBoolean) throws IOException {
        return trustAllSSLSocketFactory.createSocket(paramSocket, paramString, paramInt, paramBoolean);
    }

    /*
     * (non-Javadoc)
     * @see javax.net.SocketFactory#createSocket(java.lang.String, int)
     */
    @Override
    public Socket createSocket(String paramString, int paramInt) throws IOException {
        return trustAllSSLSocketFactory.createSocket(paramString, paramInt);
    }

    /*
     * (non-Javadoc)
     * @see javax.net.SocketFactory#createSocket(java.lang.String, int, java.net.InetAddress, int)
     */
    @Override
    public Socket createSocket(String paramString, int paramInt1, InetAddress paramInetAddress,
        int paramInt2) throws IOException {
        return trustAllSSLSocketFactory.createSocket(paramString, paramInt1, paramInetAddress, paramInt2);
    }

    /*
     * (non-Javadoc)
     * @see javax.net.ssl.SSLSocketFactory#getDefaultCipherSuites()
     */
    @Override
    public String[] getDefaultCipherSuites() {
        return trustAllSSLSocketFactory.getDefaultCipherSuites();
    }

    /*
     * (non-Javadoc)
     * @see javax.net.ssl.SSLSocketFactory#getSupportedCipherSuites()
     */
    @Override
    public String[] getSupportedCipherSuites() {
        return trustAllSSLSocketFactory.getSupportedCipherSuites();
    }

    public static SocketFactory getDefault() {
        LOG.info("[Acquiring the default ssl socket factory]");
        return new SSLSocketFactory();
    }

}
