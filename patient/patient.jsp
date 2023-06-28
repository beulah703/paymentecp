<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>DAS</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
 <link rel="stylesheet" type="text/css" href="./css/patient.css">

<jsp:include page="scripts.jsp" />
</head>

<body>
	<jsp:include page="nav.jsp" />
	<div align="center">
		<div class="container">
			<div class="card-container" align="center">

				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Todays Appointments </h5>
						<p id="toapp" class="card-text"></p>
					</div>
				</div>
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Total Tests</h5>
						<p id="totest" class="card-text"></p>
					</div>
				</div>
			</div>

			<h3>Appointments</h3>
			<div class="row mt-4">
				<div class="col-md-12">
					<table class="table table-bordered" >
						<thead>
							<tr>
								<th>Appointment</th>

								<th>Doctor</th>
								<th>Date</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody id="apptable">


						</tbody>
					</table>
				</div>
			</div>
		</div>
<div class="container">
			<div class="card-container" align="center">
		<h3>Today Diagnostic Tests</h3>
		<table class="table table-striped" >
			<thead>
				<tr>
					<th>Test ID</th>
					<th>Test Name</th>
					
				</tr>
			</thead>
			<tbody id="teststable">
				<!-- Dynamically populated with patient's diagnostic tests -->
				
			</tbody >
		</table >
	</div>
	</div>
	</div>
	<script>
		
	</script>
<div align="center">
  <div class="container">
    <div class="card-container" style="display: flex; justify-content: space-between;">

      <div class="card">
      
  <div class="card-body">
    <div class="doctor-details">
    <strong> <p class="card-text">Last Appointment</p></strong>
      <p id="doctorName" class="card-text"></p>
      <p id="appointmentFees" class="card-text"></p>
      <p id="lastVisitDate" class="card-text"></p>
       <p id="nextVisitDate" class="card-text"></p>
      <a href="#" onclick="viewPrescription()" class="card-link">View Prescription</a>
    </div>
        </div>
      </div>

      <div class="card">
        <div class="card-body">
          <h5 class="card-title">Patient Health Overview</h5>
          <div class="chart-container">
            <canvas id="healthChart" style="width: 600px; height: 600px;"></canvas>
          </div>
        </div>
      </div>

    </div>
  </div>


	<div class="container">
			<div class="card-container" align="center">
<div id="testDetailsTableContainer" style="display: none;">
  <table id="testDetailsTable" class="table table-bordered">
    <thead>
      <tr>
        <th>Test ID</th>
        <th>Test Name</th>
        <th>Method</th>
        <th>Price</th>
        <th>Report</th>
      </tr>
    </thead>
    <tbody id="testDetailsTableBody"></tbody>
  </table>
</div>
</div>
</div>
</div>



</body>
<script type="text/javascript" src="./js/patient.js"></script>

</html>