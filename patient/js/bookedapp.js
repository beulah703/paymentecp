
		function jspPage(path) {
			$(document).ready(function() {
				console.log("data")
				var formdata = {
					spec : document.getElementById("spec").value,
					status : document.getElementById("status").value,
					doctor : document.getElementById("doctor").value,
					from : document.getElementById("from").value,
					to :document.getElementById("to").value
				}

				console.log(formdata.doctor)

				$.ajax({
					url : path,
					type : 'GET',
					data : formdata,
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
		
		$(document).ready(function()
				{
			jspPage('./fetchBookData');
				});
				