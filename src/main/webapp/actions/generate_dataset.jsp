<%-- 
    Document   : generate_dataset
    Created on : May 2, 2017, 6:17:34 PM
    Author     : davidmatos
--%>

<%@page import="pt.inesc.rectify.exp_eval.DatasetGenerator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<%
    int n = Integer.parseInt(request.getParameter("n"));

    DatasetGenerator dg = DatasetGenerator.getInstance(n);
    dg.generate();

    response.sendRedirect("/rectify/?p=main");

%>
