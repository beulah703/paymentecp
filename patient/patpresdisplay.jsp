<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<jsp:include page="scripts.jsp"/>
 <script src="https://checkout.razorpay.com/v1/checkout.js"></script>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
</head>
<body>
    <jsp:include page="nav.jsp" />
    <div align="center">
		
		<div
			class=" custom-scroll overflow-auto shadow p-3 m-3  bg-white rounded"
			style="max-height: 700px;">
			<table class="table  table-bordered table-shadow mt-4 ">
				<thead>
					<tr>
						<th>ID</th>
						<th>Date</th>
						<th>Parameter </th>
						<th>Group</th>
						<th>Value</th>
						<th>Prescription</th>
						
					</tr>
				</thead>
				<c:forEach var="s" items="${pres}">
					<tbody>
						<tr>
							<td>${s.appn_id}</td>
							<td>${s.appn_sch_date}</td>
							<td>${s.patn_parameter}</td>
							<td>${s.patn_pargroup}</td>
							<td>${s.patn_value}</td>
							
							<td><a href="#" class="displayLink"
									data-image-url="data:image/jpg;base64,${s.patn_prescription}">Prescription 
			Image</a>
						</tr>
					</tbody>
				</c:forEach>




				</tbody>
			</table>
		</div>
</body>
<script type="text/javascript" src="./js/patpresdisplay.js"></script>

</html>