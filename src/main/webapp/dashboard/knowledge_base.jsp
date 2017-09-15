<%@page import="pt.inesc.rectify.hibernate.KbHttpRequest"%>
<%@page import="java.util.ArrayList"%>
<%@page import="pt.inesc.rectify.Rectify"%>
<%@page import="org.hibernate.Query"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
    
    
    
    
    <jsp:include page="../forms/kb_form.jsp"/>
    
    
    
    <%
    
    Query query = Rectify.getInstance().getHibSession().createQuery("FROM KbHttpRequest");
    ArrayList<KbHttpRequest> kbHttpRequests = (ArrayList<KbHttpRequest>)query.list();
    

    %>
    
    
    <div class="form-group">
            <label class="control-label" for="registered_trainning_routes">Registered Routes</label>

            <table class="table table-striped table-hover" name="registered_trainning_routes">
                <thead>
                    <tr class="bg-primary">
                        <th>#</th>
                        <th>Uri</th>
                        <th>Nr of DB Stmts</th>
                    </tr>

                </thead>
                <tbody>
                    <%
                        for (KbHttpRequest hr : kbHttpRequests) {
                    %>

                    <tr>
                        <td><input type="checkbox" value="<%= hr.getId()%>"></td>
                        <td><%= hr.getUri()%></td>
                        <td><%= hr.getKbDbStatements().size() %></td>
                    </tr>

                    <%
                        }


                    %>

                </tbody>



            </table>
        </div>
    
    