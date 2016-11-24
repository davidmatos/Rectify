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

}
