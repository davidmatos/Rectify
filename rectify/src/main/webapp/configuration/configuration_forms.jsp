<%-- 
    Document   : configuration_forms
    Created on : Nov 23, 2016, 6:02:52 PM
    Author     : david
--%>

<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    int step = 1;
    if (request.getParameterMap().containsKey("step")){
        step = Integer.parseInt(request.getParameter("step"));
    }
    //HashMap<String, String> configurationMap = new HashMap<>();
%>





<%
    if (step == 1) {
%>
<div class="col-md-10 content">
    <div class="panel panel-default">
        <div class="panel-heading">
            Configuration
        </div>
        <div class="panel-body">
            First it is necesary to configure Rectify


        </div>
    </div>
</div>
<% } %>







<%
    if (step == 2) {
%>
<div class="col-md-10 content">
    <div class="panel panel-default">
        <div class="panel-heading">
            WebApplication Details
        </div>
        <div class="panel-body">
            <form class="form-horizontal" id="webAppForm">
                <fieldset>

                    <!-- Form Name -->
                    <legend>WebApplication Details</legend>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="webappname">Web Application Name</label>  
                        <div class="col-md-4">
                            <input id="webappname" name="webappname" type="text" placeholder="Name of the web application to protect" class="form-control input-md" required="">
                            <span class="help-block">Name of the web application to protect</span>  
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="webappurl">URL of the web application</label>  
                        <div class="col-md-4">
                            <input id="webappurl" name="webappurl" type="text" placeholder="e.g.: www.my-web-site.com" class="form-control input-md" required="">
                            <span class="help-block">This is the URL of the site to protect</span>  
                        </div>
                    </div>

                </fieldset>
            </form>

        </div>
    </div>
</div>
<% } %>






<%
    if (step == 3) {
%>
<div class="col-md-10 content">
    <div class="panel panel-default">
        <div class="panel-heading">
            HTTP Proxy and Logs
        </div>
        <div class="panel-body">
            <form class="form-horizontal" id="httpForm">
                <fieldset>

                    <!-- Form Name -->
                    <legend>HTTP Proxy and Logs</legend>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="httplocalport">Local Port</label>  
                        <div class="col-md-4">
                            <input id="httplocalport" name="httplocalport" type="text" placeholder="Local Port e.g.: 9999" class="form-control input-md" required="">
                            <span class="help-block">This is the port that will be used by the web application</span>  
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="httpremotehost">Remote Host</label>  
                        <div class="col-md-4">
                            <input id="httpremotehost" name="httpremotehost" type="text" placeholder="Remote Host e.g.: 192.164.1.1" class="form-control input-md" required="">
                            <span class="help-block">This is the address of the machine with the httpserver</span>  
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="httpremoteport">Remote Port</label>  
                        <div class="col-md-4">
                            <input id="httpremoteport" name="httpremoteport" type="text" placeholder="Remote Port e.g.: 8080" class="form-control input-md" required="">
                            <span class="help-block">This is the port of the http server</span>  
                        </div>
                    </div>

                </fieldset>
            </form>

        </div>
    </div>
</div>
<% } %>







<%
    if (step == 4) {
%>
<div class="col-md-10 content">
    <div class="panel panel-default">
        <div class="panel-heading">
            Database Proxy and Logs
        </div>
        <div class="panel-body">
            <form class="form-horizontal" id="dbForm">
                <fieldset>

                    <!-- Form Name -->
                    <legend>Database Proxy and Logs</legend>

                    <!-- Multiple Radios -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="databaselevel">Configure Database Log</label>
                        <div class="col-md-4">
                            <div class="radio">
                                <label for="databaselevel-0">
                                    <input type="radio" name="databaselevel" id="databaselevel-0" value="yes" checked="checked">
                                    Yes
                                </label>
                            </div>
                            <div class="radio">
                                <label for="databaselevel-1">
                                    <input type="radio" name="databaselevel" id="databaselevel-1" value="nos">
                                    No
                                </label>
                            </div>
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="dblocalport">Local Port</label>  
                        <div class="col-md-4">
                            <input id="localport" name="dblocalport" type="text" placeholder="Local Port e.g.: 9999" class="form-control input-md" required="">
                            <span class="help-block">This is the port that will be used by the web application</span>  
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="dbremotehost">Remote Host</label>  
                        <div class="col-md-4">
                            <input id="remotehost" name="dbremotehost" type="text" placeholder="Remote Host e.g.: 192.164.1.1" class="form-control input-md" required="">
                            <span class="help-block">This is the address of the machine with the database server</span>  
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="dbremoteport">Remote Port</label>  
                        <div class="col-md-4">
                            <input id="remoteport" name="dbremoteport" type="text" placeholder="Remote Port e.g.: 3306" class="form-control input-md" required="">
                            <span class="help-block">This is the port of the database server</span>  
                        </div>
                    </div>

                </fieldset>
            </form>

        </div>
    </div>
</div>
<% }%>

