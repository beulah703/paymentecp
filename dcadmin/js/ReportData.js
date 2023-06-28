 $(document).ready(function() {
        // Attach event handler to the parent element and capture events from dynamically added forms
        $('#patientTable').on('submit', '.uploadForm', function(event) {
            event.preventDefault(); // Prevent form submission

            var form = $(this);
            var url = form.attr('action');
            var formData = new FormData(form[0]);

            $.ajax({
                type: 'POST',
                url: "./uploadfile",
                data: formData,
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                success: function(response) {
                    console.log(response);
                    
                    // Show success pop-up
                    Swal.fire({
                        icon: 'success',
                        title: 'File Uploaded',
                        text: 'The file has been successfully uploaded.',
                        timer: 3000, // Display the pop-up for 3 seconds
                        showConfirmButton: false
                    });
                    
                    setTimeout(function() {
                        window.opener.location.reload(); // Reload the parent page
                       window.close();
                    }, 3000);

                },
                error: function(xhr, status, error) {
                    console.log(error);
                    $('#uploadMessage').text('An error occurred during the file upload.');
                }
            });
        });
    });