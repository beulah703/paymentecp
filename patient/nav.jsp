<div>
	<nav class="navbar navbar-expand-lg navbar-lignt bg-success text-white">
		<a class="navbar-brand text-white" href="./">Doctor Appointment
			System</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<ul class="nav nav-pills flex-row mb-auto">
			<li class="nav-item"><a href="./" class="nav-link"
				aria-current="page">Dashboard</a></li>
			<li class="nav-item">
				<p class="nav-link" href="" id="appointmentsLink">Appointments</p>
				<div class="dropdown-menu" aria-labelledby="appointmentsLink">
					<a class="dropdown-item" href="./appointments">Booked
						Appointments</a> <a class="dropdown-item" href="./newappointment">New
						Appointments</a>
				</div>
			</li>
			<li class="nav-item"><a href="./patienttest" class="nav-link"
				aria-current="page">Test Reports</a></li>
				<li class="nav-item"><a href="./getallPrescription" class="nav-link"
				aria-current="page">Prescriptions Page</a></li>
			<li class="nav-item"><a href="./myfamily" class="nav-link"
				aria-current="page">My Family</a></li>
		</ul>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">

			<ul class="navbar-nav mr-auto">

			</ul>
			<ul class="navbar-nav mr-right">
				<li class="nav-right dropdown">
					<div class="dropdown">
						<button
							class="btn btn-secondary dropdown-toggle bg-gradient-success "
							type="button" id="dropdownMenuButton"
							style="background: #e9ecef; color: black" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">Patient</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item" href="./profile">My Profile</a> <a
								class="dropdown-item" href="./logout">Logout</a>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</nav>
</div>
