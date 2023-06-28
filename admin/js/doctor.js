
	function limitDaysSelection() {
		var checkboxes = document.querySelectorAll('input[type="checkbox"]');
		var checkedCount = 0;
		for (var i = 0; i < checkboxes.length; i++) {
			if (checkboxes[i].checked) {
				checkedCount++;
			}
		}
		if (checkedCount >= 3) {
			for (var i = 0; i < checkboxes.length; i++) {
				if (!checkboxes[i].checked) {
					checkboxes[i].disabled = true;
				}
			}
		} else {
			for (var i = 0; i < checkboxes.length; i++) {
				checkboxes[i].disabled = false;
			}
		}
	}

	function limitSelecction() {
		console.log("calles");
		var all = document.getElementById('ALL');
		var checkboxes = document.querySelectorAll('input[type="checkbox"]');
		if (all.checked) {
			for (var i = 0; i < checkboxes.length; i++) {
				if (!checkboxes[i].checked) {
					checkboxes[i].disabled = true;
				}
			}
		} else {
			for (var i = 0; i < checkboxes.length; i++) {
				checkboxes[i].disabled = false;
			}

		}

	}

	function getspec(specid) {
		$.ajax({
			url : "./getdoc",
			method : "post",
			data : {
				id : specid
			},
			success : function(spec) {
				//spec=JSON.parse(spec);
				//console.log(typeof (spec));
				console.log(spec);
				const form = $('#edit-docForm');

				console.log("go" + spec.test_id);

				$('#edoctorName').val(spec.doctName);
				$('#especialization').val(spec.specialization.id);
				$('#equal').val(spec.doctQual);
				$('#did').val(spec.doctId);
				$('#eexp').val(spec.doctExp);
				$('#eappt').val(spec.appt);
				$('#eavgtime').val(spec.docavgtime);
				$('#efee').val(spec.doctCfee);

				form.attr('action', './updatedoc');
				form.show();

				$('#EditForm').show();
				$('#add-spec-btn').text('update');
			}
		});

	}
