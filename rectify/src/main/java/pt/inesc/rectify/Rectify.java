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
import pt.inesc.rectify.hibernate.KbDbStatement;
import pt.inesc.rectify.hibernate.KbHttpRequest;
import pt.inesc.rectify.hibernate.RectifyLog;
import pt.inesc.rectify.http.proxy.HTTPProxy;
import pt.inesc.rectify.utils.HibernateUtil;

/**
 *
 * @author david
 */
public class Rectify {

    private static final boolean MODE_NORMAL = false;
    private static final boolean MODE_TEACHING = true;

    private Session hibSession;

    
    private KbHttpRequest currentKbHttpRequest = null;
    private Set<KbDbStatement> currentKbDbOps = new HashSet<>();

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

    public void setModeToTeaching() {
        RectifyLogger.info("Mode set to training");
        mode = MODE_TEACHING;

        currentKbHttpRequest = null;
        currentKbDbOps = new HashSet<>();

    }

    public  boolean isInTeachingMode() {
        return mode == MODE_TEACHING;
    }

    public boolean isInNormalMode() {

        return mode == MODE_NORMAL;
    }

    public void setCurrentKbHttpRequest(KbHttpRequest request) {
        currentKbHttpRequest = request;
        currentKbDbOps = new HashSet<>();
    }

    

    public void addCurrentKbDbStatement(KbDbStatement stmt) {
        currentKbDbOps.add(stmt);
    }

    public Session getHibSession() {
        return hibSession;
    }

    public void setHibSession(Session hibSession) {
        this.hibSession = hibSession;
    }

    public Set<KbDbStatement> getCurrentKbDbStatements() {
        return currentKbDbOps;
    }

    public void setCurrentKbDbOps(Set<KbDbStatement> currentKbDbOps) {
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

    
    
    

}
