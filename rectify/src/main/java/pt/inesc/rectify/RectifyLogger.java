/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.inesc.rectify;

import java.util.Date;

import org.hibernate.Session;

import pt.inesc.rectify.hibernate.RectifyLog;
import pt.inesc.rectify.utils.HibernateUtil;

/**
 *
 * @author david
 */
public class RectifyLogger {

	private static Session hibSession = HibernateUtil.getSessionFactory().openSession();
	
    public static void info(String msg) {
    	RectifyLog rectifyLog = new RectifyLog("INFO", msg, new Date(), "RectifyLogger");
    	hibSession.save(rectifyLog);
    }

    public static void error(String msg) {
    	RectifyLog rectifyLog = new RectifyLog("ERROR", msg, new Date(), "RectifyLogger");
    	hibSession.save(rectifyLog);
    }

    public static void warn(String msg) {
    	RectifyLog rectifyLog = new RectifyLog("WARNING", msg, new Date(), "RectifyLogger");
    	hibSession.save(rectifyLog);
    }

}
