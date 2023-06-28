<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*, spring.orm.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="scripts.jsp" />
<title>Home</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="row mt-4">
		<div class="col-md-6 offset-md-3">
			<div class="text-center">
				<h5>Doctor Wise</h5>
				<select class="form-control" id="docFilter"
					onchange="getdocwisepay()">
					<option value=0>All Doctors</option>
					<c:forEach var="d" items="${docs}">
						<option value="${d.id}" id="${d.id}">${d.name}</option>

						<!-- Your other HTML code within the iteration -->

					</c:forEach>


					<!-- Add more test options as needed -->
				</select>
			</div>
		</div>
	</div>

	<div class="row mt-4">
		<div class="col-md-6 offset-md-3">
			<div class="text-center">
				<h5>Specialization Wise</h5>
				<select class="form-control" id="specFilter"
					onchange="RevenueSplit()">
					<option value="null">All Specializations</option>
					<c:forEach var="s" items="${specs}">
						<option value="${s.id}" id="${d.id}">${s.title}</option>

						<!-- Your other HTML code within the iteration -->

					</c:forEach>


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
						onchange="getdatewisepay()">
				</div>
			</div>
		</div>
	</div>
	<div class="row mt-4">
		<div class="col-md-12">
			<table class="table table-bordered" id="patientTable">
				<thead>
					<tr>
						<th>Doctor/Specialization</th>

						<th>Doctor Pay</th>
						<th>Profit</th>
					</tr>
				</thead>
				<tbody id="patientTableBody">


				</tbody>
			</table>
		</div>
	</div>
	</div>


	

	<script src="./js/adminpaymentspage.js"></script>
	

</body>
</html>