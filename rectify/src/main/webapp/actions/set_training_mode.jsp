<%@page import="pt.inesc.rectify.Rectify"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%

String trainingMode = request.getParameter("training_mode");

out.print("TrainingMode=" + trainingMode);

if(trainingMode.equals("ON")){
	Rectify.setModeToTraining();
}else{
	Rectify.setModeToNormal();
}

response.sendRedirect("/rectify");


%>