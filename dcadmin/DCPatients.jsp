<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patients</title>
<jsp:include page="scripts.jsp" />
</head>

<body>
	<jsp:include page="nav.jsp" />


	<div align="center">
		<div class="col-md-9">
			<h3 align="center">Patients View</h3>
			<div class="row mt-4">
				<div class="col-md-6 offset-md-3">
					<div class="text-center">
						<h5>Test Wise</h5>
						<select class="form-control" id="testFilter"
							onchange="applyFilters3()">

							<option value=-1>All Tests</option>

							<!-- Add more test options as needed -->
						</select>

					</div>
				</div>
			</div>
			<div class="row mt-4">
				<div class="col-md-6 offset-md-3">
					<h5>Between Dates</h5>
					<div class="row">
						<div class="col-md-6">
							<input type="date" class="form-control" id="startDateFilter">
						</div>
						<div class="col-md-6">
							<input type="date" class="form-control" id="endDateFilter"
								onchange="applyFilters2()">
						</div>
					</div>
				</div>
			</div>
			<div class="row mt-4">
				<div class="col-md-12">
					<table class="table table-bordered" id="patientTable"
						style="display: none;">
						<thead>
							<tr>
								<th>Patient Id</th>
								<th>Patient Name</th>
								<th>Test Name</th>
								<th>Method</th>
								<th>Test category</th>


								<th>Price</th>
								<th>Date</th>
							</tr>
						</thead>
						<tbody id="patientTableBody">
							<!-- Table rows will be populated dynamically -->
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<script src="js/DCPatients.js"> </script>
</body>
</html>