/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.inesc.rectify;

import java.util.ArrayList;

import org.hibernate.Session;

import pt.inesc.rectify.db.proxy.DBProxy;
import pt.inesc.rectify.hibernate.KbDbOp;
import pt.inesc.rectify.hibernate.KbHttpRequest;
import pt.inesc.rectify.hibernate.KbHttpResponse;
import pt.inesc.rectify.http.proxy.HTTPProxy;
import pt.inesc.rectify.utils.HibernateUtil;

/**
 *
 * @author david
 */
public class Rectify {
	
	public static Session hibSession = HibernateUtil.getSessionFactory().openSession();
			

	private static final boolean MODE_NORMAL = false;
	private static final boolean MODE_TRAINING = true;
	
	public static KbHttpRequest currentKbHttpRequest = null;
	public static KbHttpResponse currentKbHttpResponse = null;
	public static ArrayList<KbDbOp> currentKbDbOps = new ArrayList<>();
	

	static public DBProxy dbProxy = null;

	static public HTTPProxy httpProxy = null;

	static private boolean mode = MODE_NORMAL;

	public static void setModeToNormal() {
		RectifyLogger.info("Mode set to normal");
		mode = MODE_NORMAL;
	}

	public static void setModeToTraining() {
		RectifyLogger.info("Mode set to training");
		mode = MODE_TRAINING;
	}

	public static boolean isInTrainingMode() {
		return mode == MODE_TRAINING;
	}

	public static boolean isInNormalMode() {
		
		return mode == MODE_NORMAL;
	}
	
	public static void setCurrentKbHttpRequest(KbHttpRequest request){
		currentKbHttpRequest = request;
		currentKbDbOps = new ArrayList<>();
	}
	
	public static void setCurrentKbHttpResponse(KbHttpResponse response){
		currentKbHttpResponse = response;
	}

	public static void addCurrentKbDbOp(KbDbOp dbOp){
		currentKbDbOps.add(dbOp);
	}

}
