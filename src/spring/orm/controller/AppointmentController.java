package spring.orm.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.google.gson.Gson;
import com.razorpay.RazorpayException;

import spring.orm.contract.AppointmentDAO;
import spring.orm.contract.DoctorsDAO;
import spring.orm.contract.PatientDAO;
import spring.orm.contract.SpecializationDAO;
import spring.orm.model.PatientSession;
import spring.orm.model.Specialization;
import spring.orm.model.entity.DoctorTemp;
import spring.orm.model.input.AppointmentForm;
import spring.orm.model.input.BookedAppForm;
import spring.orm.model.input.RescheduleAppointmentModel;
import spring.orm.model.output.AppOutFormFamily;
import spring.orm.model.output.DoctorList;
import spring.orm.model.output.DoctorOutPutModel;
import spring.orm.model.output.OutputBookedAppointmnets;
import spring.orm.model.output.RescheduleAppointmentOutput;
import spring.orm.services.AppointmentService;
import spring.orm.services.DoctorOutputService;
import spring.orm.services.PaymentServices;

@Controller
@RequestMapping
public class AppointmentController {

	private SpecializationDAO specializationDAO;

	private DoctorOutputService doctorservice;

	private PatientDAO patientDAO;

	private DoctorsDAO doctorDAO;

	private AppointmentDAO appointmentDAO;

	private AppointmentService appointmentService;
	private PaymentServices ps;
	private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

	@Autowired
	public AppointmentController(SpecializationDAO specializationDAO, DoctorOutputService doctorservice,
			PatientDAO patientDAO, DoctorsDAO doctorsDAO, AppointmentDAO appointmentDAO, PaymentServices ps,
			AppointmentService a) {

		this.appointmentDAO = appointmentDAO;
		this.appointmentService = a;
		this.doctorDAO = doctorsDAO;
		this.doctorservice = doctorservice;
		this.patientDAO = patientDAO;
		this.specializationDAO = specializationDAO;
		this.ps = ps;
	}

	@RequestMapping(value = "admin/newappointment")
	public String getNewAppointment(Model m) {
		// Retrieve all specializations and add them to the model

		logger.info("Admin Entered to new appointment Booking page");
		List<Specialization> aplist = specializationDAO.getAllSpecializations();
		m.addAttribute("speclist", aplist);
		// Retrieve all patients and add them to the model

		m.addAttribute("patlist", patientDAO.getAllPatientInfo());
		logger.info("Retrieved specializations and patients for the appointment form");
		return "admin/appointment";
	}

	@RequestMapping(value = { "patient/newappointment" })
	public String getNewPatientAppointment(@SessionAttribute("patientSession") PatientSession patientSession,
			Model model) {
		// Retrieve all specializations and add them to the model
		logger.info("Handling 'getNewPatientAppointment' request");

		List<Specialization> specializationsForAppointment = specializationDAO.getAllSpecializations();
		model.addAttribute("speclist", specializationsForAppointment);
		logger.debug("Retrieved specializations: {}", specializationsForAppointment);

		// Retrieve the family appointments for the patient session and add them to the
		// model
		List<AppOutFormFamily> familyMembers = appointmentService.getFormFamily(patientSession.getId());
		model.addAttribute("fam", familyMembers);
		logger.debug("Retrieved family appointments: {}", familyMembers);
		return "patient/appointment";
	}

	@RequestMapping(value = "patient/appointments")
	public String getPatientBookedAppointments(@SessionAttribute("patientSession") PatientSession patientSession,
			Model model) {
		logger.info("Handling 'getPatientBookedAppointments' request");
		// Retrieve all specializations and add them to the model
		List<Specialization> specializations = specializationDAO.getAllSpecializations();
		model.addAttribute("specdata", specializations);
		logger.debug("Retrieved specializations: {}", specializations);
		// Retrieve all doctors and add them to the model
		List<DoctorTemp> allDoctors = doctorDAO.getAllDoc();
		model.addAttribute("docdata", allDoctors);
		logger.debug("Retrieved doctors: {}", allDoctors);

		return "patient/bookedapp";
	}

	@RequestMapping(value = "patient/fetchBookData", method = RequestMethod.GET)
	public String getpatientBookAppData(@SessionAttribute("patientSession") PatientSession patientSession,
			@ModelAttribute BookedAppForm bookedAppointmentFilter, Model model) {

		logger.info("Received BookedAppForm: {}", bookedAppointmentFilter);

		// Fetch booked appointment data based on the BookedAppForm and patientSession
		// ID
		List<OutputBookedAppointmnets> bookedAppointmentDetails = appointmentDAO
				.fetchBookedAppointmentData(bookedAppointmentFilter, patientSession.getId());
		model.addAttribute("data", bookedAppointmentDetails);
		// Log the fetched booked appointment details
		logger.info("Fetched booked appointment details: {}", bookedAppointmentDetails);

		return "patient/BookedAppData";
	}

	@RequestMapping(value = { "admin/fetchBySpecialization",
			"patient/fetchBySpecialization" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> fetchBySpecialization(@RequestParam String specialization,
			@RequestParam String appointmentDate) {
		// Convert the appointmentDate to Date object
		logger.info("Received specialization: {}", specialization);
		logger.info("Received appointment date: {}", appointmentDate);
		Date appointmentDated = Date.valueOf(appointmentDate);

		// Fetch doctors by specialization and appointment date
		List<DoctorList> doctorList = doctorservice.getAllDocBySpecDate(specialization, appointmentDated);
		logger.info("Fetched doctor list: {}", doctorList);
		return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(doctorList));

	}

	@RequestMapping(value = { "admin/fetchdoctor",
			"patient/fetchdoctor" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchDoctor(@RequestParam int id) {

		// Log the received doctor ID
		logger.info("Received doctor ID: {}", id);
		// Fetch doctor details by ID
		DoctorOutPutModel doctor = doctorservice.getDocbyID(id);

		// Update the doctor's consultation fee
		doctor.setDoctCfee(doctor.getDoctCfee() + doctor.getDoctCfee() * 0.1);
		logger.info("Updated doctor details: {}", doctor);
		return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(doctor));
	}

	@RequestMapping(value = { "admin/fetchtimeSlots", "admin/reschedule/fetchtimeSlots",
			"patient/reschedule/fetchtimeSlots",
			"patient/fetchtimeSlots" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchTimeSlots(@RequestParam int id, @RequestParam String date) {

		// Log the received doctor ID and date
		logger.info("Received doctor ID: {}", id);
		logger.info("Received date: {}", date);
		// Fetch available time slots for the given doctor ID and date
		List<String> timeSlots = doctorservice.getDocTimeSlots(id, Date.valueOf(date).toString());
		logger.info("Fetched time slots: {}", timeSlots);
		return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(timeSlots));
	}

	@RequestMapping(value = "admin/newappointment/create", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> newAppointmentBooking(@ModelAttribute AppointmentForm appointment,
			HttpServletRequest request, HttpServletResponse response) throws JSONException, RazorpayException {

		try {
			BookingType bookingType = BookingType.valueOf(appointment.getBookingType());
			if (bookingType == BookingType.NEW_PATIENT) {

				// Book an appointment with a new patient
				int app_id = appointmentService.bookAppointmentWithNewPatient(appointment);
				logger.info("New appointment booked with a new patient. Appointment ID: " + app_id);

			} else {

				int app_id = appointmentService.bookAppointment(appointment);
				appointmentService.sendBookingMail(request, response, app_id);
				// Book an appointment
				logger.info("New appointment booked. Appointment ID: " + app_id);
			}
		} catch (Exception e) {

			String s1 = ps.makeRefund(appointment);
			System.out.println("Slot Already Booked");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(s1);

		}
		return null;

	}

	@RequestMapping(value = "patient/newappointment/create", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> newAppointmentPatientBooking(
			@SessionAttribute("patientSession") PatientSession patientSession,
			@ModelAttribute AppointmentForm appointment, HttpServletRequest request, HttpServletResponse response)
			throws JSONException, RazorpayException {
		int patientId = patientSession.getId();
		Integer app_id;
		try {
			BookingType bookingType = BookingType.valueOf(appointment.getBookingType());
			if (bookingType == BookingType.SELF) {

				appointment.setExistingPatientid(String.valueOf(patientSession.getId()));
				// Book an appointment with an existing patient (self)
				app_id = appointmentService.bookAppointment(appointment);
				logger.info("New appointment booked with an existing patient (self). Appointment ID: " + app_id);

			} else {
				logger.info("New appointment booked. Appointment details: " + appointment);
				System.out.println(appointment);
				// Book an appointment
				app_id = appointmentService.bookAppointment(appointment);

			}
			String userMail = patientSession.getEmail();
			appointmentService.sendBookingMail(request, response, app_id);
		} catch (Exception e) {
			String s1 = ps.makeRefund(appointment);
			System.out.println("Slot Already Booked");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(s1);
		}
		return null;
	}

	@RequestMapping(value = { "admin/cancel/{app_id}", "patient/cancel/{app_id}" })
	public String cancelAppointment(@PathVariable int app_id, Model m) {
		// Cancel an appointment
		logger.info("Cancelling appointment with ID: " + app_id);
		appointmentService.cancelAppointment(app_id);
		logger.info("Canceled appointment with ID: " + app_id);

		return "redirect:../appointments";
	}

	@RequestMapping(value = "patient/reschedule/{app_id}")
	public String reschedulePatAppointment(@PathVariable int app_id, Model m) {
		// Get the appointment details for rescheduling
		logger.info("Retrieving appointment details for rescheduling. Appointment ID: " + app_id);

		RescheduleAppointmentOutput rescheduleAppointment = appointmentService.getAppointmentByIdOutput(app_id);
		System.out.println(rescheduleAppointment);
		m.addAttribute("app", rescheduleAppointment);
		return "admin/reschedule";
	}

	@RequestMapping(value = "admin/reschedule/{app_id}")
	public String rescheduleAppointment(@PathVariable int app_id, Model m) {
		// Extract the appointment details from the RescheduleAppointmentModel object
		logger.info("Extracting appointment details for rescheduling. Appointment ID: " + app_id);

		RescheduleAppointmentOutput rescheduleAppointment = appointmentService.getAppointmentByIdOutput(app_id);
		rescheduleAppointment.setApp_id(app_id);
		System.out.println(rescheduleAppointment);
		m.addAttribute("app", rescheduleAppointment);
		return "admin/reschedule";
	}

	@RequestMapping(value = { "admin/reschedule/success", "patient/reschedule/success" }, method = RequestMethod.POST)
	public String rescheduleAppointment(@ModelAttribute RescheduleAppointmentModel rescheduleModel) {
		// Extract the appointment details from the RescheduleAppointmentModel object
		int appointmentId;
		try {
			appointmentId = (rescheduleModel.getAppointmentId());
		} catch (NumberFormatException e) {
			// Handle the case where appointmentId is not a valid integer
			// You can redirect to an error page or show an error message
			logger.error("Invalid appointment ID:{} ", rescheduleModel.getAppointmentId());

			return "errorPage";
		}

		String rescheduleDate = rescheduleModel.getRescheduleDate();
		String slot = rescheduleModel.getSlot();
		logger.info("Rescheduling appointment. Appointment ID: " + appointmentId + ", Date: " + rescheduleDate
				+ ", Slot: " + slot);

		// Update the appointment details in the database using the appointmentId
		appointmentService.reschduleAppointment(rescheduleModel);
		// Redirect to a success page or show a success message
		return "admin/success";
	}

	@RequestMapping(value = "admin/appointments")
	public String getBookedAppForm(Model model) {
		// Get the data for the booked appointment form
		logger.info("Retrieving data for the booked appointment form");
		List<Specialization> specializationData = specializationDAO.getAllSpecializations();

		List<DoctorTemp> doctorData = doctorDAO.getAllDoc();

		model.addAttribute("specdata", specializationData);
		model.addAttribute("docdata", doctorData);

		return "admin/bookedapp";
	}

	@RequestMapping(value = "admin/fetchBookData", method = RequestMethod.GET)
	public String getBookAppData(@ModelAttribute BookedAppForm bookedAppForm, Model model) {

		// Fetch booked appointment data based on the BookedAppForm
		List<OutputBookedAppointmnets> data = appointmentDAO.fetchBookedAppointmentData(bookedAppForm, null);
		model.addAttribute("data", data);
		logger.info("Fetching booked appointment data. BookedAppForm: " + bookedAppForm);

		return "admin/BookedAppData";
	}

}