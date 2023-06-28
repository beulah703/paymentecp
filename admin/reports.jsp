<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reports</title>
<jsp:include page="scripts.jsp" />
</head>
<body>

	<jsp:include page="nav.jsp" />
	<div align="center">
		<h3>Reports</h3>

		<!-- Reports List -->
		<ul class="list-group">
			<li class="list-group-item">
				<h4>Doctor Wise</h4>
				<ul class="list-group">
					<li class="list-group-item">
						<h5>Doctor 1</h5>
						<ul class="list-group">
							<li class="list-group-item">Report 1</li>
							<li class="list-group-item">Report 2</li>
							<!-- Add more reports for Doctor 1 here -->
						</ul>
					</li>
					<li class="list-group-item">
						<h5>Doctor 2</h5>
						<ul class="list-group">
							<li class="list-group-item">Report 1</li>
							<li class="list-group-item">Report 2</li>
							<!-- Add more reports for Doctor 2 here -->
						</ul>
					</li>
					<!-- Add more doctors here -->
				</ul>
			</li>
			<li class="list-group-item">
				<h4>Date Wise</h4>
				<ul class="list-group">
					<li class="list-group-item">
						<h5>2023-06-01</h5>
						<ul class="list-group">
							<li class="list-group-item">Report 1</li>
							<li class="list-group-item">Report 2</li>
							<!-- Add more reports for 2023-06-01 here -->
						</ul>
					</li>
					<li class="list-group-item">
						<h5>2023-06-02</h5>
						<ul class="list-group">
							<li class="list-group-item">Report 1</li>
							<li class="list-group-item">Report 2</li>
							<!-- Add more reports for 2023-06-02 here -->
						</ul>
					</li>
					<!-- Add more dates here -->
				</ul>
			</li>
			<!-- Add more report categories here -->
		</ul>
	</div>
</body>
</html>