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

    final static Logger logger = Logger.getLogger("Rectify");

    public static void info(String msg) {
        logger.info(msg);
    }

    public static void error(String msg) {
        logger.severe(msg);
    }

    public static void warn(String msg) {
        logger.warning(msg);
    }
    
    
    
}
