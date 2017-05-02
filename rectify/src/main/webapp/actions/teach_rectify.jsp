<%-- 
    Document   : teach_rectify
    Created on : May 1, 2017, 4:54:52 PM
    Author     : davidmatos
--%>

<%@page import="pt.inesc.rectify.RectifyLogger"%>
<%@page import="pt.inesc.rectify.teach.RectifyTeacher"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%

RectifyTeacher rectifyTeacher = new RectifyTeacher();


RectifyLogger.info("Starting teaching phase");
rectifyTeacher.teach();
RectifyLogger.info("Finished teaching phase");

response.sendRedirect("/rectify/?p=knowledge_base");


%>