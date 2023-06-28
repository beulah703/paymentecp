
	$(document).ready(function() {
		// Handle form submission for adding a family member
		$("#addForm").submit(function(e) {
			e.preventDefault();
			var formData = $(this).serialize();
			if (validateForm()) {
			$.ajax({
				type: "POST",
				url: "./savefm",
				data: formData,
				success: function(response) {
					// Handle success response
					// Update the table with the new family member dynamically
					window.location.reload();
				},
				error: function() {
					// Handle error response
				}
			});
			}
		});
	});

	function validateForm() {
	    var bloodGroup = $("#pfmbbgroup").val();
	    if (bloodGroup === "") {
	        alert("Please select a valid blood group.");
	        return false;
	    }
	    return true;
	}
	document.addEventListener("DOMContentLoaded", function() {
		var editButtons = document.querySelectorAll(".edit-button");
		var popupForm = document.getElementById("editFormSection");
		var closePopupButton = document.getElementById("closePopupButton");

		editButtons.forEach(function(button) {
			button.addEventListener("click", function() {
				popupForm.style.display = "block";
			});
		});

		closePopupButton.addEventListener("click", function() {
			popupForm.style.display = "none";
		});
	});
// Handle click event for deleting a family member
function deleteMember(memberId) {
    var confirmation = confirm("Are you sure you want to delete this family member?");
    if (confirmation) {
        $.ajax({
            type: "POST",
            url: "./deleteMember",
            data: {
                memberId: memberId
            },
            success: function(response) {
                // Handle success response
                alert("Family member deleted successfully.");
                // Remove the corresponding row from the table dynamically
                $("table tbody").find(`tr[data-memberid='${memberId}']`).remove();
            },
            error: function() {
                // Handle error response
            }
        });
    }
} 

   // Handle click event for editing a family member
function editMember(memberId) {
    $.ajax({
        type: "GET",
        url: "./getMember",
        data: {
            memberId: memberId
        },
        success: function(response) {
            // Handle success response
            // Populate the edit form with the retrieved data
            $("#editId").val(response.member.id);
            $("#editName").val(response.member.name);
            $("#editRelation").val(response.member.relation);
            $("#editAge").val(response.member.age);
            $("#editFormSection").show();
        },
        error: function() {
            // Handle error response
        }
    });
}

// Handle form submission for updating a family member
$("#editForm").submit(function(e) {
    e.preventDefault();
    var formData = $(this).serialize();
    $.ajax({
        type: "POST",
        url: "./updateMember",
        data: formData,
        success: function(response) {
            // Handle success response
            $("#editFormSection").hide();
            // Update the corresponding row in the table dynamically
            var member = response.member;
            var updatedRow = `
                <tr data-memberid="${member.id}">
                    <td>${member.name}</td>
                    <td>${member.age}</td>
                    <td>${member.gender}</td>
                    <td>${member.bloodGroup}</td>
                    <td>${member.relation}</td>
                    /* <td>
                        <button onclick="editMember(${member.id})">Edit</button>
                        <button onclick="deleteMember(${member.id})">Delete</button>
                    </td> */
                </tr>`;
            $("table tbody").find(`tr[data-memberid='${member.id}']`).replaceWith(updatedRow);
            // Reset the form
            $("#editForm")[0].reset();
        },
        error: function() {
            // Handle error response
        }
    });
}); 
