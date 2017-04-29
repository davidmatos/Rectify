/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.inesc.rectify;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pt.inesc.rectify.db.proxy.DBProxy;
import pt.inesc.rectify.hibernate.KbDbOp;
import pt.inesc.rectify.hibernate.KbHttpRequest;
import pt.inesc.rectify.hibernate.KbHttpResponse;
import pt.inesc.rectify.hibernate.RectifyLog;
import pt.inesc.rectify.http.proxy.HTTPProxy;
import pt.inesc.rectify.utils.HibernateUtil;

/**
 *
 * @author david
 */
public class Rectify {

    private static final boolean MODE_NORMAL = false;
    private static final boolean MODE_TRAINING = true;

    private Session hibSession;

    
    private KbHttpRequest currentKbHttpRequest = null;
    private KbHttpResponse currentKbHttpResponse = null;
    private Set<KbDbOp> currentKbDbOps = new HashSet<>();

    private DBProxy dbProxy = null;

    private HTTPProxy httpProxy = null;

    private boolean mode = MODE_NORMAL;

    private static Rectify instance = null;

    public static Rectify getInstance() {
        if (instance == null) {
            instance = new Rectify();
        }
        return instance;
    }

    public Rectify() {
        hibSession = HibernateUtil.getSessionFactory().openSession();
        Transaction t = hibSession.beginTransaction();
        for (RectifyLog item
                : (List<RectifyLog>) hibSession.createQuery("FROM RectifyLog item").list()) {
            hibSession.delete(item);
        }
        hibSession.flush();
        assert hibSession.createQuery("FROM RectifyLog item").list().size() == 0;
        t.commit();

    }

    public void setModeToNormal() {
        RectifyLogger.info("Mode set to normal");
        mode = MODE_NORMAL;
    }

    public void setModeToTraining() {
        RectifyLogger.info("Mode set to training");
        mode = MODE_TRAINING;

        currentKbHttpRequest = null;
        currentKbHttpResponse = null;
        currentKbDbOps = new HashSet<>();

    }

    public  boolean isInTrainingMode() {
        return mode == MODE_TRAINING;
    }

    public boolean isInNormalMode() {

        return mode == MODE_NORMAL;
    }

    public void setCurrentKbHttpRequest(KbHttpRequest request) {
        currentKbHttpRequest = request;
        currentKbDbOps = new HashSet<>();
    }

    public void setCurrentKbHttpResponse(KbHttpResponse response) {
        currentKbHttpResponse = response;
    }

    public void addCurrentKbDbOp(KbDbOp dbOp) {
        currentKbDbOps.add(dbOp);
    }

    public Session getHibSession() {
        return hibSession;
    }

    public void setHibSession(Session hibSession) {
        this.hibSession = hibSession;
    }

    public Set<KbDbOp> getCurrentKbDbOps() {
        return currentKbDbOps;
    }

    public void setCurrentKbDbOps(Set<KbDbOp> currentKbDbOps) {
        this.currentKbDbOps = currentKbDbOps;
    }

    public DBProxy getDbProxy() {
        return dbProxy;
    }

    public void setDbProxy(DBProxy dbProxy) {
        this.dbProxy = dbProxy;
    }

    public HTTPProxy getHttpProxy() {
        return httpProxy;
    }

    public void setHttpProxy(HTTPProxy httpProxy) {
        this.httpProxy = httpProxy;
    }

    public boolean isMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public KbHttpRequest getCurrentKbHttpRequest() {
        return currentKbHttpRequest;
    }

    public KbHttpResponse getCurrentKbHttpResponse() {
        return currentKbHttpResponse;
    }
    
    
    
    
    
    

}
