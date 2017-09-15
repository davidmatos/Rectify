<%-- 
    Document   : clear_logs
    Created on : May 2, 2017, 7:03:35 PM
    Author     : davidmatos
--%>
<%@page import="pt.inesc.rectify.Rectify"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
      
Rectify.getInstance().getHibSession().createQuery("delete from KbDbStatement").executeUpdate();
Rectify.getInstance().getHibSession().createQuery("delete from KbHttpRequest").executeUpdate();



response.sendRedirect("/rectify/?p=main");
%>