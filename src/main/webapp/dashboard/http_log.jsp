<%@page import="pt.inesc.rectify.http.parser.HttpParser"%>
<%@page import="pt.inesc.rectify.Rectify"%>
<%@page import="org.hibernate.Query"%>
<%@page import="java.util.ArrayList"%>
<%@page import="pt.inesc.rectify.hibernate.LogHttpRequest"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>




    <%
    
    Query query = Rectify.getInstance().getHibSession().createQuery("FROM LogHttpRequest");
    ArrayList<LogHttpRequest> logHttpRequests = (ArrayList<LogHttpRequest>)query.list();
    

    %>
    
    
    <div class="form-group">
            <label class="control-label" for="registered_trainning_routes">Registered Routes</label>

            <table class="table table-striped table-hover" name="registered_trainning_routes">
                <thead>
                    <tr class="bg-primary">
                        <th>#</th>
                        <th>Uri</th>
                        <th>Host</th>
                        <th>TS</th>
                        <th>Method</th>
                    </tr>

                </thead>
                <tbody>
                    <%
                        for (LogHttpRequest hr : logHttpRequests) {
                    %>

                    <tr>
                        <td><input type="checkbox" value="<%= hr.getId()%>"></td>
                        <td><%= hr.getUri()%></td>
                        <td><%= hr.getHost() %></td>
                        <td><%= hr.getTs() %></td>
                        <td><%= HttpParser.getMethod(hr.getRequest()) %></td>
                    </tr>

                    <%
                        }


                    %>

                </tbody>



            </table>
        </div>
    
    