<%-- 
    Document   : configuration_menu
    Created on : Nov 23, 2016, 5:37:19 PM
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
int step = 1;
if(request.getParameterMap().containsKey("step")){
    step = Integer.parseInt(request.getParameter("step"));
}
%>
        


<div class="col-md-2 sidebar">
    <ul class="nav nav-pills nav-stacked">
        <li <%= step==1? "class=\"active\"":""%>><a href="index.jsp?step=1">1. Configuration</a></li>
        <li <%= step==2? "class=\"active\"":""%>><a href="index.jsp?step=2">2. WebApplication Details</a></li>
        <li <%= step==3? "class=\"active\"":""%>><a href="index.jsp?step=3">3. HTTP Proxy and Logs</a></li>
        <li <%= step==4? "class=\"active\"":""%>><a href="index.jsp?step=4">4. Database Proxy and Logs</a></li>
        <!--<li><a href="#">5. Link</a></li>-->
    </ul>
</div>