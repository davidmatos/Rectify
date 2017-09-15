
<%@page import="org.hibernate.Transaction"%>
<%@page import="java.util.List"%>
<%@page import="pt.inesc.rectify.hibernate.TeachingRoute"%>
<%@page import="org.hibernate.Query"%>
<%@page import="pt.inesc.rectify.hibernate.KbDbStatement"%>
<%@page import="pt.inesc.rectify.Rectify"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%

    // out.print(request.getParameter("trainning_routes"));
    if (request.getParameter("trainning_routes") != null) {
        String newTrainningRequestsString = request.getParameter("trainning_routes");
        Transaction transaction = Rectify.getInstance().getHibSession().beginTransaction();
        String[] newTrainningRequests = newTrainningRequestsString.split("\n");
        for (int i = 0; i < newTrainningRequests.length; i++) {
            TeachingRoute trainningRoute = new TeachingRoute(newTrainningRequests[i], false);
            Rectify.getInstance().getHibSession().save(trainningRoute);
        }
        transaction.commit();
    }
%>


<form class="form-horizontal" id="form-recording"
      action="/rectify/?p=knowledge_base" method="POST">
    <fieldset>


        <!-- Form Name -->
        <legend>Knowledge Base</legend>


        <%
            Query query = Rectify.getInstance().getHibSession().createQuery("FROM TeachingRoute");
            List<TeachingRoute> trainningRoutes = query.list();

            if (trainningRoutes.isEmpty()) { %>
        <p>There are no routes registered yet</p>


        <% } else { %>
        <div class="form-group">
            <label class="control-label" for="registered_trainning_routes">Registered Routes</label>

            <table class="table table-striped table-hover" name="registered_trainning_routes">
                <thead>
                    <tr class="bg-primary">
                        <th>#</th>
                        <th>Route</th>
                        <th>Taught</th>
                    </tr>

                </thead>
                <tbody>
                    <%
                        for (TeachingRoute tr : trainningRoutes) {
                    %>

                    <tr>
                        <td><input type="checkbox" value="<%= tr.getId()%>"></td>
                        <td><%= tr.getRoute()%></td>
                        <td><%= tr.getTaught() %></td>
                    </tr>

                    <%
                        }


                    %>

                </tbody>



            </table>
        </div>
        <% }%>


        <div class="form-group">
            <label class="control-label" for="trainning_routes">Add Teaching Routes (one per line)</label>
            <textarea class="form-control" rows="3" name="trainning_routes"></textarea>
        </div>
        <!-- Button (Double) -->
        <div class="form-group">
            <div class="col-md-4">

            </div>
            <div class="col-md-8">
                <button id="start" class="btn btn-success" type="submit">Save trainning routes</button>

            </div>

    </fieldset>
</form>

       