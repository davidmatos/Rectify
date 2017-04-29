<%-- 
    Document   : start_db_proxy
    Created on : Nov 29, 2016, 7:23:39 PM
    Author     : david
--%>


<%@page import="pt.inesc.rectify.db.proxy.DBProxy"%>
<%@page import="pt.inesc.rectify.Rectify"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    String urlToReturn = request.getParameter("return");

    String dbRemoteAddress = request.getParameter("dbremotehost").toString();

    int dbRemotePort = Integer.parseInt(request.getParameter("dbremoteport").toString());
    int dbLocalPort = Integer.parseInt(request.getParameter("dblocalport").toString());

    Rectify.getInstance().setDbProxy(new DBProxy(dbRemoteAddress, dbRemotePort, dbLocalPort));

    System.out.println("Will start db proxy to:" + dbRemoteAddress + ":" + dbRemotePort + " on port " + dbLocalPort);
    
    Rectify.getInstance().getDbProxy().startProxy();
    
    
    
    response.sendRedirect(urlToReturn);

%>