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
import pt.inesc.rectify.exp_eval.DatasetGenerator;
import pt.inesc.rectify.hibernate.LogDbStatement;

import pt.inesc.rectify.hibernate.LogHttpRequest;
import pt.inesc.rectify.http.parser.HttpParser;

/**
 *
 * @author davidmatos
 */
public class AsyncLogWriter extends TimerTask {
    
    private final List<LogDbStatement> logDbStatements;
    private final List<LogHttpRequest> logHttpRequest;
    
    private final static long SECONDS_TO_SYNC_LOG = 5;
    
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
            this.logDbStatements.add(new LogDbStatement(new Date(), query, DatasetGenerator.getInstance(0).getI()));
        }
        
    }
    
    public void addLogHttpRequest(String request) {
        synchronized (this.logHttpRequest) {
            this.logHttpRequest.add(new LogHttpRequest(new Date(), request, HttpParser.getUri(request), HttpParser.getHost(request), DatasetGenerator.getInstance(0).getI()));
        }
    }
    
    @Override
    public void run() {
        sync();
        
    }
    
    public void sync(){
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
