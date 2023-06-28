<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DCReport</title>
<jsp:include page="scripts.jsp" />
</head>
<body onload="getPatientName()">
	<jsp:include page="nav.jsp" />

	<center>
		</br>
		</br>
		<form method="post">
			<label>patient ID:</label> 
			 <select id="pid" onchange="getPatientName()">
			 <c:forEach items="${pids}" var="pid">
									<option value="${pid}">${pid}</option>
								</c:forEach>
			 </select>
			 
			 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			 
			 <label id="name">Name</label>
			 
			  &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			 
			 <input	type="button" value="Enter" onclick="Reportpage()">
		</form>
		</br>
		</br>
		</br>
		<div id="upload"></div>
	</center>

</body>
<script src="js/DCReport.js"></script>
</html>