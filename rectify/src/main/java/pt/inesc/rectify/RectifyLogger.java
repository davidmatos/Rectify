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

	public static void info(String msg) {
		if (msg.length() > 1000) {
			divideInfo(msg);
		} else {
			RectifyLog rectifyLog = new RectifyLog("INFO", msg, new Date(), "RectifyLogger");
			Rectify.hibSession.save(rectifyLog);
		}

	}

	public static void error(String msg) {
		RectifyLog rectifyLog = new RectifyLog("ERROR", msg, new Date(), "RectifyLogger");
		Rectify.hibSession.save(rectifyLog);
	}

	public static void warn(String msg) {
		RectifyLog rectifyLog = new RectifyLog("WARNING", msg, new Date(), "RectifyLogger");
		Rectify.hibSession.save(rectifyLog);
	}

	private static void divideInfo(String msg) {
		do {
			String msgPart = msg.substring(0, 999);
			msg = msg.substring(999);
			RectifyLog rectifyLog = new RectifyLog("INFO", msgPart, new Date(), "RectifyLogger");
			Rectify.hibSession.save(rectifyLog);
		} while (msg.length() > 1000);
		RectifyLog rectifyLog = new RectifyLog("INFO", msg, new Date(), "RectifyLogger");
		Rectify.hibSession.save(rectifyLog);
	}

	public static void query(String msg) {
		if (msg.length() > 1000) {
			divideInfo(msg);
		} else {
			RectifyLog rectifyLog = new RectifyLog("QUERY", msg, new Date(), "RectifyLogger");
			Rectify.hibSession.save(rectifyLog);
		}

	}

	public static void http(String msg) {

		RectifyLog rectifyLog = new RectifyLog("HTTP", msg, new Date(), "RectifyLogger");
		Rectify.hibSession.save(rectifyLog);

	}

}
