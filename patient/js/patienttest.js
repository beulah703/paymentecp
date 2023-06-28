
$(document).ready(function() {
	  // Get the hyperlinks elements
	  var displayLinks = $('.displayLink');

	  // Add click event listener to the parent element using event delegation
	  $('#testDetailsTableBody').on('click', '.displayLink', function(e) {
	    e.preventDefault();

	    // Get the image URL from the clicked hyperlink's data attribute
	    var imageUrl = $(this).data('image-url');
	    console.log("coming?");

	    // Create the popup window
	    var popupWindow = window.open('', 'Image Popup', 'width=500,height=500');

	    // Write the HTML content with Bootstrap classes to the popup window
	    popupWindow.document.write('<html><head><title>Image Popup</title><link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"></head><body><div class="container"><div class="row"><div class="col-md-6"><img src="' + imageUrl + '" class="img-responsive"/><br/><button onclick="window.print()" class="btn btn-primary">Print</button> <button onclick="window.close()" class="btn btn-primary">Close</button></div></div></div></body></html>');
	  });

	  // Rest of your code...
	});

		function applyFilters() {
			var selectedDate1 = document.getElementById("dateInput1").value;
			var selectedDate2 = document.getElementById("dateInput2").value;
			var testDetailsTableBody = document
					.getElementById("testDetailsTableBody");

			testDetailsTableBody.innerHTML = "";

			console.log(selectedDate1);
			console.log(selectedDate2);

			$.ajax({
				url : "./gettestbydate",
				type : "POST",
				data : {
					date1 : selectedDate1,
					date2 : selectedDate2
				},
				success : function(response) {

					console.log(response);
					// var filteredTestDetails = JSON.parse(response);
					var filteredTestDetails = response
					console.log("filteredtest: ", filteredTestDetails);

					// ...

					for (var i = 0; i < filteredTestDetails.length; i++) {
						var row = document.createElement("tr");

						var idColumn = document.createElement("td");
						idColumn.textContent = i + 1;

						var reportColumn = document.createElement("td");
						reportColumn.textContent = "Report " + (i + 1);
						
						var dateColumn = document.createElement("td");
						
						dateColumn.textContent = filteredTestDetails[i].dgbl_date.day+"/"+filteredTestDetails[i].dgbl_date.month+"/"+filteredTestDetails[i].dgbl_date.year;

						/* 
						<td><a href="#" class="displayLink"
					data-image-url="data:image/jpg;base64,${img.content}">Reports
								Image</a> */

						var imgColumn = document.createElement("td");
								imgColumn.innerHTML=`<a href="#" class="displayLink"
									data-image-url="data:image/jpg;base64,`+ filteredTestDetails[i].content+`">Reports
										Image</a>`
						/* var displayLink = document.createElement("a");
						displayLink.classList.add("displayLink");
						console.log(filteredTestDetails[i].content)
						displayLink.setAttribute("href", "#");
						displayLink.setAttribute("data-image-url",
								"data:image/jpg;base64,"
										+ filteredTestDetails[i].content);
						displayLink.textContent = "Reports Image"
						imgColumn.appendChild(displayLink) */

						row.appendChild(idColumn);
						row.appendChild(reportColumn);
						row.appendChild(dateColumn);
						row.appendChild(imgColumn);

						testDetailsTableBody.appendChild(row);
					}

					// ...
				},
				error : function(xhr, status, error) {
					console.log("Error: " + error);
				}
			});
		}
