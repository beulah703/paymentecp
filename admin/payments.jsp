<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payments</title>

<jsp:include page="scripts.jsp" />
</head>
<body>

	<jsp:include page="nav.jsp" />
	<div align="center">
		<div class="col-md-9" align="center">
			<h3>Payments</h3>

			<!-- Payment Filters -->

			<div class="row">
				<div class="col-md-2" align="center">
					<label for="doctorNameSelect">Select Doctor:</label> <select
						class="form-control" id="doctorNameSelect">
						<option value="">All Doctors</option>
						<option value="Doctor 1">Doctor 1</option>
						<option value="Doctor 2">Doctor 2</option>
						<option value="Doctor 3">Doctor 3</option>
						<!-- Add more doctor options here -->
					</select>
				</div>
				<div class="col-md-2">
					<label for="paymentDateInput">Select Date:</label> <input
						type="date" class="form-control" id="paymentDateInput">
				</div>
			</div>



			<div class="row mt-12">
				<div class="col-md-12">
					<h3>All Payments</h3>
					<!-- Payments List -->
					<table class="table">
						<thead>
							<tr>
								<th>Payment Reference</th>
								<th>Doctor Name</th>
								<th>Payment Mode</th>
								<th>Payment Amount</th>
								<th>Payment Date</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody id="paymentsTableBody">
							<tr>
								<td>REF001</td>
								<td>Doctor 1</td>
								<td>Cash</td>
								<td>$150.00</td>
								<td>2023-06-01</td>
								<td>
									<button class="btn btn-primary">Edit</button>
									<button class="btn btn-danger">Delete</button>
								</td>
							</tr>
							<tr>
								<td>REF002</td>
								<td>Doctor 2</td>
								<td>Card</td>
								<td>$120.00</td>
								<td>2023-06-02</td>
								<td>
									<button class="btn btn-primary">Edit</button>
									<button class="btn btn-danger">Delete</button>
								</td>
							</tr>
							<!-- Add more payment rows here -->
						</tbody>
					</table>
				</div>
			</div>

		</div>
	</div>
</body>
</html>