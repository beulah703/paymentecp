package spring.orm.customexceptions;

import spring.orm.model.entity.AppointmentEntity;

public class SlotAlreadyBookedException extends Exception {
	private String referenceId;
	private AppointmentEntity appointmentDetails;

	public SlotAlreadyBookedException(String message, String referenceId, AppointmentEntity appointmentDetails) {
		super(message);
		this.referenceId = referenceId;
		this.appointmentDetails = appointmentDetails;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public AppointmentEntity getAppointmentDetails() {
		return appointmentDetails;
	}
}