package spring.orm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.google.gson.Gson;

import spring.orm.model.PatientSession;
import spring.orm.model.output.ParaGroupOutput;
import spring.orm.model.output.PatientlastvisitOutput;
import spring.orm.services.PatientServices;

@Controller
@RequestMapping("/patient")
public class PatientDashboard {

	@Autowired
	private PatientServices patientservice;
	private static final Logger logger = LoggerFactory.getLogger(PatientDashboard.class);

	// Display all the appointments and test details
	@RequestMapping(value = "/getapptests", method = RequestMethod.GET)
	public ResponseEntity<String> getTests(@SessionAttribute("patientSession") PatientSession patientSession) {
		logger.info("Method to display tests in dashboard is called");
		// Extract the patient ID from the session attribute
		int patientId = patientSession.getId();

		// Retrieve the appointments tests details for the patient using the patient ID
		List<Object> appointmentTests = patientservice.getTestsDetails(patientId);

		// Return the response with the appointment tests in JSON format
		return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(appointmentTests));
	}

	// Get all Appointment Details
	@RequestMapping(value = "/getapps", method = RequestMethod.GET)
	public ResponseEntity<String> getAppointmentsDetails(
			@SessionAttribute("patientSession") PatientSession patientSession) {
		logger.info("Method to display appointments in dashboard is called");
		// Extract the patient ID from the session attribute
		int patientId = patientSession.getId();

		// Retrieve the appointments details for the patient using the patient ID
		List<Object> appointments = patientservice.getAppointmentsDetails(patientId);

		// Return the response with the appointments in JSON format
		return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(appointments));
	}

	// Get all Parameters data for respective patient
	@RequestMapping(value = "/getOutParaGroup", method = RequestMethod.GET)
	public ResponseEntity<String> getParameterValues(
			@SessionAttribute("patientSession") PatientSession patientSession) {
		logger.info("Method to display parameter values for chart in dashboard is called");
		// Extract the patient ID from the session attribute
		int patientId = patientSession.getId();

		// Retrieve the parameter groups for the patient using the patient ID
		List<ParaGroupOutput> paraGroups = patientservice.getParamterValues(patientId);

		// Return the response with the parameter groups in JSON format
		return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(paraGroups));
	}
	
//Method to get Patient Last Appointment Details
	@RequestMapping(value = "/getPrescriptionView", method = RequestMethod.GET)
	public @ResponseBody List<PatientlastvisitOutput> getPrescriptionLastVisit(
			@SessionAttribute("patientSession") PatientSession patientSession) {

		logger.info("Method to display patient Last Visit details in dashboard is called");
		// Extract the patient ID from the session attribute
		int patientId = patientSession.getId();

		// Retrieve the last visit information for the patient using the patient ID
		List<PatientlastvisitOutput> lastVisits = patientservice.getPatientLastVisit(patientId);

		// Return the last visit information
		return lastVisits;
	}
	
//Method to count the number of patients booked appointments and tests
	@RequestMapping(value = "/getapptestcards", method = RequestMethod.GET)
	public @ResponseBody List<Object> getAppointmentTestsCount(
			@SessionAttribute("patientSession") PatientSession patientSession) {
		logger.info("Method to display appointments and tests counts  in dashboard is called");
		// Extract the patient ID from the session attribute
		int patientId = patientSession.getId();

		// Retrieve the appointments and test details for the patient using the patient ID
		List<Object> testCards = patientservice.getAppointmentTestCounts(patientId);

		// Return the test cards
		return testCards;
	}

}
