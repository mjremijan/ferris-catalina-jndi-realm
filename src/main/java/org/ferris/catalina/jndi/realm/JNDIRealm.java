package org.ferris.catalina.jndi.realm;

import java.util.Hashtable;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

/**
 * <p>
 * This class is a decorator over the Tomcat <code>org.apache.catalina.realm.JNDIRealm</code> class. This class
 * configures an {@code SSLSocketFactory} and a {@code AcceptAllX509TrustManager} so the SSL connection to the LDAP
 * server can be established without having to worry about any certificate problems.
 * </p>
 * <p>
 * <b style="color:red;">This is intended for localhost/development purposes only!!</b>
 * </p>
 * <p>
 * Configure Tomcat by editing the server.xml used when Tomcat starts. An example realm is:
 * </p>
 * <xmp> 
 * <Realm className="org.ferris.catalina.jndi.realm.JNDIRealm" 
 *   connectionURL="ldaps://server:12345"
 *   connectionName="uid=admin, ou=system" 
 *   connectionPassword="XYZYZYZYZYZYZ" 
 *   userPattern="uid={0},ou=people,dc=example"
 *   digest="SHA" 
 *   userPassword="userPassword" 
 *   roleBase="ou=groups,ou=system" 
 *   roleName="cn"
 *   roleSearch="(uniqueMember={0})"
 * /> </xmp>
 * 
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JNDIRealm extends org.apache.catalina.realm.JNDIRealm {
    private static Log LOG = LogFactory.getLog(JNDIRealm.class);

    /**
     * Create custom realm
     */
    public JNDIRealm() {
        super();
        LOG.info("[Creating JNDIRealm]");
    }

    /**
     * For unit testing
     */
    public JNDIRealm(Log log) {
        this();
        super.containerLog = log;
    }

    @Override
    protected Hashtable<String, String> getDirectoryContextEnvironment() {
        LOG.info("[Adding SSLSocketFactory]");

        Hashtable<String, String> env = super.getDirectoryContextEnvironment();
        env.put("java.naming.ldap.factory.socket", "org.ferris.catalina.jndi.net.ssl.SSLSocketFactory");

        return env;
    }
}
