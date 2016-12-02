/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.inesc.rectify.utils;

import java.util.List;

import org.hibernate.Transaction;

import pt.inesc.rectify.Rectify;
import pt.inesc.rectify.hibernate.Configuration;

/**
 *
 * @author david
 */
public class RectifyUtils {

    public static boolean isNewInstance() {
        
        List<Configuration> configurationValues = Rectify.hibSession.createSQLQuery("SELECT * FROM configuration;").list();
        return configurationValues.isEmpty();
    }

    public static String getConfigurationEntry(String configurationName) {
        
        String result = Rectify.hibSession.createSQLQuery("SELECT configuration_value FROM configuration WHERE configuration_name = '" + configurationName + "'").list().get(0).toString();
        
        return result;
    }
    
    public static void updateConfigurationEntry(String configurationName, String configurationValue ) {
        
        
        Transaction transaction = Rectify.hibSession.beginTransaction();
        
        Configuration configuration = (Configuration)Rectify.hibSession.createQuery("FROM Configuration WHERE configurationName = :configurationName").setParameter("configurationName", configurationName).list().get(0);
        	
        configuration.setConfigurationValue(configurationValue);
        
        Rectify.hibSession.update(configuration);
        
        transaction.commit();
    }


}
