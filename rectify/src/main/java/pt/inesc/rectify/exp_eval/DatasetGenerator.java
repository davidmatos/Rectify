/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.inesc.rectify.exp_eval;

import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import pt.inesc.rectify.AsyncLogWriter;
import pt.inesc.rectify.Rectify;
import pt.inesc.rectify.RectifyLogger;
import pt.inesc.rectify.hibernate.LogDbStatement;
import pt.inesc.rectify.hibernate.LogHttpRequest;
import pt.inesc.rectify.hibernate.TeachingRoute;
import pt.inesc.rectify.utils.HttpUtils;

/**
 *
 * @author davidmatos
 */
public class DatasetGenerator {

    private int n;

    private int i;

    private static DatasetGenerator instance = null;

    private static final int N = 10;

    public static DatasetGenerator getInstance(int n) {
        if (instance == null) {
            instance = new DatasetGenerator(n);
        }
        return instance;
    }

    public DatasetGenerator(int n) {
        this.n = n;
        this.i = 0;

        if (!Rectify.getInstance().getHibSession().createQuery("FROM LogHttpRequest").list().isEmpty()) {
            this.i = (int) Rectify.getInstance().getHibSession().createCriteria(LogHttpRequest.class).setProjection(Projections.max("i")).uniqueResult();

            this.i = Math.max(this.i, this.i = (int) Rectify.getInstance().getHibSession().createCriteria(LogDbStatement.class).setProjection(Projections.max("i")).uniqueResult());
        }

        this.i++;

    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void generate() {

        RectifyLogger.info("Starting a dataset at " + i + " with " + n + " entries.");

        Query query = Rectify.getInstance().getHibSession().createQuery("FROM TeachingRoute");
        ArrayList<TeachingRoute> teachingRoutes = (ArrayList<TeachingRoute>) query.list();

        for (i = 0; i < n; i++) {

            TeachingRoute randomTeachingRoute = teachingRoutes.get((int) (Math.random() * teachingRoutes.size()));
            HttpUtils.performHttpRequest(randomTeachingRoute.getRoute());

            AsyncLogWriter.getInstance().sync();

        }

        RectifyLogger.info("Dataset successfully generated.");
    }

}
