package spring.orm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import spring.orm.contract.DiagnosticBillDAO;
import spring.orm.contract.PatientDAO;
import spring.orm.contract.TestDAO;
import spring.orm.model.TestModel;
import spring.orm.model.input.BillInputModel;
import spring.orm.model.output.patientsoutputmodel;
import spring.orm.model.output.testsCategoriesModel;
import spring.orm.services.TestServices;
import spring.orm.util.MailSend;

@Controller
public class TestBookBillGenController {

	private TestServices testService;

	private DiagnosticBillDAO daignosticBillDAO;

	private TestDAO testDAO;

	private HttpSession httpSession;

	private PatientDAO patientDAO;
	private static final Logger logger = LoggerFactory.getLogger(TestBookBillGenController.class);

	@Autowired
	public TestBookBillGenController(TestServices testService, DiagnosticBillDAO daignosticBillDAO, TestDAO testDAO,
			HttpSession httpSession, PatientDAO patientDAO) {
		super();
		this.testService = testService;
		this.daignosticBillDAO = daignosticBillDAO;
		this.testDAO = testDAO;
		this.httpSession = httpSession;
		this.patientDAO = patientDAO;
	}

	@RequestMapping("/dcadmin/booktest")
	public String GetCat(Model model) {

		logger.info("Entered Test Booking page");
		return "dcadmin/booktest";
	}

	@GetMapping("/dcadmin/gettestcat")
	public @ResponseBody ResponseEntity<String> GetCategories(Model model) {

		logger.info("Method to load Categories is Called ");
		List<testsCategoriesModel> lc = testDAO.getCategories();

		logger.info("List Of Categories" + " " + lc.toString());
		return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(lc));

	}

	@GetMapping("/dcadmin/getpatients")
	public @ResponseBody ResponseEntity<String> getPatients(Model model) {
		logger.info("Method to load Patients is Called ");
		List<patientsoutputmodel> lc = testDAO.getPatients();

		logger.info("List Of Patients" + " " + lc.toString());

		return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(lc));

	}
	// Get list of test Categories and patients from the respective DAOs - TestDAO and PatientDAO

	@RequestMapping(value = "/dcadmin/gettestbycat", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getTestByCategory(@RequestParam String cat, Model model) {
		logger.info("Method to load Tests of selected Category is Called ");
		List<TestModel> test = testDAO.getTestByCategory(cat);
		logger.info("List Of Patients" + " " + test);
		return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(test));

	}

	// This method is responsible for booking a test and storing the information provided in the BillInputModel object
	// to database.
	@RequestMapping(value = "/dcadmin/bookdctest", method = RequestMethod.POST)
	public void BookTest(Model model, @ModelAttribute BillInputModel billInput) {
		logger.info("Method to book Test is Called ");
		daignosticBillDAO.bookDcTest(billInput);

	}

	@RequestMapping(value = "/dcadmin/gettestprice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getTestPrice(@RequestParam int test, Model model) {
		logger.info("Method to get test Price of Selected test is Called ");
		logger.info("Selected Test ID" + " " + test);
		Object price = testDAO.getSelectedTestPrice(test);
		logger.info("selected test Price" + " " + (int) price);
		return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(price));

	}

	// This method retrieves the price of a test based on the provided test ID from TestDAO gettestprice method.
	// The method receives the test ID as a request parameter and the Model object for rendering the view.

	@RequestMapping(value = "/dcadmin/storedb", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> storeToDatabase(Model model, @RequestParam int patient) {
		logger.info("Method to store booked tests to database is Called ");
		int billid = daignosticBillDAO.storeToDatabase(patient);
		logger.info("Generated Bill Id for booked tests " + " " + billid);
		return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(billid));
	}

	// Invokes the gettotalbills() method from the DiagnosticBillDAO dbs object to retrieve the total bills for the
	// specified patient.

	@RequestMapping(value = "/dcadmin/totalbills", method = RequestMethod.GET)
	public ResponseEntity<String> totalBills(@RequestParam int patient, Model model) {
		logger.info("Method to calculate total bill is Called ");
		List<Object> lb = daignosticBillDAO.getTotalBills(patient);
		// logger.info("Total tests and total Bill is " + " " + lb);
		return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(lb));

	}

	// Calls the sendEmail1() method from the MailSend class to send the email with the provided parameters.
	@RequestMapping(value = "/dcadmin/mailsend2", method = RequestMethod.POST)
	public @ResponseBody void mailSend(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String email, @RequestParam String content) {
		logger.info("Method to send Mail is Called ");
		try {
			MailSend.sendEmailTestBooking(request, response, email, content);
			logger.info("No Exception Raised to send mail ");
		} catch (Exception e) {
			// Catches any exception that occurs during the email sending process and prints the stack trace.
			// MessagingException
			// AuthenticationFailedException
			// SendFailedException
			// SMTPException
			e.printStackTrace();
		}

	}

}