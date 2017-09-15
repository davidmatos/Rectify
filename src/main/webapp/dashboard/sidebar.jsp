<%-- 
    Document   : sidebar
    Created on : Nov 28, 2016, 10:58:35 AM
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%

String p = "main";
if(request.getParameterMap() !=null){
if(request.getParameterMap().containsKey("p")){
	p = request.getParameter("p");
}
	
}

%>

<div class="col-md-2 sidebar">
    <ul class="nav nav-pills nav-stacked">
        <li <%= p.equals("main") ? "class=\"active\"": ""%>><a href="?p=main">Home</a></li>
        <li <%= p.equals("http_log") ? "class=\"active\"": ""%>><a href="?p=http_log">HTTP Log</a></li>
        <li <%= p.equals("db_log") ? "class=\"active\"": ""%>><a href="?p=db_log">DB Log</a></li>
        <li <%= p.equals("knowledge_base") ? "class=\"active\"": ""%>><a href="?p=knowledge_base">Knowledge Base</a></li>
        <li <%= p.equals("trainning") ? "class=\"active\"": ""%>><a href="?p=trainning">Trainning</a></li>
        
    </ul>
</div>