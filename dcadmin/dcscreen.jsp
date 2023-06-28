<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<jsp:include page="scripts.jsp" />

</head>

<body onload="jspPage('./testprofitdata')">
<center>
	<!-- Retrieving model objects -->
	<div>
		<jsp:include page="nav.jsp" />
		<div class="col-md-9">
			<form id="filter">
				<div class="row mt-4">
					<div class="col-md-6 offset-md-3">
						<h5>Between Dates</h5>
						<div class="row">
							<div class="col-md-6">
								<input type="date" class="form-control" id="from" name="from">
							</div>
							<div class="col-md-6">
								<input type="date" class="form-control" id="to" name="to" onchange="jspPage('./testDateWiseProfitdata')">
							</div>
						</div>
					</div>
				</div>		
				</form>
				</div>
				</br></br>

		<div id="content"></div>
</center>
	</div>
	<script src="js/dcscreen.js"></script>

</body>
</html>