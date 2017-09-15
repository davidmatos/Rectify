<%-- 
    Document   : exp_ecal
    Created on : May 2, 2017, 6:46:07 PM
    Author     : davidmatos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>




<form method="GET" action="../rectify/actions/generate_dataset.jsp">
    
    <div class="form-group">
		<div class="col-sm-4">
			<span class="control-label">Size of the dataset (N):</span>
		</div>
		<div class="col-sm-8">
			<input type="text" value="100" name="n"/>
		</div>
	</div>
    
    <button id="generate_dataset" class="btn btn-success" type="submit">Generate Dataset</button>
</form>
      
 