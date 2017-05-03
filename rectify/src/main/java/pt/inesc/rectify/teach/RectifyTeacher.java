/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.inesc.rectify.teach;

import java.util.ArrayList;

import org.hibernate.Transaction;

import pt.inesc.rectify.Rectify;
import pt.inesc.rectify.RectifyLogger;
import pt.inesc.rectify.hibernate.KbDbStatement;
import pt.inesc.rectify.hibernate.TeachingRoute;
import pt.inesc.rectify.http.proxy.HTTPProxy;
import pt.inesc.rectify.utils.HttpUtils;

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

            HttpUtils.performHttpRequest(teachingRoute.getRoute());

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

    

}
