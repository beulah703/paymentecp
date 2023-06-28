$(document).ready(function() {
	
		
		
		$.ajax({
			url : './getpatientid',
			type : 'GET',
			success : function(response) {
				var data = response;
				console.log(data);
				  var dropdown = $('#patientId');
	                dropdown.empty(); // Clear existing options
	                
	                // Add new options based on the data
	                $.each(data, function(index, value) {
	                    dropdown.append($('<option></option>').attr('value', value).text(value));
	                }); 
			}
		});

	});



function getappn() {
    var patientId = document.getElementById("patientId").value;
console.log(patientId);
    $.ajax({
        url: "./getpatientbyid",
        type: "POST",
        data: {
        	patientId : patientId
        },
        success: function(response) {
        	var data = response;
			console.log(data);
			  var dropdown = $('#appnId');
                dropdown.empty(); // Clear existing options
                
                // Add new options based on the data
                $.each(data, function(index, value) {
                    dropdown.append($('<option></option>').attr('value', value).text(value));
                }); 
            
        },
        error: function(xhr, status, error) {
            console.log("Error: " + error);
        }
    });
}