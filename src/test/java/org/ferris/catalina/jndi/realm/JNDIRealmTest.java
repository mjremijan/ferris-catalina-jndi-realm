package org.ferris.catalina.jndi.realm;

import org.ferris.catalina.jndi.realm.JNDIRealm;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.ferris.catalina.jndi.net.ssl.SSLSocketFactory;

import java.util.Hashtable;

import org.apache.juli.logging.LogFactory;
import org.junit.Test;

/**
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class JNDIRealmTest {

    @Test
    public void testGetDirectoryContextEnvironment() {
        JNDIRealm realm = new JNDIRealm(LogFactory.getLog(JNDIRealmTest.class));
        realm.setConnectionTimeout("5000");

        Hashtable<String, String> env = realm.getDirectoryContextEnvironment();
        assertEquals(SSLSocketFactory.class.getName(), env.get("java.naming.ldap.factory.socket"));
    }
}
