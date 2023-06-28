package spring.orm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import spring.orm.contract.PatientDAO;
import spring.orm.contract.PatientProfileUpdateDAO;
import spring.orm.model.PatientSession;
import spring.orm.model.output.PrescriptionOutputmodel;

@Controller
public class PostConsultUpdateController {

	private PatientDAO patientDAO;

	private PatientProfileUpdateDAO profileUpdateDAO;

	@Autowired
	public PostConsultUpdateController(PatientDAO patientDAO, PatientProfileUpdateDAO profileUpdateDAO) {
		super();
		this.patientDAO = patientDAO;
		this.profileUpdateDAO = profileUpdateDAO;
	}

	// @RequestMapping(value = "patient/getParaGroup", method = RequestMethod.GET)
	// public ResponseEntity<String> getPatientPrescription(
	// @SessionAttribute("patientSession") PatientSession patientSession) {
	// int p = patientSession.getId();
	// List<PatientMedicalProfile> patientProfile = patientDAO.getPatientMedicalProfileById(p);
	// return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(patientProfile));
	// // return "patient/myprofile";
	// }

	// @RequestMapping(value = "patient/getPrescription", method = RequestMethod.GET)
	// public ResponseEntity<String> getPrescription(@SessionAttribute("patientSession") PatientSession patientSession)
	// {
	// int p = patientSession.getId();
	// List<patientPrescriptionOutputmodel> lo = patientDAO.getPatientPrescriptionById(p);
	// return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(lo));
	// // return "patient/myprofile";
	// }

	@RequestMapping(value = "patient/getallPrescription", method = RequestMethod.GET)
	public String getAllPrescription(Model model, @SessionAttribute("patientSession") PatientSession patientSession) {
		int p = patientSession.getId();// to maintain same session id when logged in
		List<PrescriptionOutputmodel> prescriptionList = profileUpdateDAO.getallPrescription(p); // merged
																									// PatientMedicalProfile
																									// &
		// DiagnosticBillModel attributes
		model.addAttribute("pres", prescriptionList);
		System.out.println(prescriptionList.toString());
		return "patient/patpresdisplay"; // reflects at patients page , which displays in the format of table, it also
											// includes prescription image
	}

}
