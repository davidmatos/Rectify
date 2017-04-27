/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.inesc.rectify;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import org.hibernate.Transaction;
import pt.inesc.rectify.hibernate.LogDbOperation;
import pt.inesc.rectify.hibernate.LogHttpRequest;

/**
 *
 * @author davidmatos
 */
public class AsyncLogWriter extends TimerTask {

    private ArrayList<LogDbOperation> logDbOperations;
    private ArrayList<LogHttpRequest> logHttpRequest;
    

    private static long SECONDS_TO_SYNC_LOG = 5;

    private static AsyncLogWriter instance = null;

    public static AsyncLogWriter getInstance() {
        if (instance == null) {
            instance = new AsyncLogWriter();
        }

        return instance;
    }

    public AsyncLogWriter() {
        this.logDbOperations = new ArrayList<>();
        this.logHttpRequest = new ArrayList<>();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(this, SECONDS_TO_SYNC_LOG*1000, SECONDS_TO_SYNC_LOG*1000);

    }
    
    
    
    public void addLogDbOperation(String query){
        this.logDbOperations.add(new LogDbOperation(new Date(), query, null));
    
    }
    
    
    public void addLogHttpRequest(String request, String uri){
        this.logHttpRequest.add(new LogHttpRequest(new Date(), request, uri));
    }

    @Override
    public void run() {
        System.out.println("Syncing...");
        synchronized (logDbOperations) {
            if (!logDbOperations.isEmpty()) {

                Transaction transaction = Rectify.hibSession.beginTransaction();
                for (LogDbOperation dbOperation : logDbOperations) {

                    Rectify.hibSession.save(dbOperation);

                }
                transaction.commit();

                logDbOperations.clear();
            }

        }

        synchronized (logHttpRequest) {
            if (!logHttpRequest.isEmpty()) {

                Transaction transaction = Rectify.hibSession.beginTransaction();
                for (LogHttpRequest httpRequest : logHttpRequest) {

                    Rectify.hibSession.save(httpRequest);

                }
                transaction.commit();

                logHttpRequest.clear();
            }
        }
        
        
        
        
        
        
        
        

    }

}
