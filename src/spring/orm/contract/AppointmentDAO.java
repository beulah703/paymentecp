package spring.orm.contract;

import java.util.List;

import spring.orm.customexceptions.SlotAlreadyBookedException;
import spring.orm.model.PatientModel;
import spring.orm.model.entity.AppointmentEntity;
import spring.orm.model.entity.DoctorTemp;
import spring.orm.model.input.AppointmentForm;
import spring.orm.model.input.BookedAppForm;
import spring.orm.model.input.RescheduleAppointmentModel;
import spring.orm.model.output.AdminAppOutModel;
import spring.orm.model.output.AdminDashOut;
import spring.orm.model.output.AdminProfitAppOut;
import spring.orm.model.output.OutputBookedAppointmnets;

public interface AppointmentDAO {
	// This interface represents the contract for the AppointmentDao class.

	public List<AppointmentEntity> getAllAppointments();
	// Method to retrieve all appointments.

	List<Object[]> fetchUpcomingAppointmentData();
	// Method to fetch upcoming appointment data.

	public boolean isSlotBooked(int doc_id, String date, String time);
	// Method to check if a slot is already booked.

	public int bookAppointment(PatientModel existingPatientid, DoctorTemp doctor, String string, String payref,
			double appfee) throws SlotAlreadyBookedException;
	// Method to book an appointment with an existing patient.

	public AppointmentEntity getAppointmentById(int app_id);
	// Method to retrieve an appointment by ID.

	public void cancelAppointment(int app_id);
	// Method to cancel an appointment.

	public void reschduleAppointment(RescheduleAppointmentModel rm);
	// Method to reschedule an appointment.

	public List<OutputBookedAppointmnets> fetchBookedAppointmentData(BookedAppForm baf, Integer p_id);
	// Method to fetch booked appointment data.

	public int bookAppointment(AppointmentForm appointment, int patientId);
	// Method to book an appointment with a new patient.

	public List<AppointmentEntity> getAppointmentsByPatientId(int patn_id);
	// Method to retrieve appointments by patient ID.

	public List<AdminAppOutModel> getTopAppointments();
	// Method to get top appointments.

	public List<AdminProfitAppOut> getTopprof();
	// Method to get top profitable appointments.

	public AdminDashOut getDashreport();
	// Method to get dashboard report.

	public void updateAppStatusComp(int app_id);
	// Method to update appointment status as completed.

}