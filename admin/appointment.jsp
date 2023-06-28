<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<title>New Appointment Form</title>
<jsp:include page="scripts.jsp" />
<!-- Include Bootstrap CSS -->

</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="container mx-auto">
		<div class="row justify-content-center">
			<div class="col-md-5 ">
				<div class="shadow p-3  bg-white rounded">

					<h2>New Appointment Form</h2>
					<form id="appointmentForm" action="./newappointment/create"
						method="post">
						<!-- Specialization -->
						<div class="form-group">
							<label for="specialization">Specialization:</label> <select
								class="form-control" id="specialization" name="specialization"
								required>
								<option value="">Select Specialization</option>
								<c:forEach items="${speclist}" var="spec">
									<option value="${spec.id}">${spec.title}</option>
								</c:forEach>
							</select>
						</div>

						<!-- Date -->
						<jsp:useBean id="now" class="java.util.Date" />
						<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"
							var="formattedDate" />
						<div class="form-group">
							<label for="appointmentDate">Date:</label> <input type="date"
								class="form-control" id="appointmentDate" name="appointmentDate"
								min="${formattedDate}" required>
						</div>

						
						
						<!-- Doctor -->
						<div class="form-group">
							<label for="doctor">Doctor:</label> <select class="form-control"
								id="doctor" name="doctor" required>
								<option value="">Select Doctor</option>
								<!-- Dynamically populated based on selected specialization -->
							</select>

						</div>


						<!-- Available Slots -->
						<div class="form-group">
							<label for="slots">Available Slots:</label> <select
								class="form-control" id="slots" name="slots" required>
								<option value="">Select Slot</option>
								<!-- Dynamically populated based on selected doctor and date -->
							</select>
						</div>

						<!-- Radio Button: Existing or New Patient -->
						<div class="form-group">
							<label for="bookingType">Booking Type:</label><br>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="bookingType"
									id="existingBooking" value="existing Patient" checked>
								<label class="form-check-label" for="existingBooking">Existing
									Patient</label>
							</div>

							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="bookingType"
									id="newBooking" value="NEW PATIENT"> <label
									class="form-check-label" for="newBooking">New Patient</label>
							</div>
							<input type="numeric" value="" hidden id="appnfee" name="appnfee">
							<input type="text" value="" hidden id="appnrefer"
								name="appnrefer"> <input type="text" value="" hidden
								id="appnmode" name="appnmode">

						</div>

						<!-- Existing Patient Form (Hidden by default) -->
						<div class="form-group" id="existingPatientForm"
							style="display: block;">
							<select class="form-control" id="existingPatient"
								name="existingPatientid" data-filter="true">
								<option value="">Select Patient</option>
								<c:forEach items="${patlist}" var="pat">
									<option value="${pat.patn_id}">${pat.patn_name}</option>
								</c:forEach>
								<!-- Populate the options dynamically with existing patient data -->
							</select>
						</div>


						<!-- New Patient Form (Hidden by default) -->
						<div class="form-group" id="newPatientForm" style="display: none;">
							<!-- New Patient Form (Hidden by default) -->
							<label for="newPatientName">Patient Name:</label> <input
								type="text" class="form-control" id="newPatientName"
								name="newPatientName"> <label for="newPatientAge">Patient
								Age:</label> <input type="text" class="form-control" id="newPatientAge"
								name="newPatientAge"> <label for="newPatientGender">Patient
								Gender:</label> <select class="form-control" id="newPatientGender"
								name="newPatientGender">
								<option value="">Select Gender</option>
								<option value="male">Male</option>
								<option value="female">Female</option>
								<option value="other">Other</option>
							</select> <label for="newPatientBgroup">Patient Blood Group:</label> <select
								type="text" class="form-control" id="newPatientBgroup"
								name="newPatientBgroup">
								<option value="">Select Blood Group</option>
								<option value="O+">O+</option>
								<option value="O-">O-</option>
								<option value="A+">A+</option>
								<option value="A-">A-</option>
								<option value="B+">B+</option>
								<option value="B-">B-</option>
								<option value="AB+">AB+</option>
								<option value="AB-">AB-</option>


							</select>

							<!-- Add more fields for capturing additional patient information -->
						</div>

						<!-- Submit Button -->
						<button type="button" class="btn btn-success"
							onclick="previewBooking()">Preview Booking</button>
					</form>

				</div>
			</div>
			<div class="col-md-5">
				<div class="form-group" align="center">
					<div id="docdetails" style="display: none;">
						<div class="card" style="width: 18rem;">
							<img id="doc_img" src="..." class="card-img-top" alt="...">
							<div class="card-body">
								<h5 id="doc_name" class="card-title">Card title</h5>
								<h6 id="doc_qual" class="card-text"></h6>
								<h6 id="doc_exp" class="card-text"></h6>
								<h6 id="doc_fee" class="card-text"></h6>
								<h6 id="doc_exp" class="card-text"></h6>

							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Preview Booking Modal -->
			<div class="modal fade" id="previewModal" tabindex="-1" role="dialog"
				aria-labelledby="previewModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="previewModalLabel">Preview
								Booking</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body" id="bookingDetails">
							<!-- Dynamically populated with booking details -->
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Cancel</button>
							<button type="button" class="btn btn-primary" onclick="payment()">Confirm
								Booking</button>
						</div>
					</div>
				</div>
			</div>
				<div class="modal fade" id="previewModal4" tabindex="-1"
					role="dialog" aria-labelledby="previewModalLabel"
					aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="previewModalLabel">Payment Failed</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body" id="bookingDetails4">
								<!-- Dynamically populated with booking details -->
							</div>
							
						</div>
					</div>
				</div>
		</div>
	</div>

	<!-- Include Bootstrap JS -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script type="text/javascript" src="./js/appointment.js"></script>

</body>
</html>