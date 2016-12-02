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
                   
                    <a class="navbar-brand" href="#">
                        Rectify
                    </a>
                </div>

               
            </div><!-- /.container-fluid -->
        </nav>
        <div class="container-fluid main-container">
            <jsp:include page="sidebar.jsp"/>
            <div class="col-md-10 content">
                <jsp:include page="main.jsp"/>
            </div>
            <jsp:include page="../includes/footer.jsp"/>
        </div>
