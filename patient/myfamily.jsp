<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*, spring.orm.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DAS</title>
<jsp:include page="scripts.jsp" /> 
<link rel="stylesheet" type="text/css" href="./css/myfamily.css">

</head>
<body>
	<jsp:include page="nav.jsp" />

	<div align="center">
		<div class="container">
			<h1>My Profile</h1>

			<h2 class="mt-4">Family Members</h2>
			<table class="table mt-4">
				<thead>
					<tr>
						<th>Name</th>
						<th>Age</th>
						<th>Gender</th>
						<th>Blood Group</th>
						<th>Relation</th>
						<!-- 		<th>Action</th>
 -->
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${members}" var="member">
						<tr>
							<td>${member.pfmbName}</td>
							<td>${member.pfmbAge}</td>
							<td>${member.pfmbGender}</td>
							<td>${member.pfmbbgroup}</td>
							<td>${member.pfmbRelation}</td>
							<!-- Add your buttons or actions here -->
							<!-- <td><input type="button" value="Edit"
								class="btn btn-success edit-button"> <input
								type="button" value="Delete" class="btn btn-danger"></td>
						</tr> -->
					</c:forEach>
				</tbody>
			</table>


			<div class="container">
				<div class="row">
					<div class="col-md-6">
						<h4>Add Family Members</h4>
						<form id="addForm">
							<div class="form-group">
								<label for="pfmbName">Name:</label> <input type="text"
									id="pfmbName" name="pfmbName" class="form-control" required>
							</div>
							<div class="form-group">
								<label for="pfmbRelation">Relation:</label> <input type="text"
									id="pfmbRelation" name="pfmbRelation" class="form-control"
									required>
							</div>
							<div class="form-group">
								<label for="pfmbAge">Age:</label> <input type="number"
									id="pfmbAge" name="pfmbAge" class="form-control" required>
							</div>
							<div class="form-group">
								<label for="pfmbGender">Gender:</label> <select id="pfmbGender"
									name="pfmbGender" class="form-control" required>
									<option value="Male">Male</option>
									<option value="Female">Female</option>
									<option value="Other">Other</option>
								</select>
							</div>
							<div class="form-group">
								<label for="pfmbbgroup">Blood Group:</label> <select
									id="pfmbbgroup" name="pfmbbgroup" class="form-control" required>
									<option value="">Select</option>
									<option value="A+">A+</option>
									<option value="A-">A-</option>
									<option value="B+">B+</option>
									<option value="B-">B-</option>
									<option value="AB+">AB+</option>
									<option value="AB-">AB-</option>
									<option value="O+">O+</option>
									<option value="O-">O-</option>
								</select>
							</div>
							<button type="submit" class="btn btn-primary">Add Member</button>
						</form>
					</div>
				</div>
			</div>


			<div id="editFormSection" style="display: none;">
				<h4>Edit Family Member</h4>
				<form id="editForm" method="post">
					<input id="editId" name="editId" type="text" hidden> <label
						for="editName">Name:</label> <input type="text" id="editName"
						name="editName"><br> <label for="editRelation">Relation:</label>
					<input type="text" id="editRelation" name="editRelation"><br>
					<label for="editAge">Age:</label> <input type="number" id="editAge"
						name="editAge"><br>
					<button type="submit" id="update">Update</button>
				</form>
			</div>
		</div>
	</div>
	<div id="editFormSection" style="display: none;">
		<h4>Edit Family Member</h4>
		<form id="editForm" method="post">
			<!-- Form fields -->
			<button type="submit" id="update">Update</button>
		</form>
		<button id="closePopupButton">Close</button>
	</div>

</body>
<script type="text/javascript" src="./js/myfamily.js"></script>

</html>