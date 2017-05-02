/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.inesc.rectify.teach;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Transaction;

import pt.inesc.rectify.Rectify;
import pt.inesc.rectify.RectifyLogger;
import pt.inesc.rectify.hibernate.KbDbStatement;
import pt.inesc.rectify.hibernate.KbHttpRequest;
import pt.inesc.rectify.hibernate.TeachingRoute;
import pt.inesc.rectify.http.proxy.HTTPProxy;

/**
 *
 * @author davidmatos
 */
public class RectifyTeacher {

    ArrayList<TeachingRoute> teachingRoutes;

    public RectifyTeacher() {
        teachingRoutes = (ArrayList<TeachingRoute>) Rectify.getInstance().getHibSession().createQuery("FROM TeachingRoute").list();// WHERE taught = false").list();

    }

    public void teach() {
        if (Rectify.getInstance().getHttpProxy() == null) {
            RectifyLogger.severe("Rectify unable to start teaching phase because the HTTP Proxy is not running.");
            return;
        }

        Rectify.getInstance().setModeToTeaching();
        for (TeachingRoute teachingRoute : this.teachingRoutes) {
            RectifyLogger.info("Teaching rectify '" + teachingRoute.getRoute() + "'");

            HTTPProxy httpProxy = Rectify.getInstance().getHttpProxy();

            performHttpRequest(teachingRoute.getRoute());

            Rectify.getInstance().getCurrentKbHttpRequest().setKbDbStatements(Rectify.getInstance().getCurrentKbDbStatements());

            synchronized (Rectify.getInstance().getHibSession()) {
                Transaction transaction = Rectify.getInstance().getHibSession().beginTransaction();
                Rectify.getInstance().getHibSession().save(Rectify.getInstance().getCurrentKbHttpRequest());

                transaction.commit();

                transaction = Rectify.getInstance().getHibSession().beginTransaction();
                for (KbDbStatement kbDbStatement : Rectify.getInstance().getDbProxy().getTeachingModeStatements()) {
                    System.out.println("Saving this entry: " + kbDbStatement.getQuery());
                    kbDbStatement.setKbHttpRequest(Rectify.getInstance().getCurrentKbHttpRequest());
                    Rectify.getInstance().getHibSession().save(kbDbStatement);
                }

                transaction.commit();

            }

            markAsTaught(teachingRoute);
            Rectify.getInstance().setCurrentKbDbStatements(null);
            Rectify.getInstance().setCurrentKbHttpRequest(null);
            Rectify.getInstance().getDbProxy().resetTeachingModeStatements();
        }

        Rectify.getInstance().setModeToNormal();
        RectifyLogger.success("Completed teaching phase");
    }

    private void markAsTaught(TeachingRoute teachingRoute) {
        teachingRoute.setTaught(true);
        Transaction transaction = Rectify.getInstance().getHibSession().beginTransaction();
        Rectify.getInstance().getHibSession().update(teachingRoute);
        transaction.commit();
    }

    private void performHttpRequest(String uri) {
//        try {
//            new HttpThread(uri).run();
//            Thread.sleep(3000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(RectifyTeacher.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try {
            URL url = new URL(uri);
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            yc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                //System.out.println(inputLine);
            }
            in.close();
        } catch (Exception e) {
            RectifyLogger.severe("Couldn't perform HTTP request due to: " + e.getMessage());
        }
    }

//    private class HttpThread implements Runnable {
//
//        String uri;
//
//        public HttpThread(String uri) {
//            this.uri = uri;
//        }
//
//        @Override
//        public void run() {
//            try {
//
//                RectifyLogger.info("Teaching Rectify route: " + uri);
//
//                URLConnection connection = new URL(uri).openConnection();
//                connection.setRequestProperty("Accept-Charset", "UTF-8");
//
//                Rectify.getInstance().setCurrentKbHttpRequest(new KbHttpRequest(new Date(), connection.getContent().toString(), uri, null));
//
//                InputStream response = connection.getInputStream();
//
//                Scanner scanner = new Scanner(response);
//                String responseBody = scanner.useDelimiter("\\A").next();
//
//            } catch (Exception e) {
//                RectifyLogger.severe("Couldn't perform HTTP request due to: " + e.getMessage());
//
//            }
//        }
//
//    }
}
