<%-- 
    Document   : dashboard
    Created on : Nov 23, 2016, 4:54:36 PM
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    int step = 1;
    if (request.getParameterMap().containsKey("step")) {
        step = Integer.parseInt(request.getParameter("step"));
    }
%>


<nav class="navbar navbar-default navbar-static-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">
                Rectify
            </a>
        </div>


    </div><!-- /.container-fluid -->
</nav>
<div class="container-fluid main-container">
    <jsp:include page="configuration_menu.jsp"/>


    <jsp:include page="configuration_forms.jsp"/>

    <div class="col-md-10 content">
        <div class="col-md-5 content ">
            <% if (step > 1) {%>
            <span class="pull-right"><a href="#" class="btn btn-primary btn-default" onclick="saveForms(<%= step - 1%>)"><span class="glyphicon glyphicon-step-backward"></span> Previous</a></span>
            <% } %>
        </div>
        <div class="col-md-5 content">
            <% if (step < 4) {%>
            <a href="#" class="btn btn-primary btn-default" onclick="saveForms(<%= step + 1%>)">Next <span class="glyphicon glyphicon-step-forward"></span></a>
                <% } else {%>
            <a class="btn btn-success" onclick="saveForms(<%= step + 1%>)"><span class="glyphicon glyphicon-floppy-disk"></span> Save Configuration</a>
            <% } %>
        </div>
    </div>
    <jsp:include page="../includes/footer.jsp"/>
</div>


<script>
    function saveForms(next) {

    <% if (step == 2) { %>
        saveFormValues("webAppForm");
    <% } %>

    <% if (step == 3) { %>
        saveFormValues("httpForm");
    <% } %>

    <% if (step == 4) { %>
        saveFormValues("dbForm");
    <% }%>

        if (next < 5) {
            window.location.href = "index.jsp?step=" + next;
        } else {
            var str = "configuration/save_configurations.jsp?";
            
            str = str + "webappname=" + localStorage.getItem("webappname") + "&";
            str = str + "webappurl=" + localStorage.getItem("webappurl") + "&";
            str = str + "httplocalport=" + localStorage.getItem("httplocalport") + "&";
            str = str + "httpremotehost=" + localStorage.getItem("httpremotehost") + "&";
            str = str + "httpremoteport=" + localStorage.getItem("httpremoteport") + "&";
            str = str + "dblocalport=" + localStorage.getItem("dblocalport") + "&";
            str = str + "dbremotehost=" + localStorage.getItem("dbremotehost") + "&";
            str = str + "dbremoteport=" + localStorage.getItem("dbremoteport");
            
            window.location.href = str;
        }


    }

    function saveFormValues(formId) {
        var x = document.getElementById(formId);
        var i;
        for (i = 0; i < x.length; i++) {
//            alert(x.elements[i].name);
            localStorage.setItem(x.elements[i].name, x.elements[i].value);
        }
    }
</script>