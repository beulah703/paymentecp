<!DOCTYPE html>
<html>
<head>
<jsp:include page="scripts.jsp" />
<meta charset="UTF-8">
<title>Appointment Rescheduled Successfully</title>
<link rel="stylesheet" type="text/css" href="your-css-file.css">
</head>
<body>
	<div align="center">
		<h1>Appointment Rescheduled Successfully</h1>
		<p>Your appointment has been successfully rescheduled. Thank you!</p>
		<h6><b align="center">Redirected Shortly</b></h6>
	</div>
</body>
<script>
	$(document).ready(function() {
		// Delay in milliseconds
		var delay = 5000;

		setTimeout(function() {
			// Relative URL to redirect to
			var url = "../appointments";

			// Redirect
			window.location.href = url;
		}, delay);
	});
</script>
</html>