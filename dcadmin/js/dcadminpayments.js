   
    var dummyData = [];
   
    var slist = JSON.parse('${dbills}');
   
    for (var i = 0; i < slist.length; i++) {
        var s = slist[i];
       
     
        var data = {
            PatientId: s.dgbl_patn_id,
            PaymentType:s.dgbl_patn_type,
            PaymentAmount: s.dgbl_amount,
            date: s.dgbl_date
           
           
        };
       
       dummyData.push(data);
    }
    
    function getFilteredRecords(startDate, endDate,type) {
        // Apply filtering logic here based on selectedDoctor,startDate, and endDate
        var filteredRecords = dummyData;
        console.log(filteredRecords) ;
    if (startDate && endDate!="" && type!="main") {
			filteredRecords = filteredRecords.filter(function (record) {
				console.log("i1") ;
				return(record.date >= startDate && record.date <= endDate && record.PaymentType==type)		
			});
		}
    else  if (startDate && endDate!="") {
		filteredRecords = filteredRecords.filter(function (record) {
			console.log("i1") ;
			return(record.date >= startDate && record.date <= endDate )		
		});
	}
    
    else if (type=="main") {
		filteredRecords = filteredRecords.filter(function (record) {
			console.log("i1") ;
			return(filteredRecords)		
		});
	}
    else if (startDate!="" && type=="main") {
		filteredRecords = filteredRecords.filter(function (record) {
			console.log("i1") ;
			return(record.date == startDate)	
		});
	}
    
    else if(startDate!="" && type!="main"){
        
        	filteredRecords = filteredRecords.filter(function (record) {	
				return(record.date == startDate &&  record.PaymentType==type)
				
			});
        }
    else if (type!="main") {
		filteredRecords = filteredRecords.filter(function (record) {
			console.log("i1") ;
			return( record.PaymentType==type)		
		});
	}
      
        
        // Filter based on Date Wise
       
        
      
        return filteredRecords;
    }

    function applyFilters() {
      
        var startDate = document.getElementById("startDateFilter").value;
        var endDate = document.getElementById("endDateFilter").value;
        var type = document.getElementById("typeFilter").value;
        var filteredRecords;
        // Perform filtering logic and populate the table
       
        filteredRecords = getFilteredRecords(startDate, endDate,type);
        
       
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
            paymentModeCell.innerHTML = record.PaymentType;
            row.appendChild(paymentModeCell);

         

            var paymentAmountCell = document.createElement("td");
            paymentAmountCell.innerHTML = record.PaymentAmount;
            row.appendChild(paymentAmountCell);

            // Add the row to the table body
            document.getElementById("patientTableBody").appendChild(row);
        }

        // Show the table if there are filtered records, hide it otherwise
        document.getElementById("patientTable").style.display = filteredRecords.length > 0 ? "table" : "none";
    }
    