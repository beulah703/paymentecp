	function RevenueSplit() {
			// Make an AJAX request to call the controller method
			console.log("in spec");
			var spec = document.getElementById("specFilter").value;
			// var speci=document.getElementById(spec).textContent;

			var d1 = document.getElementById("startDateFilter").value;
			var d2 = document.getElementById("endDateFilter").value;
			var amount = 0;
			var count = 1;
			var profit = 0;
			var tamount = 0;
			if (d1 && d2) {
				getspecdatewisepay();
			} else {
				console.log(spec);
				console.log("inside revenue");
				$
						.ajax({
							url : "./revenueSplit", // Specify the URL of the controller method
							type : "POST", // Use POST or GET depending on your server-side implementation
							data : {
								spec : spec
							}, // Pass the patientId as data to the server
							success : function(response) {
								var data = response;

								// Iterate over the data and create table rows
								data
										.forEach(function(appointment) {
											tamount = amount
													+ (appointment.appn_payamount);
											var totalam = tamount * count;

											amount = amount
													+ (appointment.appn_payamount / 10);
											console.log(amount);
											count = count + 1;

											docpay = amount;
											profit = totalam - docpay;

										});
								var data = response;
								var tableBody = $('#patientTableBody');

								// Clear any existing table rows
								tableBody.empty();
								var newRow = $('<tr>');
								newRow.append($('<td>').text(spec));
								newRow.append($('<td>').text("null"));
								newRow.append($('<td>').text(profit));
								tableBody.append(newRow);

							},
							error : function(xhr, status, error) {
								console.error("Error: " + error);
							}
						});
			}
		}
		function getdocwisepay() {
			console.log("doc wise");
			var doc = document.getElementById("docFilter").value;
			var doci = document.getElementById("docFilter").value;
			console.log(doci);
			var d1 = document.getElementById("startDateFilter").value;
			var d2 = document.getElementById("endDateFilter").value;
			if (d1 && d2) {
				getdocdatewisepay();
			} else {
				console.log("sppec");
				$
						.ajax({

							url : "./getdocwisepay",
							method : "POST",
							data : {
								doc : doc
							},
							success : function(response) {
								var data = response;
								console.log(data);
								var tableBody = $('#patientTableBody');
								var amount = 0;
								var count = 1;
								var docpay = 0;
								var profit = 0;
								var tamount = 0;
								// Clear any existing table rows
								tableBody.empty();

								// Iterate over the data and create table rows
								
										data.forEach(function(appointment) {
											tamount = amount
													+ (appointment.appn_payamount);
											var totalam = tamount * count;

											amount = amount
													+ (appointment.appn_payamount / 10);
											console.log(amount);
											count = count + 1;

											docpay = amount;
											profit = totalam - docpay;

										});
								var newRow = $('<tr>');

								newRow.append($('<td>').text(doci));
								newRow.append($('<td>').text(docpay));
								newRow.append($('<td>').text(profit));
								tableBody.append(newRow);

								// Create the button element

							},
							error : function(xhr, status, error) {
								console.error("Error: " + error);
							}
						});
			}
		}

		function getspecdatewisepay() {
			var spec = document.getElementById("specFilter").value;
			var speci = document.getElementById("specFilter").value;
			var d1 = document.getElementById("startDateFilter").value;
			var d2 = document.getElementById("endDateFilter").value;
			var amount = 0;
			var count = 1;
			var profit = 0;
			var tamount = 0;
			console.log("sppec");
			$.ajax({

				url : "./getspecdatewisepay",
				method : "POST",
				data : {
					spec : spec,
					dat : d1,
					date : d2
				},
				success : function(response) {
					var data = response;
					var tableBody = $('#patientTableBody');

					// Clear any existing table rows
					tableBody.empty();

					// Iterate over the data and create table rows
					data.forEach(function(appointment) {
						tamount = amount + (appointment.appn_payamount);
						var totalam = tamount * count;

						amount = amount + (appointment.appn_payamount / 10);
						console.log(amount);
						count = count + 1;

						docpay = amount;
						profit = totalam - docpay;

					});
					var newRow = $('<tr>');
					newRow.append($('<td>').text(speci));
					newRow.append($('<td>').text("null"));
					newRow.append($('<td>').text(profit));
					tableBody.append(newRow);
				},
				error : function(xhr, status, error) {
					console.error("Error: " + error);
				}
			});
		}
		function getdatewisepay() {
			var doc = document.getElementById("docFilter").value;
			var d1 = document.getElementById("startDateFilter").value;
			var d2 = document.getElementById("endDateFilter").value;
			var spec = document.getElementById("specFilter").value;
			console.log(d1);

			console.log(d1);
			if (doc > 0) {
				getdocdatewisepay();
			} else if (spec != "null") {
				console.log(spec);
				getspecdatewisepay();
			} else {
				console.log("sppec");
				$
						.ajax({

							url : "./getdatewisepay",
							method : "POST",
							data : {
								dat : d1,
								date : d2
							},
							success : function(response) {
								console.log(response);
								var data = response;
								console.log(data);
								var tableBody = $('#patientTableBody');
								var amount = 0;
								var profit = 0;
								var count = 1;
								var tamount = 0;
								// Clear any existing table rows
								tableBody.empty();

								// Iterate over the data and create table rows
								data
										.forEach(function(appointment) {
											tamount = amount
													+ (appointment.appn_payamount);
											var totalam = tamount * count;

											amount = amount
													+ (appointment.appn_payamount / 10);
											console.log(amount);
											count = count + 1;

											docpay = amount;
											profit = totalam - docpay;

										});
								var newRow = $('<tr>');

								newRow.append($('<td>').text("All Doctors"));
								newRow.append($('<td>').text("null"));
								newRow.append($('<td>').text(profit));
								tableBody.append(newRow);

							},
							error : function(xhr, status, error) {
								console.error("Error: " + error);
							}
						});
			}
		}
		function getdocdatewisepay() {
			var doc = document.getElementById("docFilter").value;
			var d1 = document.getElementById("startDateFilter").value;
			var d2 = document.getElementById("endDateFilter").value;
			var amount = 0;
			var count = 1;
			var docpay = 0;
			var profit = 0;
			var tamount = 0;
			console.log("indocdate");
			$.ajax({

				url : "./getdocdatewisepay",
				method : "POST",
				data : {
					doc : doc,
					dat : d1,
					date : d2
				},
				success : function(response) {
					var data = response;
					var tableBody = $('#patientTableBody');

					// Clear any existing table rows
					tableBody.empty();

					// Iterate over the data and create table rows
					data.forEach(function(appointment) {
						tamount = amount + (appointment.appn_payamount);
						var totalam = tamount * count;

						amount = amount + (appointment.appn_payamount / 10);
						console.log(amount);
						count = count + 1;

						docpay = amount;
						profit = totalam - docpay;

					});
					var newRow = $('<tr>');
					newRow.append($('<td>').text(doc));
					newRow.append($('<td>').text(docpay));
					newRow.append($('<td>').text(profit));

					tableBody.append(newRow);

				},
				error : function(xhr, status, error) {
					console.error("Error: " + error);
				}
			});
		}