<%@page import="pt.inesc.rectify.RectifyConstants"%>
<%@page import="pt.inesc.rectify.utils.RectifyUtils"%>
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
				<%=Rectify.httpProxy != null ? "checked" : ""%> data-off="Disabled"
				id="http_proxy_running" />
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-4">
			<span class="control-label">HTTP Server Address</span>
		</div>
		<div class="col-sm-8">
			<input type="text" class="form-control" id="httpremotehost"
				<%=Rectify.httpProxy != null ? "disabled" : ""%>
				value="<%=RectifyUtils.getConfigurationEntry(RectifyConstants.CFG_HTTP_REMOTE_HOST)%>"
				name="<%= RectifyConstants.CFG_HTTP_REMOTE_HOST %>" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-4">
			<span class="control-label">HTTP Server Port</span>
		</div>
		<div class="col-sm-8">
			<input type="number" class="form-control" id="httpremoteport"
				<%=Rectify.httpProxy != null ? "disabled" : ""%>
				value="<%=RectifyUtils.getConfigurationEntry(RectifyConstants.CFG_HTTP_REMOTE_PORT)%>" 
				name="<%= RectifyConstants.CFG_HTTP_REMOTE_PORT %>" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-4">
			<span class="control-label">Proxy Port</span>
		</div>
		<div class="col-sm-8">
			<input type="number" class="form-control" id="httplocalport"
				<%=Rectify.httpProxy != null ? "disabled" : ""%>
				value="<%=RectifyUtils.getConfigurationEntry(RectifyConstants.CFG_HTTP_LOCAL_PORT)%>" 
				name="<%= RectifyConstants.CFG_HTTP_LOCAL_PORT %>"/>
		</div>
	</div>
	
	<button type="submit" class="btn btn-default">Save Configuration</button>
</form>