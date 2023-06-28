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
		 <div class="shadow p-3  bg-white rounded">
			<h4 class="">Admin Settings</h4>
			<form action="" method="post">

				<div class="form-group">
					<label for="companyName" class="form-label">Company Name</label> <input
						type="text" name="companyName" id="companyName"
						class="form-control">
				</div>

				<div class="form-group">
					<label for="email" class="form-label">Email</label> <input
						type="email" name="email" id="email" class="form-control">
				</div>

				<div class="form-group">
					<label for="phone" class="form-label">Phone</label> <input
						type="tel" name="phone" id="phone" class="form-control">
				</div>

				<div class="form-group">
					<label for="address" class="form-label">Address</label>
					<textarea name="address" id="address" class="form-control" rows="3"></textarea>
				</div>

				<div class="form-group">
					<label for="logo" class="form-label">Logo</label> <input
						type="file" name="logo" id="logo" class="form-control-file">
				</div>

				<button type="submit" class="btn btn-primary btn-block">Save
					Settings</button>
			</form>
		</div>
	</div>
</body>
</html>
