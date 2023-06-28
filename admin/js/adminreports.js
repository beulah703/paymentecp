		function jspPage(path) {
			$(document).ready(function() {
				console.log("data")
				$.ajax({
					url : path,
					method : 'GET',
					data : $('#filter').serialize(),
					success : function(data) {
						
						$('#content').html(data);
					},
					error : function(xhr, status, error) {
					
						console.log('Error: ' + error);
					}
				});
			})
		}