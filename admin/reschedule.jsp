<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page
	import="java.util.*, spring.orm.model.entity.*,spring.orm.model.output.RescheduleAppointmentOutput"%>
<%@ page import="java.text.SimpleDateFormat"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reschedule Appointment</title>
<jsp:include page="scripts.jsp" />
</head>
<body>


	<div align="center">
		<div class="container" align="center">
			<div class="row-md-12">
				<div class="col-md-6">
					<h3 align="center">Reschedule Appointment</h3>
					<form action="../reschedule/success" method="POST">
						<input type="text" id="appointmentId" name="appointmentId"
							value=${app.app_id } hidden>

						<div class="form-group">
							<label for="patientName">Patient Name</label> <input type="text"
								class="form-control" id="patientName"
								value="${app.patient.getPatn_name()}" disabled>
						</div>
						<div class="form-group">
							<label for="doctorName">Doctor Name</label> <input type="text"
								class="form-control" id="doctorName"
								value="${app.doctor.doctName}" disabled>
						</div>
						<div class="form-group">
							<input id="doctid" type="number" hidden class="form-control"
								value="${app.doctor.doctId}">
						</div>

						<div class="form-group">
							<label for="currentDateTime">Current Date and Time</label> <input
								type="text" class="form-control" id="currentDateTime"
								value="${app.app_sch_date} ${app.slot}" disabled>
						</div>
						<jsp:useBean id="now" class="java.util.Date" />
						<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"
							var="formattedDate" />
						<div class="form-group">
							<label for="rescheduleDate">Reschedule Date</label> <input
								type="date" class="form-control" id="rescheduleDate"
								name="rescheduleDate" min="${formattedDate}" required>
						</div>
						<div class="form-group">
							<label for="slots">Available Slots:</label> <select
								class="form-control" id="slots" name="slot" required>
								<option value="">Select Slot</option>
								<!-- Dynamically populated based on selected doctor and date -->
							</select>
						</div>
						<button type="submit" class="btn btn-primary">Reschedule
							Appointment</button>
						<button type="button" onclick="history.back();"
							class="btn btn-primary">Back</button>
					</form>
				</div>
			</div>
		</div>
	</div>


	

</body>
<script type="text/javascript" src="../js/reschedule.js"></script>
</html>