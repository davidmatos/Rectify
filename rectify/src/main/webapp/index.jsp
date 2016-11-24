<%-- 
    Document   : index
    Created on : Nov 23, 2016, 4:49:27 PM
    Author     : david
--%>

<%@page import="pt.inesc.rectify.RectifyUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<html>
    <head>
        <jsp:include page="includes/head.jsp" />

    </head>
    <body>
        <%
            
            if (RectifyUtils.isNewInstance() || request.getParameterMap().containsKey("step")) {

        %>
        <jsp:include page="configuration/configuration.jsp" />
        <%        } else {
        %>
        <jsp:include page="dashboard/dashboard.jsp" />
        <%        
            }

        %>
    </body>
</html>
