<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List,spring.orm.model.output.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Booked Appointment Data</title>
</head>
<body>


	<table class="table table-bordered">
		<thead>
			<tr>
				<th>Appointment ID</th>
				<th>Patient Name</th>
				<th>Doctor</th>
				<th>Date</th>
				<th>Specialization</th>
				<th>Status</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${data}" var="d">


				<tr>
					<td>${d.appn_id}</td>
					<td>${d.pat_name }</td>
					<td>${d.doc_name }</td>
					<td>${d.appn_sch_date}</td>
					<td>${d.spec_title}</td>
					<td>${d.appn_status }</td>
					<td><c:if test="${d.appn_status == 'YETO'}">
							<button
								onclick="location.href='./reschedule/${d.appn_id}'">Reschedule</button>
							<button onclick="location.href='./cancel/${d.appn_id}'">Cancel</button>
						</c:if></td>



				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>