<%-- 
    Document   : dashboard
    Created on : Nov 23, 2016, 4:54:36 PM
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-default navbar-static-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">

            <a class="navbar-brand" href="#"> Rectify </a>
        </div>



        <%
            String p = "main";
            if (request.getParameterMap().containsKey("p")) {
                p = request.getParameter("p");
            }

            p = p + ".jsp";
        %>



        <ul class="nav navbar-nav navbar-right">
            <li <%= p.equals("main") ? "class=\"active\"" : ""%>><a href="?p=main">Home</a></li>
            <li <%= p.equals("http_log") ? "class=\"active\"" : ""%>><a href="?p=http_log">HTTPLog</a></li>
            <li <%= p.equals("db_log") ? "class=\"active\"" : ""%>><a href="?p=db_log">DBLog</a></li>
            <li <%= p.equals("knowledge_base") ? "class=\"active\"" : ""%>><a href="?p=knowledge_base">Knowledge Base</a></li>
            <li <%= p.equals("trainning") ? "class=\"active\"" : ""%>><a href="?p=trainning">Trainning</a></li>
        </ul>


    </div>
    <!-- /.container-fluid -->
</nav>
<div class="container-fluid main-container">
    <%--<jsp:include page="sidebar.jsp" />--%>
    <div class="col-md-12 content">

        <jsp:include page="<%= p%>" flush="true" />



    </div>

</div>
<jsp:include page="../includes/footer.jsp" />