<%-- 
    Document   : save_configurations
    Created on : Nov 23, 2016, 7:17:54 PM
    Author     : david
--%>

<%@page import="pt.inesc.rectify.Rectify"%>
<%@page import="pt.inesc.rectify.utils.HibernateUtil"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="pt.inesc.rectify.hibernate.Configuration"%>
<%@page import="org.hibernate.Session"%>

<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
    
    Transaction transaction = Rectify.hibSession.beginTransaction();

    Map params = request.getParameterMap();
    Set<String> keys = params.keySet();
    for (String key : keys) {
        Criteria cr = Rectify.hibSession.createCriteria(Configuration.class);
        cr.add(Restrictions.eq("configurationName", key));
        List results = cr.list();
        Configuration configuration = null;
        if (results.isEmpty()) {
            configuration = new Configuration(key, request.getParameter(key));
        } else {
            configuration = (Configuration) results.get(0);
        }

        Rectify.hibSession.save(configuration);
        out.print(key + "=" + request.getParameter(key) + "<br>");

    }
    transaction.commit();
    response.sendRedirect("/rectify/index.jsp");

%>

