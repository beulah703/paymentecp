	$(document)
			.ready(
					function() {
						// Get the hyperlinks elements
						var displayLinks = $('.displayLink');

						// Add click event listener to each hyperlink
						displayLinks
								.on(
										'click',
										function(e) {
											e.preventDefault();

											// Get the image URL from the clicked hyperlink's data attribute
											var imageUrl = $(this).data(
													'image-url');

											// Create the popup window
											var popupWindow = window.open('',
													'Image Popup',
													'width=500,height=500');

											// Write the HTML content with Bootstrap classes to the popup window
											popupWindow.document
													.write('<html><head><title>Image Popup</title><link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"></head><body><div class="container"><div class="row"><div class="col-md-6"><img src="' + imageUrl + '" class="img-responsive"/><br/><button onclick="window.print()" class="btn btn-primary">Print</button> <button onclick="window.close()" class="btn btn-primary">Close</button></div></div></div></body></html>');
										});
					});
