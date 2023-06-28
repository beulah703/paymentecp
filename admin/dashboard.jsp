<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DAS</title>
<jsp:include page="scripts.jsp" />
</head>
<body>
	<div>
		<jsp:include page="nav.jsp" />
		<div align="center">
			<div class="card-container" align="center">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Total Doctors</h5>
						<p class="card-text">${cards.doccount }</p>
					</div>
				</div>
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Todays Appointments</h5>
						<p class="card-text">${cards.total_appointments}</p>
					</div>
				</div>
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Total Specializations</h5>
						<p class="card-text">${cards.total_specializations}</p>
					</div>
				</div>
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Total Payments</h5>
						<p class="card-text">$ ${cards.total_payments}</p>
					</div>
				</div>
				<!-- Add more doctors here -->
			</div>

			<div class="container ">
				<div align="center">
					<div class="row">
						<div class="col-md-6">
							<div class="tables">
								<div class="container">
									<table class="table">
										<thead>
											<h3 align="center">Appointments</h3>
											<tr>
												<th>S.NO</th>
												<th>Doctor Name</th>
												<th>Patient Name</th>
												<th>Schedule Date</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${App }" var="a" varStatus="sno">
												<tr>
													<td>${sno.index+1 }</td>
													<td>${a.docname}</td>
													<td>${a.patientname}</td>
													<td>${a.bookedDate}</td>
												</tr>
											</c:forEach>

										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="tables">
								<div class="container">
									<table class="table">
										<thead>
											<h3 align="center">Payments</h3>
											<tr>
												<th>Payment Reference</th>
												<th>Payment Mode</th>
												<th>Pay Amount</th>
												<th>Doctor Name</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${ prof}" var="p">
											<tr>
												<td>${p.payref }</td>
												<td>${p.paymode }</td>
												<td>${p.payamount }</td>
												<td>${p.docname }</td>
											</tr>
											</c:forEach>
											<!-- Add more rows here -->
										</tbody>

									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>