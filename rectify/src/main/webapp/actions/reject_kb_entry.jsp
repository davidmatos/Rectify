<%@page import="java.util.HashSet"%>
<%@page import="pt.inesc.rectify.Rectify"%>
<%@page import="pt.inesc.rectify.RectifyLogger"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%

Rectify.currentKbHttpRequest = null;
Rectify.currentKbHttpResponse = null;
Rectify.currentKbDbOps = new HashSet<>();



response.sendRedirect("/rectify/?p=knowledge_base");

%>