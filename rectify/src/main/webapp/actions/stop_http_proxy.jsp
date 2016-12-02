<%-- 
    Document   : start_db_proxy
    Created on : Nov 29, 2016, 7:23:39 PM
    Author     : david
--%>


<%@page import="pt.inesc.rectify.db.proxy.DBProxy"%>
<%@page import="pt.inesc.rectify.Rectify"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    String urlToReturn = request.getParameter("return").toString();

    Rectify.dbProxy.stopProxy();

    Rectify.dbProxy = null;

    response.sendRedirect(urlToReturn);

%>