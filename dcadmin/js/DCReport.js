function Reportpage() {

		$(document).ready(function() {
			$.ajax({
				url : "./uploaddata",
				type : "GET",
				data :{
					pid:document.getElementById("pid").value
				},
			
				success : function(response) {
					$("#upload").html(response);
				},
				error : function(xhr, status, error) {
					console.log("Error: " + error);
				}
			});
		});

	}
	
	function getPatientName(){
		$(document).ready(function() {
			$.ajax({
				url : "./patientname",
				type : "GET",
				data :{
					pid : document.getElementById("pid").value
				},
				success : function(response) {
					document.getElementById("name").innerHTML=response
				},
				error : function(xhr, status, error) {
					console.log("Error: " + error);
				
				}
			});
		});
		
	}