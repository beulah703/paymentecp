<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, spring.orm.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DAS</title>
<jsp:include page="scripts.jsp" />
</head>
<body>

	<jsp:include page="nav.jsp" />
	<div align="center">
		<div class="container">
			<h1>My Profile</h1>
			<div class="row">
				<div class="col-md-6">
					<h4>Add Family Members</h4>
					<form id="addForm">
						<input id="index" name="index" type="text" hidden>
						<div class="form-group">
							<label for="name">Name:</label>
							<input type="text" class="form-control" id="name" name="name">
						</div>
						<div class="form-group">
							<label for="relation">Relation:</label>
							<input type="text" class="form-control" id="relation" name="relation">
						</div>
						<div class="form-group">
							<label for="age">Age:</label>
							<input type="number" class="form-control" id="age" name="age">
						</div>
						<button type="submit" class="btn btn-primary">Add Member</button>
					</form>
				</div>

			</div>
			<h2 class="mt-4">Family Members</h2>
			<table class="table mt-4">
				<tr>
                    <th>Index</th>
                    <th>Name</th>
                    <th>Relation</th>
                    <th>Age</th>
                    <th>Action</th>
                </tr>
				<tbody>
					<tr>
						<td>1</td>
						<td>Family Member 1</td>
						<td>Relation 1</td>
						<td>20</td>
						<td>
							<button type="button" class="btn btn-danger"
								onclick="editMember(1)">Edit</button>
							<button type="button" class="btn btn-danger"
								onclick="confirmDelete(1)">Delete</button>
						</td>
					</tr>
					<tr>
						<td>2</td>
						<td>Family Member 2</td>
						<td>Relation 2</td>
						<td>60</td>
						<td>
							<button type="button" class="btn btn-danger"
								onclick="editMember(2)">Edit</button>
							<button type="button" class="btn btn-danger"
								onclick="confirmDelete(2)">Delete</button>
						</td>
					</tr>
					<!-- Add more rows for family members -->
				</tbody>
			</table>

			<div id="editFormSection" style="display: none;">
				<h4>Edit Family Member</h4>
				<form id="editForm">
					<input id="editIndex" name="index" type="text" hidden>
					<div class="form-group">
						<label for="editName">Name:</label>
						<input type="text" class="form-control" id="editName"
							name="name">
					</div>
					<div class="form-group">
						<label for="editRelation">Relation:</label>
						<input type="text" class="form-control" id="editRelation"
							name="relation">
					</div>
					<div class="form-group">
						<label for="editAge">Age:</label>
						<input type="number" class="form-control" id="editAge" name="age">
					</div>
					<button type="submit" class="btn btn-primary" id="update">Update</button>
				</form>
			</div>

		</div>
	</div>
</body>
<script type="text/javascript" src="./js/myprofile.js"></script>
</html>
