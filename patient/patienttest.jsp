<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="scripts.jsp" />
<title>DAS</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


<script
	src="https://cdn.jsdelivr.net/npm/chart.js@4.3.0/dist/chart.umd.min.js"></script>
 <link rel="stylesheet" type="text/css" href="./css/patienttest.css">
</head>



<body>
	<jsp:include page="nav.jsp" />
	<div align="center">
		<div class="container">
			<div class="row mt-4">
				<div class="col-md-12">
					<div class="filters">
						<label for="dateInput1">Select Date:</label> <input type="date"
							id="dateInput1"> </select> <label for="dateInput2">Select
							Date:</label> <input type="date" id="dateInput2">

						<button onclick="applyFilters()">Apply Filters</button>
					</div>
				</div>
			</div>

			<div class="row mt-4">
				<div class="col-md-12">
					<div class="card">
						<div class="card-body">
							<h5 class="card-title">Recent Test</h5>
							<img id="testStatusImage"
								src="https://www.cdc.gov/fungal/diseases/histoplasmosis/images/318301-A_WEB_FungalLandingPages_Histo_Diagnosis.jpg"
								style="width: 100px; height: 50px;" alt="Test Status" />
						</div>
						<div class="card-body">
							<div class="test-details">
								<p id="testName" class="card-text">Test: MRI SCAN</p>
								<p id="testId" class="card-text">Test ID: 1</p>
								<p id="testDate" class="card-text">Date: 16-06-2023</p>
							</div>
							<div class="view-link">
								<a onclick="fun1()" id="viewLink">View Test</a>
							</div>
						</div>
					</div>
				</div>
			</div>


		</div>
		<div class="container">
			<div class="card-container" align="center">
				<div id="testDetailsTableContainer">
					<table id="testDetailsTable" class="table table-bordered">
						<thead>
							<tr>
								<th>s.no</th>
								<th>Report</th>
								<th>Date</th>
								<th>content</th>
							</tr>
						</thead>
						<tbody id="testDetailsTableBody">
							<c:forEach var="img" items="${reportUrls}" varStatus="status">
								<tr>
									<td>${status.index + 1}</td>
									<td>Report ${status.index + 1}</td>
									<td>${img.getDgbl_date()}</td>
									<td><a href="#" class="displayLink"
										data-image-url="data:image/jpg;base64,${img.content}">Reports
											Image</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="./js/patienttest.js"></script>
	
</html>