<%-- 
    Document   : main
    Created on : Nov 28, 2016, 11:42:12 AM
    Author     : david
--%>

<%@page import="pt.inesc.rectify.hibernate.RectifyLog"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Session"%>
<%@page import="pt.inesc.rectify.utils.HibernateUtil"%>
<%@page import="pt.inesc.rectify.utils.RectifyUtils"%>
<%@page import="pt.inesc.rectify.Rectify"%>
<%@page import="pt.inesc.rectify.RectifyConstants"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
	<div class="col-md-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">HTTP Proxy</h3>
			</div>
			<div class="panel-body">
				<jsp:include page="../forms/http_form.jsp" />
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">HTTP Log</h3>
			</div>
			<div class="panel-body">
				<div>
					<div class="form-group pull-right">
						<input type="text" class="search form-control"
							placeholder="Search request..." />
					</div>
					<span class="counter pull-right"></span>
					<table class="table table-hover table-bordered results">
						<thead>
							<tr>
								<th>#</th>
								<th class="col-md-5 col-xs-4">Endpoint</th>
								<th class="col-md-4 col-xs-3">Host</th>
								<th class="col-md-3 col-xs-5">TimeStamp</th>
							</tr>
							<tr class="warning no-result">
								<td colspan="4"><i class="fa fa-warning"></i>No result</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">1</th>
								<td>www.ist.pt</td>
								<td>192.168.2.4</td>
								<td>10/2/2015 15:35:35.0</td>
							</tr>
							<tr>
								<th scope="row">2</th>
								<td>www.ist.pt</td>
								<td>192.168.2.4</td>
								<td>10/2/2015 15:35:35.0</td>
							</tr>
							<tr>
								<th scope="row">3</th>
								<td>www.ist.pt</td>
								<td>192.168.2.4</td>
								<td>10/2/2015 15:35:35.0</td>
							</tr>
							<tr>
								<th scope="row">4</th>
								<td>www.ist.pt</td>
								<td>192.168.2.4</td>
								<td>10/2/2015 15:35:35.0</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">DB Proxy</h3>
			</div>
			<div class="panel-body">
				<jsp:include page="../forms/db_form.jsp" />
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">DB Log</h3>
			</div>
			<div class="panel-body">
				<div>
					<div class="form-group pull-right">
						<input type="text" class="search form-control"
							placeholder="Search query..." />
					</div>
					<span class="counter pull-right"></span>
					<table class="table table-hover table-bordered results">
						<thead>
							<tr>
								<th>#</th>
								<th class="col-md-5 col-xs-4">Query Type</th>
								<th class="col-md-4 col-xs-3">Affected Table</th>
								<th class="col-md-3 col-xs-5">TimeStamp</th>
							</tr>
							<tr class="warning no-result">
								<td colspan="4"><i class="fa fa-warning"></i>No result</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">1</th>
								<td>INSERT</td>
								<td>users</td>
								<td>10/2/2015 15:35:35.0</td>
							</tr>
							<tr>
								<th scope="row">2</th>
								<td>INSERT</td>
								<td>users</td>
								<td>10/2/2015 15:35:35.0</td>
							</tr>
							<tr>
								<th scope="row">3</th>
								<td>INSERT</td>
								<td>users</td>
								<td>10/2/2015 15:35:35.0</td>
							</tr>
							<tr>
								<th scope="row">4</th>
								<td>UPDATE</td>
								<td>users</td>
								<td>10/2/2015 15:35:35.0</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>



<div class="row">
	<div class="col-md-12">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Rectify Log</h3>
			</div>
			<div class="panel-body">

				<table class="table">
					<thead>
						<tr>
							<th>Timestamp</th>
							<th>Level</th>
							<th>Message</th>
						</tr>
					</thead>
					<tbody>
						<%
							List<RectifyLog> logs = Rectify.hibSession.createQuery("FROM RectifyLog order by ts desc")
									.setMaxResults(100).list();
							for (RectifyLog log : logs) {
						%>
						<tr
							class="<%=log.getLevel().equals("INFO")
						? "success"
						: log.getLevel().equals("WARNING") ? "warning" : log.getLevel().equals("QUERY")
								? "info"
								: log.getLevel().equals("HTTP") ? "default" : "danger"%>">
							<td><%=log.getTs()%></td>
							<td><%=log.getLevel()%></td>
							<td><%=log.getMessage()%></td>
						</tr>
						<%
							}
						%>

					</tbody>
				</table>

			</div>
		</div>
	</div>
</div>


<script>
	$(function() {
		$('#trainning_mode').change(
				function() {

					var training_mode = 'OFF';
					if ($(this).prop('checked')) {
						training_mode = 'ON';
					}

					$(location).attr(
							'href',
							'actions/set_training_mode.jsp?training_mode='
									+ training_mode);

				})

	})
</script>