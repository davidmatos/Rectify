/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.inesc.rectify;

import java.sql.Date;
import java.util.Calendar;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pt.inesc.rectify.hibernate.HibernateUtil;
import pt.inesc.rectify.hibernate.Logs;

/**
 *
 * @author david
 */
public class RectifyLogger {

    //final static Logger logger = Logger.getLogger("Rectify");
    private static Session hibSession = HibernateUtil.getSessionFactory().openSession();

    public static void info(String msg) {

        Logs logs = new Logs("Rectify", new Date(Calendar.getInstance().getTimeInMillis()), "Rectify", "INFO", msg);
        saveLogToDatabase(logs);

//        logger.info(msg);
    }

    public static void error(String msg) {
//        logger.severe(msg);
        Logs logs = new Logs("Rectify", new Date(Calendar.getInstance().getTimeInMillis()), "Rectify", "ERROR", msg);
        saveLogToDatabase(logs);
    }

    public static void warn(String msg) {
//        logger.warning(msg);
        Logs logs = new Logs("Rectify", new Date(Calendar.getInstance().getTimeInMillis()), "Rectify", "WARN", msg);
        saveLogToDatabase(logs);
    }

    private static void saveLogToDatabase(Logs logs) {
        Transaction transaction = hibSession.beginTransaction();
        transaction.begin();

        hibSession.save(logs);
        transaction.commit();
    }

}
