
$(document).ready(function() {
	$("#addForm").submit(function(e) {
		e.preventDefault();
		var formData = $(this).serialize();
		$.ajax({
			url: "./addMember",
			method: "post",
			data: formData,
			success: function(response) {
				console.log(response);
				// You can perform additional actions like refreshing the member list
			},
			error: function(xhr, status, error) {
				console.log("Error: " + error);
			}
		});
	});
});

function confirmDelete(index) {
	$.confirm({
		title: 'Confirm!',
		content: 'Are you sure to delete this family member?',
		buttons: {
			confirm: function() {
				deleteMember(index);
			},
			cancel: function() {
				$.alert('Canceled!');
			}
		}
	});
}

function deleteMember(index) {
	$.ajax({
		url: "./deleteMember",
		method: "post",
		data: {
			index: index
		},
		success: function(response) {
			console.log(response);
			$.alert({
				title: 'Deleted!',
				content: 'The item has been deleted.',
				buttons: {
					confirm: {
						text: 'OK',
						action: function() {
							// Refresh the page
							location.reload();
						}
					}
				}
			});
		},
		error: function(xhr, status, error) {
			console.log("Error: " + error);
		}
	});
}

function editMember(index) {
	var memberData = {
		name: "Family Member " + index,
		relation: "Relation " + index,
		age: index * 10
	};

	$("#editIndex").val(index);
	$("#editName").val(memberData.name);
	$("#editRelation").val(memberData.relation);
	$("#editAge").val(memberData.age);

	$("#editFormSection").show();
}

$(document).on('submit', '#update', function(e) {
	e.preventDefault();
	var formData = $(this).serialize();
	$.ajax({
		url: "./updateMember",
		method: "post",
		data: formData,
		success: function(response) {
			console.log(response);
			$("#editFormSection").hide();
			// You can perform additional actions like refreshing the member list
		},
		error: function(xhr, status, error) {
			console.log("Error: " + error);
		}
	});
});

