/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.inesc.rectify;


import java.util.logging.Logger;



/**
 *
 * @author david
 */
public class RectifyLogger {

    private static final String LOGGER_NAME = "Rectify";
    private static Logger logger = null;

    public static void info(String msg) {
        getLogger().info(msg);
    }

    public static void error(String msg) {
        getLogger().severe(msg);
    }

    public static void warn(String msg) {
        getLogger().warning(msg);

    }

    private static Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger(LOGGER_NAME);
            
        }
        return logger;
    }

}
