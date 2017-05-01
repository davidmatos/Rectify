<%@page import="pt.inesc.rectify.db.parser.ParsedQuery"%>
<%@page import="pt.inesc.rectify.Rectify"%>
<%@page import="pt.inesc.rectify.hibernate.LogDbStatement"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>


<div class="col-md-6">
    <table  class="table table-striped table-hover" >
        <thead>
            <tr class="bg-primary">
                <th>#</th>
                <th>Statement Type</th>
                <th>Tables</th>
                <th>Columns</th>
                <th>Values</th>
                <th>Where columns</th>
                <th>Where values</th>
                <!--<th>Query</th>-->
            </tr>
        </thead>
        <tbody>

            <%
                Query query = Rectify.getInstance().getHibSession().createQuery("from LogDbStatement order by id desc");
                query.setMaxResults(100);
                List<LogDbStatement> dbStatements = query.list();

                for (LogDbStatement logDbStatment : dbStatements) {
                    String q = logDbStatment.getRequest();
                    ParsedQuery pQuery = new ParsedQuery(q);
                    if (pQuery.isDisposable()) {
                        continue;
                    }
                    if (q.length() > 25) {
                        q = "..." + q.substring(q.length() - 25, q.length());
                    }
            %>
            <tr>
                <td><input type="checkbox" value="<%= logDbStatment.getId()%>"/></td>
                <td><%= pQuery.getStatementType()%></td>
                <td><%= pQuery.getTables()%></td>
                <td><%= pQuery.getColumns()%> </td>
                <td><%= pQuery.getValues()%></td>
                <td><%= pQuery.getWhereColumns()%></td>
                <td><%= pQuery.getWhereValues()%></td>
                <!--// <td></td>-->
            </tr>
            <%
                }

            %>
        </tbody>
    </table>

</div>
