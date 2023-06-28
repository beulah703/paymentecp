function jspPage(path){
		
		var from=$('#from').val();
		var to=$('#to').val();
	$(document).ready(function() {
		console.log("data")
	  $.ajax({
		url :path,
		type : 'GET',
		data:{
			from:from,
			to:to
		},
		
		success : function(data) {
			// Insert the fetched JSP content into the desired <div> element
			$('#content').html(data);
		},
		error : function(xhr, status, error) {
			// Handle error if the AJAX request fails
			console.log('Error: ' + error);
		}
	  });
	});
	}