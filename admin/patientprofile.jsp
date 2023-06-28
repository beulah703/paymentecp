<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient Profile</title>

<jsp:include page="../admin/scripts.jsp" />
<link rel="stylesheet" type="text/css" href="./css/patientprofile.css">


</head>
<body>

	<jsp:include page="nav.jsp" />

	<div class="container">
		<h3>Patient Profile Update Form</h3>
		

		<form id="profileUpdateForm" enctype="multipart/form-data" action = "./getpatientprofile" method="post"  modelAttribute="ProfileUpdateForm">
			<div class="form-group">
				
					
					Patient ID: <select class="form-control" id="patientId" name="patientId" onchange="getappn()">
                                <option value="main">All Patients Id</option>
                                <!-- Add more category options as needed -->
                            </select>
			</div>
			<div class="form-group">
			Appointment ID: <select class="form-control" id="appnId" name="Appnid">
                                <option value="main">All Appointments</option>
                                <!-- Add more category options as needed -->
                            </select>
			</div>
			<div class="form-group">
				<label for=" parameter">Parameter:</label> <input type="text"
					id=" parameter" name=" parameter" required>
			</div>
			<div class="form-group">
				<label for="patgroup">Parameter Group:</label> <input type="text"
					id="patgroup" name="Patgroup"  required>
			</div>
			<div class="form-group">
				<label for="value">Value:</label> <input type="text"
					id="value" name="value" required>
			</div>
	
			
			<div class="form-group">
				<label for="reportsInput">Upload Prescriptions:</label> <input type="file"
					id="reportsInput" name="reportsInput" accept="image/*">
			</div>
			<div class="form-group">
				<button type="submit">Update Profile</button>
			</div>
		</form>
	</div>


<script src="./js/patientprofile.js"></script>


</body>
</html>
