function refundPayment(patientId,amount,email) {
    // Make an AJAX request to call the controller method
    $.ajax({
        url: "./refundPayment", // Specify the URL of the controller method
        type: "POST", // Use POST or GET depending on your server-side implementation
        data: { patientId: patientId,
        	amount:amount,
        	email:email
        	}, // Pass the patientId as data to the server
        success: function(response) {
            // Handle the response from the server
            console.log(response); // You can log the response or perform further actions as needed
        },
        error: function(xhr, status, error) {
            // Handle any error that occurred during the AJAX request
            console.log("Error: " + error);
        }
    });
}
    var dummyData=[];
    var slist = JSON.parse('${tests}');
    
    for (var i = 0; i < slist.length; i++) {
        var s = slist[i];
       
     
        var data = {
            PatientId: s.appn_patn_id,
            PaymentMode: s.appn_paymode,
            PaymentReference: s.appn_payreference,
            PaymentAmount: s.appn_payamount,
            date: s.appn_booked_Date,
            doctor:s.appn_doct_id,
            AppStatus:s.appn_status,
            Appnmail:s.appn_email
           
        };
       
        dummyData.push(data);
    }
    
    function getFilteredRecords1(status) {
        // Apply filtering logic here based on selectedDoctor,startDate, and endDate
        var filteredRecords = dummyData;
        console.log(filteredRecords);
       
        
        // Filter based on Date Wise
         if (status=="sus") {
			filteredRecords = filteredRecords.filter(function (record) {
				console.log("i1") ;
				return(record.AppStatus == status)
				
			});
		}
        
      
        return filteredRecords;
    }
   

    function applyFilters() {
      
        var status = document.getElementById("statusFilter").value;
       
        var filteredRecords;
    
        filteredRecords = getFilteredRecords1(status);
        
       
        // Clear previous table rows
        document.getElementById("patientTableBody").innerHTML = "";

        // Populate the table with filtered records
        for (var i = 0; i < filteredRecords.length; i++) {
            var record = filteredRecords[i];

            // Create a new table row
            var row = document.createElement("tr");

            // Create table cells and populate them with record data
            var patientIdCell = document.createElement("td");
            patientIdCell.innerHTML = record.PatientId;
            row.appendChild(patientIdCell);

            var paymentModeCell = document.createElement("td");
            paymentModeCell.innerHTML = record.PaymentMode;
            row.appendChild(paymentModeCell);

            var paymentReferenceCell = document.createElement("td");
            paymentReferenceCell.innerHTML = record.PaymentReference;
            row.appendChild(paymentReferenceCell);

            var paymentAmountCell = document.createElement("td");
            paymentAmountCell.innerHTML = record.PaymentAmount;
            row.appendChild(paymentAmountCell);
            
            var paymentAmountCell = document.createElement("td");
            paymentAmountCell.innerHTML = record.AppStatus;
            row.appendChild(paymentAmountCell);
            
            var refundButtonCell = document.createElement("td");
            var refundButton = document.createElement("button");
            refundButton.innerHTML = "Refund";
            refundButton.className = "btn btn-primary";
            refundButton.setAttribute("onclick", "refundPayment(" + record.PatientId +",'" + record.PaymentAmount + "','" + record.Appnmail + "')");

            refundButtonCell.appendChild(refundButton);
            row.appendChild(refundButtonCell);

            // Add the row to the table body
            document.getElementById("patientTableBody").appendChild(row);
        }

        // Show the table if there are filtered records, hide it otherwise
        document.getElementById("patientTable").style.display = filteredRecords.length > 0 ? "table" : "none";
    }