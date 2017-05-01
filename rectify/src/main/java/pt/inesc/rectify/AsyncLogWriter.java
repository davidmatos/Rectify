/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.inesc.rectify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.hibernate.Transaction;
import pt.inesc.rectify.hibernate.LogDbStatement;

import pt.inesc.rectify.hibernate.LogHttpRequest;
import pt.inesc.rectify.hibernate.LogHttpResponse;

/**
 *
 * @author davidmatos
 */
public class AsyncLogWriter extends TimerTask {

    private List<LogDbStatement> logDbStatements;
    private List<LogHttpRequest> logHttpRequest;
    private List<LogHttpResponse> logHttpResponse;

    private static long SECONDS_TO_SYNC_LOG = 5;

    private static AsyncLogWriter instance = null;

    public static AsyncLogWriter getInstance() {
        if (instance == null) {
            instance = new AsyncLogWriter();
        }

        return instance;
    }

    public AsyncLogWriter() {
        this.logDbStatements = Collections.synchronizedList(new ArrayList<LogDbStatement>());
        this.logHttpRequest = Collections.synchronizedList(new ArrayList<LogHttpRequest>());
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(this, SECONDS_TO_SYNC_LOG * 1000, SECONDS_TO_SYNC_LOG * 1000);

    }

    public void addLogDbStatement(String query) {
        synchronized (this.logDbStatements) {
            this.logDbStatements.add(new LogDbStatement(new Date(), query));
        }

    }

    public void addLogHttpRequest(String request, String uri, String host) {
        synchronized (this.logHttpRequest) {
            this.logHttpRequest.add(new LogHttpRequest(new Date(), request, uri, host, null));
        }
    }

    @Override
    public void run() {
        
        synchronized (logDbStatements) {
            if (!logDbStatements.isEmpty()) {

                Transaction transaction = Rectify.getInstance().getHibSession().beginTransaction();
                for (LogDbStatement dbOperation : logDbStatements) {

                    Rectify.getInstance().getHibSession().save(dbOperation);

                }
                transaction.commit();
                
                logDbStatements.clear();
            }

        }

        synchronized (logHttpRequest) {
            if (!logHttpRequest.isEmpty()) {

                Transaction transaction = Rectify.getInstance().getHibSession().beginTransaction();
                for (LogHttpRequest httpRequest : logHttpRequest) {

                    Rectify.getInstance().getHibSession().save(httpRequest);

                }
                transaction.commit();

                logHttpRequest.clear();
            }
        }

    }

}
