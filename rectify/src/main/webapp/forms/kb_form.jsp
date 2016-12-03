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
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Modal Header</h4>
			</div>
			<div class="modal-body">



				<!-- Multiple Checkboxes -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="checkboxes">Multiple
						Checkboxes</label>
					<div class="col-md-4">

						<%
							for (KbDbOp op : Rectify.currentKbDbOps) {
						%>

						<div class="checkbox">
							<label for="checkboxes-1"> <input type="checkbox"
								name="checkboxes" id="checkboxes-1" value="2"> <%=op.getQuery()%>
							</label>
						</div>



						<%
							}
						%>




						<!-- 						<div class="checkbox"> -->
						<!-- 							<label for="checkboxes-1"> <input type="checkbox" -->
						<!-- 								name="checkboxes" id="checkboxes-1" value="2"> Option -->
						<!-- 								two -->
						<!-- 							</label> -->
						<!-- 						</div> -->
					</div>
				</div>







			</div>
			<div class="modal-footer">
				<!-- Button (Double) -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="button1id">Record</label>
					<div class="col-md-8">
						<button id="acceptLog" name="acceptLog" class="btn btn-success">Accept</button>
						<button id="rejectLog" name="rejectLog"
							class="btn btn-danger disabled">Reject</button>
					</div>
				</div>
			</div>
		</div>

	</div>
</div>
<script type="text/javascript">
	$('#myModal').modal('show');
</script>
<%
	}
%>