<%-- 
    Document   : start_db_proxy
    Created on : Nov 29, 2016, 7:23:39 PM
    Author     : david
--%>


<%@page import="pt.inesc.rectify.http.proxy.HTTPProxy"%>
<%@page import="pt.inesc.rectify.db.proxy.DBProxy"%>
<%@page import="pt.inesc.rectify.Rectify"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    String urlToReturn = request.getParameter("return");

    String httpRemoteAddress = request.getParameter("httpremotehost").toString();

    int httpRemotePort = Integer.parseInt(request.getParameter("httpremoteport").toString());
    int httpLocalPort = Integer.parseInt(request.getParameter("httplocalport").toString());

    Rectify.httpProxy = new HTTPProxy(httpRemoteAddress, httpRemotePort, httpLocalPort);

   
    Rectify.httpProxy.startProxy();
    
    
    
    response.sendRedirect(urlToReturn);

%>