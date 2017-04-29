<%@page import="pt.inesc.rectify.hibernate.KbDbOp"%>
<%@page import="java.util.HashSet"%>
<%@page import="pt.inesc.rectify.Rectify"%>
<%@page import="pt.inesc.rectify.RectifyLogger"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%

Rectify.getInstance().setCurrentKbHttpRequest(null);
Rectify.getInstance().setCurrentKbHttpResponse(null);
Rectify.getInstance().setCurrentKbDbOps(new HashSet<KbDbOp>());



response.sendRedirect("/rectify/?p=knowledge_base");

%>