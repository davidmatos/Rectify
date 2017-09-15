<%@page import="pt.inesc.rectify.hibernate.KbDbStatement"%>
<%@page import="java.util.HashSet"%>
<%@page import="pt.inesc.rectify.Rectify"%>
<%@page import="pt.inesc.rectify.RectifyLogger"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%

Rectify.getInstance().setCurrentKbHttpRequest(null);

Rectify.getInstance().setCurrentKbDbStatements(new HashSet<KbDbStatement>());



response.sendRedirect("/rectify/?p=knowledge_base");

%>