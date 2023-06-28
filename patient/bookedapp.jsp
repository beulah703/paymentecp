<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="java.util.*, spring.orm.model.entity.*,spring.orm.model.*,java.lang.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appointments</title>
<jsp:include page="scripts.jsp" />
</head>
<body>



	<jsp:include page="nav.jsp" />
	<div align="center">
		<div class="container" align="center">
			<div class="row-md-12">
				<div class="col-md-9">
					<h3 align="center">Booked Appointments</h3>
					<form id="myform">
						<div class="row">
							<div class="col-md-2">
								<h5>From Date:</h5>
								<input type="date" class="form-control" id="from" name="from">
							</div>
							<div class="col-md-2">
								<h5>To Date:</h5>
								<input type="date" class="form-control" id="to" name="to">
							</div>
							<div class="col-md-2">
								<h5>Specialization:</h5>
								<select class="form-control" id="spec" name="spec"
									onchange="jspPage('./fetchBookData')">
									<option value="select">select</option>
									<c:forEach items="${specdata}" var="spec">
										<option value="${spec.title}">${spec.title}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-md-2">
								<h5>Status:</h5>
								<select class="form-control" id="status" name="status"
									onchange="jspPage('./fetchBookData')">
									<option value="select">select</option>
									<option value="YETO">YET TO</option>
									<option value="CMPL">Completed</option>
									<option value="CNL">Canceled</option>
								</select>

							</div>
							<div class="col-md-2">
								<h5>Doctor:</h5>
								<select class="form-control" id="doctor" name="doctor"
									onchange="jspPage('./fetchBookData')">
									<option value="select">select</option>
									<c:forEach items="${docdata}" var="doc">
										<option value="${doc.doctName}">${doc.doctName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</form>
				</div>
				</br> </br> </br>
				<div id="content"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="./js/bookedapp.js"></script>

</body>
</html>