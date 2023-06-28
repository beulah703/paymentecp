<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page
	import="spring.orm.model.*, java.util.*,spring.orm.model.output.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="scripts.jsp" />
<title>Doctors</title>
<link rel="stylesheet" type="text/css" href="./css/doctor.css">

</head>
<body>
	<jsp:include page="nav.jsp" />
	<div align="center">
		<h2>Doctors List</h2>
		<div
			class=" custom-scroll overflow-auto shadow p-3 m-3  bg-white rounded"
			style="max-height: 300px;">
			<table class="table  table-bordered table-shadow mt-4 ">
				<thead style="position: sticky; top: 0; background-color: white; z-index: 1;">
					<tr>
						<th>Doctor ID</th>
						<th>Doctor Name</th>
						<th>Qualification</th>
						<th>Experience</th>
						<th>Photo</th>
						<th>Fee</th>
						<th>Available on</th>
						<th>From Time</th>
						<th>To Time</th>
						<th>Avg appointment Time</th>

						<th>Actions</th>
					</tr>
				</thead>
				<c:forEach var="s" items="${docsche}">
					<tbody>
						<tr>
							<td>${s.doctId}</td>
							<td>${s.doctName}</td>
							<td>${s.doctQual}</td>
							<td>${s.doctExp}</td>
							<td><img src="data:image/png;base64,${s.doctPhoto}"
								class="profile-pic" /></td>
							<td>${s.doctCfee}</td>
							<td>${s.weekday}</td>
							<td>${s.timeFrom}</td>
							<td>${s.timeTo}</td>
							<td>${s.averageAppointmentTime}</td>

							<td>
								<button class="btn btn-primary" onclick="getspec('${s.doctId}')">Edit</button>
								<button class="btn btn-danger"
									onclick="deletespec('${s.doctId}')">Delete</button>
							</td>
						</tr>
					</tbody>
				</c:forEach>




				</tbody>
			</table>
		</div>
		<br>
		<div class="form-container">
			<button type="button" class="btn btn-primary" id="show-btn-doc"
				onclick="onclickdoc()">Add Doctor</button>
			<div class="container">

				<div class="row justify-content-center">
					<div class="col-md-5">
						<div id="doctorForm" style="display: none;"
							class="shadow p-3  bg-white rounded">

							<form action="./savedoc" method="post"
								enctype="multipart/form-data" modelAttribute="doctorForm">
								<br>
								<h2>Add Doctor</h2>
								<div class="form-group">
									<label for="doctorName">Doctor Name</label> <input type="text"
										class="form-control" id="doctorName" name="docname">
								</div>
								<div class="form-group">
									<label for="specialization">Specialization</label> <select
										class="form-control" id="specialization" name="docspec">
										<c:forEach var="spec" items="${speclist}">
											<option value=${spec.id }>${spec.title}</option>
										</c:forEach>

									</select>
								</div>
								<div class="form-group">
									<label for="qual">Qualification</label> <input type="text"
										class="form-control" id="qual" name="docqual">
								</div>
								<div class="form-group">
									<label for="exp">Experience</label> <br> <input
										type="text" id="exp" name="docexp" id="exp"
										class="form-control" pattern="[0-9]+" required>
								</div>
								<div class="form-group">
									<label for="photo">Photo</label> <input type="file" id="photo"
										name="docphoto" class="form-control" accept="image/*">
								</div>
								<div class="form-group">
									<label for="days">Select days:</label><br>
									<div class="form-check form-check-inline">
										<input class="form-check-input" name="weekday" type="checkbox"
											id="ALL" value="ALL" onchange="limitSelecction()"> <label
											class="form-check-label" for="ALL">All</label>
									</div>
									<div class="form-check form-check-inline">
										<input class="form-check-input" name="weekday" type="checkbox"
											id="monday" value="MON" onchange="limitDaysSelection()">
										<label class="form-check-label" for="monday">Monday</label>
									</div>
									<div class="form-check form-check-inline">
										<input class="form-check-input" name="weekday" type="checkbox"
											id="tuesday" value="TUE" onchange="limitDaysSelection()">
										<label class="form-check-label" for="tuesday">Tuesday</label>
									</div>
									<div class="form-check form-check-inline">
										<input class="form-check-input" name="weekday" type="checkbox"
											id="wednesday" value="WED" onchange="limitDaysSelection()">
										<label class="form-check-label" for="wednesday">Wednesday</label>
									</div>
									<div class="form-check form-check-inline">
										<input class="form-check-input" name="weekday" type="checkbox"
											id="thursday" value="THU" onchange="limitDaysSelection()">
										<label class="form-check-label" for="thursday">Thursday</label>
									</div>
									<div class="form-check form-check-inline">
										<input class="form-check-input" name="weekday" type="checkbox"
											id="friday" value="FRI" onchange="limitDaysSelection()">
										<label class="form-check-label" for="friday">Friday</label>
									</div>
									<div class="form-check form-check-inline">
										<input class="form-check-input" name="weekday" type="checkbox"
											id="saturday" value="SAT" onchange="limitDaysSelection()">
										<label class="form-check-label" for="saturday">Saturday</label>
									</div>
									<div class="form-check form-check-inline">
										<input class="form-check-input" name="weekday" type="checkbox"
											id="sunday" value="SUN" onchange="limitDaysSelection()">
										<label class="form-check-label" for="sunday">Sunday</label>
									</div>
								</div>
								<div class="form-group" class="form-control">
									<label for="timeAvailable">Select Available Time</label> <br>
									<label for="from">From</label> <input type="time" id="appt"
										name="docfrom"> <label for="to">To</label> <input
										type="time" id="appt" name="docto">
								</div>
								<div class="form-group">
									<label for="avgtime">Average Time</label> <input type="text"
										class="form-control" id="eavgtime" pattern="[0-9]+"
										name="docavgtime">
								</div>
								<div class="form-group">
									<label for="fee">Fee</label> <input type="number"
										class="form-control" id="fee" name="docfee" min="0">
								</div>
								<button type="submit" class="btn btn-primary">Submit</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="form-container">
		<div class="container">

			<div class="row justify-content-center">
				<div class="col-md-5">
					<div id="EditForm" style="display: none;"
						class="shadow p-3  bg-white rounded">

						<form action="./updatedoc" method="post"
							enctype="multipart/form-data" modelAttribute="doctorForm"
							id="edit-docForm">
							<br>
							<h2>Add Doctor</h2>
							<div class="form-group">
								<label for="doctorName">Doctor Name</label> <input type="text"
									class="form-control" id="edoctorName" name="docname">
							</div>
							<div class="form-group">
								<label for="specialization">Specialization</label> <select
									class="form-control" id="especialization" name="docspec">
									<option value=${spec.id }>${spec.title}</option>
									<c:forEach var="spec" items="${speclist}">
										<option value=${spec.id }>${spec.title}</option>
									</c:forEach>

								</select> <input type="number" class="form-control" id="did"
									name="doc_id" hidden>
							</div>
							<div class="form-group">
								<label for="qual">Qualification</label> <input type="text"
									class="form-control" id="equal" name="docqual">
							</div>
							<div class="form-group">
								<label for="exp">Experience</label> <br> <input type="text"
									name="docexp" id="eexp" class="form-control" pattern="[0-9]+"
									required>
							</div>
							<div class="form-group">
								<label for="photo">Photo</label> <input type="file" id="ephoto"
									name="docphoto" class="form-control" accept="image/*">
							</div>
							<div class="form-group">
								<label for="days">Select days:</label><br>
								<div class="form-check form-check-inline">
									<input class="form-check-input" name="weekday" type="checkbox"
										id="eALL" value="ALL" onchange="limitSelecction()"> <label
										class="form-check-label" for="ALL">All</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" name="weekday" type="checkbox"
										id="monday" value="MON" onchange="limitDaysSelection()">
									<label class="form-check-label" for="monday">Monday</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" name="weekday" type="checkbox"
										id="tuesday" value="TUE" onchange="limitDaysSelection()">
									<label class="form-check-label" for="tuesday">Tuesday</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" name="weekday" type="checkbox"
										id="wednesday" value="WED" onchange="limitDaysSelection()">
									<label class="form-check-label" for="wednesday">Wednesday</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" name="weekday" type="checkbox"
										id="thursday" value="THU" onchange="limitDaysSelection()">
									<label class="form-check-label" for="thursday">Thursday</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" name="weekday" type="checkbox"
										id="friday" value="FRI" onchange="limitDaysSelection()">
									<label class="form-check-label" for="friday">Friday</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" name="weekday" type="checkbox"
										id="saturday" value="SAT" onchange="limitDaysSelection()">
									<label class="form-check-label" for="saturday">Saturday</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" name="weekday" type="checkbox"
										id="sunday" value="SUN" onchange="limitDaysSelection()">
									<label class="form-check-label" for="sunday">Sunday</label>
								</div>
							</div>
							<div class="form-group" class="form-control">
								<label for="timeAvailable">Select Available Time</label> <br>
								<label for="from">From</label> <input type="time" id="eappt"
									name="docfrom"> <label for="to">To</label> <input
									type="time" id="appt" name="docto">
							</div>
							<div class="form-group">
								<label for="avgtime">Average Time</label> <input type="text"
									class="form-control" id="eavgtime" pattern="[0-9]+"
									name="docavgtime">
							</div>
							<div class="form-group">
								<label for="fee">Fee</label> <input type="number"
									class="form-control" id="efee" name="docfee" min="0">
							</div>

							<button id="add-spec-btn" type="submit" class="btn btn-primary">Add</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>



</body>

<script src="./js/doctor.js"></script>



</html>