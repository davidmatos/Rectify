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

    if (Rectify.getInstance().getHttpProxy() == null) {

        String httpRemoteAddress = request.getParameter("httpremotehost").toString();

        //int httpRemotePort = Integer.parseInt(request.getParameter("httpremoteport").toString());
        int httpLocalPort = Integer.parseInt(request.getParameter("httplocalport").toString());

        HTTPProxy httpProxy = new HTTPProxy(httpRemoteAddress, httpLocalPort);

        Rectify.getInstance().setHttpProxy(httpProxy);
        Rectify.getInstance().getHttpProxy().startProxy();
    }

    String urlToReturn = request.getParameter("return");
    response.sendRedirect(urlToReturn);

%>