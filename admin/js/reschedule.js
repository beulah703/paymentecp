
		$(document)
				.ready(
						function() {
							$('#rescheduleDate')
									.change(
											function() {
												var id = $('#doctid').val();
												var appointmentDate = $(
														'#rescheduleDate')
														.val();
												$
														.ajax({
															url : './fetchtimeSlots',
															type : 'GET',
															data : {
																id : id,
																date : appointmentDate
															},
															success : function(
																	timeslots) {
																console
																		.log(timeslots);
																$('#slots')
																		.empty(); // Clear the existing options before appending new ones
																for (var i = 0; i < timeslots.length; i++) {
																	var option = $('<option/>');
																	option
																			.attr(
																					'value',
																					timeslots[i])
																			.text(
																					timeslots[i]);
																	$('#slots')
																			.append(
																					option);
																}
															}
														});
											});
						});
	