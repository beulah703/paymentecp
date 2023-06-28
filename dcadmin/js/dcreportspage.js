function jspPage(path) {
			$(document).ready(function() {
				console.log("data")
				$.ajax({
					url : path,
					method : 'GET',
					data : $('#filter').serialize(),
					success : function(data) {
						// Insert the fetched JSP content into the desired <div> element
						$('#content').html(data);
					},
					error : function(xhr, status, error) {
						// Handle error if the AJAX request fails
						console.log('Error: ' + error);
					}
				});
			})
		}