<%@page import="pt.inesc.rectify.utils.RectifyUtils"%>
<%@page import="pt.inesc.rectify.RectifyConstants"%>
<%@page import="pt.inesc.rectify.Rectify"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<form class="form-horizontal" method="post" action="../rectify/actions/save_config.jsp">
	<div class="form-group">
		<div class="col-sm-4">
			<span class="control-label">Proxy state:</span>
		</div>
		<div class="col-sm-8">
			<input type="checkbox" data-toggle="toggle" data-on="Enabled"
                               data-off="Disabled" <%=Rectify.getInstance().getDbProxy() != null ? "checked" : ""%>
				id="db_proxy_running" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-4">
			<span class="control-label">DB Server Address</span>
		</div>
		<div class="col-sm-8">
			<input type="text" class="form-control" id="dbremotehost"
                               <%=Rectify.getInstance().getDbProxy() != null ? "disabled" : ""%>
				value="<%=RectifyUtils.getConfigurationEntry(RectifyConstants.CFG_DB_REMOTE_HOST)%>" 
				name="<%= RectifyConstants.CFG_DB_REMOTE_HOST %>"/>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-4">
			<span class="control-label">DB Server Port</span>
		</div>
		<div class="col-sm-8">
			<input type="number" class="form-control" id="dbremoteport"
                               <%=Rectify.getInstance().getDbProxy() != null ? "disabled" : ""%>
                               value="<%=RectifyUtils.getConfigurationEntry(RectifyConstants.CFG_DB_REMOTE_PORT)%>" 
				name="<%= RectifyConstants.CFG_DB_REMOTE_PORT %>"/>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-4">
			<span class="control-label">Proxy Port</span>
		</div>
		<div class="col-sm-8">
			<input type="number" class="form-control" id="dblocalport"
				<%=Rectify.getInstance().getDbProxy() != null ? "disabled" : ""%>
				value="<%=RectifyUtils.getConfigurationEntry(RectifyConstants.CFG_DB_LOCAL_PORT)%>" 
				name="<%= RectifyConstants.CFG_DB_LOCAL_PORT %>"/>
		</div>
	</div>
	<button type="submit" class="btn btn-default">Save Configuration</button>
</form>




<script>
	$(function() {
		$('#db_proxy_running').change(
				function() {
					if ($(this).prop('checked')) {
						//start db proxy
						var dbRemoteHost = $('#dbremotehost').val();
						var dbRemotePort = $('#dbremoteport').val();
						var dbLocalPort = $('#dblocalport').val();
						$(location).attr(
								'href',
								'actions/start_db_proxy.jsp?return=/rectify&dbremotehost='
										+ dbRemoteHost + '&dbremoteport='
										+ dbRemotePort + '&dblocalport='
										+ dbLocalPort);
					} else {
						//stopt db proxy
						$(location).attr('href',
								'actions/stop_db_proxy.jsp?return=/rectify');
					}
				})

	})
</script>