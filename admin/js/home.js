	function onclickdoc() {

		console.log("Hello");
		$('#doctorForm').toggle();
	}
	function onclickspec() {

		console.log("Hello");
		$('#idInput').val('');
		$('#titleInput').val('');
		$('#descriptionInput').val('');
		$('#add-spec-btn').text('Add');
		$('#specializationForm').toggle();
	}
	$('#show-btn').click(function() {
		$('#idInput').val('');
		$('#titleInput').val('');
		$('#descriptionInput').val('');
		$('#add-spec-btn').text('Add');
		$('#specializationForm').toggle();
		
	});

	function getspec(specid) {
		$.ajax({
			url : "./getspec",
			method : "post",
			data : {
				id : specid
			},
			success : function(spec) {
				/* spec = JSON.parse(spec); */
				console.log(typeof (spec));
				const form = $('#edit-spec-form');
				$('#idInput').val(spec.id);
				$('#titleInput').val(spec.title);
				$('#descriptionInput').val(spec.description);
				form.attr('action', './updatespec');
		        form.show();
				$('#specializationForm').show();
				$('#add-spec-btn').text('Update');
				
			}
		});
	}
	


	function deletespec(specid) {
		$.confirm({
			title : 'Confirm!',
			content : 'Are you Sure to delete!',
			buttons : {
				confirm : function() {
					$.ajax({
						url : "./delspec",
						data : {
							id : specid
						},
						success : function(data) {
							$.alert({
								  title: 'Deleted!',
								  content: 'The item has been deleted.',
								  buttons: {
								    confirm: {
								      text: 'OK',
								      action: function () {
								        // Refresh the page
								        location.reload();
								      }
								    }
								  }
								});
						}
					});

				},
				cancel : function() {
					$.alert('Canceled!');
				},
			}
		});
		
		function onclickdoc() {

			console.log("Hello");
			

			$('#doctorName').val('');
			$('#specialization').val('');
			$('#qual').val('');
			$('#exp').val('');
			$('#photo').val('');
			$('#add-spec-btn').text('Add');
			$('#specializationForm').toggle();
		}

	}