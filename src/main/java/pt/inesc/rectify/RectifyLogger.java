/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.inesc.rectify;


import java.util.Date;
import java.util.logging.Logger;
import org.hibernate.Transaction;



/**
 *
 * @author david
 */
public class RectifyLogger {
    
    private static final String LEVEL_INFO = "info";
    private static final String LEVEL_WARNING = "warning";
    private static final String LEVEL_SEVERE = "danger";
    private static final String LEVEL_SUCCESS = "success";

    private static final String LOGGER_NAME = "Rectify";
    private static Logger logger = null;

    
    public static void success(String msg) {
        getLogger().info(msg);
        logToDb(msg, LEVEL_SUCCESS);
    }
    
    public static void info(String msg) {
        getLogger().info(msg);
        logToDb(msg, LEVEL_INFO);
    }

    public static void severe(String msg) {
        getLogger().severe(msg);
        logToDb(msg, LEVEL_SEVERE);
    }

    public static void warn(String msg) {
        getLogger().warning(msg);
        logToDb(msg, LEVEL_WARNING);

    }

    private static Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger(LOGGER_NAME);
            
        }
        return logger;
    }

    
    
    private static synchronized void logToDb(String message, String level){
        pt.inesc.rectify.hibernate.RectifyLog rl = new pt.inesc.rectify.hibernate.RectifyLog(level, message, new Date(), "Rectify");
        Transaction transactio = Rectify.getInstance().getHibSession().beginTransaction();
        Rectify.getInstance().getHibSession().save(rl);
        transactio.commit();
    }
}
