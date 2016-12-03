<%@page import="pt.inesc.rectify.hibernate.KbDbOp"%>
<%@page import="pt.inesc.rectify.Rectify"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	boolean recording = false;
	boolean stop = false;

	if (request.getParameter("mode") != null) {
		if (request.getParameter("mode").equals("recording")) {
			recording = true;
			stop = false;
		}

		if (request.getParameter("mode").equals("stop")) {
			recording = false;
			stop = true;
		}
	}
%>


<%
	if (recording) {
		Rectify.setModeToTraining();
	}

	if (stop) {
		Rectify.setModeToNormal();
	}
%>






<%
	if (!recording) {
%>
<form class="form-horizontal" id="form-recording"
	action="/rectify/?p=knowledge_base" method="get">
	<fieldset>

		<!-- Form Name -->
		<legend>Knowledge Base</legend>

		<!-- Button (Double) -->
		<div class="form-group">
			<label class="col-md-4 control-label" for="button1id">Record</label>
			<div class="col-md-8">
				<input type="hidden" name="mode" value="recording" /> <input
					type="hidden" name="p" value="knowledge_base" />
				<button id="start" class="btn btn-success" type="submit">Start</button>
			</div>
		</div>

	</fieldset>
</form>

<%
	}
%>

<%
	if (recording) {
%>

<form class="form-horizontal" id="form-recording"
	action="/rectify/?p=knowledge_base" method="get">
	<fieldset>

		<!-- Form Name -->
		<legend>Knowledge Base</legend>

		<!-- Button (Double) -->
		<div class="form-group">
			<label class="col-md-4 control-label" for="button1id">Record</label>
			<div class="col-md-8">
				<input type="hidden" name="mode" value="stop" /> <input
					type="hidden" name="p" value="knowledge_base" />

				<button id="stop" class="btn btn-danger" type="submit">Stop</button>
			</div>
		</div>

	</fieldset>
</form>


<%
	}
%>
<%
	if (stop) {
%>
<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
	<div class="modal-dialog modal-lg">
		<form method="post" action="/rectify/actions/save_kb_entry.jsp">


			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Resume of the recorded HTTP request</h4>
				</div>
				<div class="modal-body">



					<%
						if (Rectify.currentKbHttpRequest != null) {
					%>

					<!-- Multiple Checkboxes -->
					<div class="form-group">
						<label class="col-md-8 control-label" for="checkboxes"><%=Rectify.currentKbHttpRequest.getUri()%></label>
						<div class="col-md-8">

							<%
								int i = 0;
								for (KbDbOp op : Rectify.currentKbDbOps) {
									i++;
							%>

							<div class="checkbox">
								<label for="<%=i%>"> <input type="checkbox"
									name="kbDbOps" value="<%=i%>" checked="checked">
									<%=op.getQuery()%>
								</label>
							</div>



							<%
								}
							%>

						</div>
					</div>

					<%
						} else {
					%>
					Didn't record any HTTP request.

					<%
						}
					%>





				</div>
				<div class="modal-footer">
					<!-- Button (Double) -->
					<div class="form-group">
						<label class="col-md-4 control-label" for="button1id">Record</label>
						<div class="col-md-8">
							<input type="submit" id="acceptLog" name="acceptLog"
								class="btn btn-success" value="accept" /> <a
								href="/rectify/actions/reject_kb_entry.jsp">Discard</a>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	$('#myModal').modal('show');
</script>
<%
	}
%>