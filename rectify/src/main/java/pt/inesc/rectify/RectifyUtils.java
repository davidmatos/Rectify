/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.inesc.rectify;

import java.util.List;
import org.hibernate.Session;
import pt.inesc.rectify.hibernate.Configuration;
import pt.inesc.rectify.hibernate.HibernateUtil;

/**
 *
 * @author david
 */
public class RectifyUtils {

    public static boolean isNewInstance() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Configuration> configurationValues = session.createSQLQuery("SELECT * FROM configuration;").list();
        return configurationValues.isEmpty();
    }

    public static String getConfigurationEntry(String configurationName) {
        Session hibSession = HibernateUtil.getSessionFactory().openSession();
        String result = hibSession.createSQLQuery("SELECT configuration_value FROM configuration WHERE configuration_name = '" + configurationName + "'").list().get(0).toString();
        
        return result;
    }

}
