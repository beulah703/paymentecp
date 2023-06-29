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
/**
 * This method handles the request for the test booking page.
 * It logs the entry into the page and returns the view name "dcadmin/booktest".
 *
 * @param model the model object to be populated with data for the view
 * @return the view name for the test booking page
 */
	@RequestMapping("/dcadmin/booktest")
	public String testBookingPage(Model model) {

		logger.info("Entered Test Booking page");
		return "dcadmin/booktest";
	}

	/**
 * This method handles the request to fetch test categories.
 * It retrieves the categories from the database using the testDAO,
 */
@GetMapping("/dcadmin/gettestcat")
public @ResponseBody ResponseEntity<String> getCategories(Model model) {

    logger.info("Method to load Categories is Called");

    // Retrieve the categories from the testDAO
    List<testsCategoriesModel> categoriesList = testDAO.getCategories();

    logger.info("List Of Categories: " + categoriesList.toString());

    // Return a ResponseEntity with the categories in JSON format
    return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(categoriesList));
}


	/**
 * This method handles the request to fetch patients.
 * It retrieves the patients from the database using the testDAO,
 */
@GetMapping("/dcadmin/getpatients")
public @ResponseBody ResponseEntity<String> getPatients(Model model) {

    logger.info("Method to load Patients is Called");

    // Retrieve the patients from the testDAO
    List<patientsoutputmodel> patientsList = testDAO.getPatients();

    logger.info("List Of Patients: " + patientsList.toString());

    // Return a ResponseEntity with the patients in JSON format
    return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(patientsList));
}

	/**
 * This method handles the request to fetch tests based on a selected category.
 * It retrieves the tests from the testDAO by category, logs the execution,
 * and returns a JSON response with the tests.
 * @return a ResponseEntity containing a JSON response with the tests
 */
@RequestMapping(value = "/dcadmin/gettestbycat", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<String> getTestByCategory(@RequestParam String category) {
    logger.info("Method to load Tests of selected Category is Called");

    // Retrieve the tests from the testDAO based on the selected category
    List<TestModel> test = testDAO.getTestByCategory(category);

    logger.info("List Of Tests: " + test);

    // Return a ResponseEntity with the tests in JSON format
    return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(test));
}


	/**
 * This method is responsible for booking a test and storing the provided information in the database.

 * @param billInput  the BillInputModel object containing the information for the test booking
 */
@RequestMapping(value = "/dcadmin/bookdctest", method = RequestMethod.POST)
public void BookTest(@ModelAttribute BillInputModel billInput) {
    logger.info("Method to book Test is Called");

    // Book the test by storing the information in the database using daignosticBillDAO
    daignosticBillDAO.bookDcTest(billInput);
}

/**
 * This method handles the request to retrieve the price of a selected test.
 * It retrieves the price of the test from the testDAO based on the provided test ID,
 * logs the execution, and returns a JSON response with the test price.
 * @param test  the ID of the selected test
 * @return a ResponseEntity containing a JSON response with the test price
 */
@RequestMapping(value = "/dcadmin/gettestprice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<String> getTestPrice(@RequestParam int test) {
    logger.info("Method to get test Price of Selected test is Called");

    logger.info("Selected Test ID: " + test);

    // Retrieve the price of the selected test from the testDAO
    Object price = testDAO.getSelectedTestPrice(test);

    logger.info("Selected Test Price: " + (int) price);

    // Return a ResponseEntity with the test price in JSON format
    return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(price));
}


	/**
 * This method stores the booked tests to the database.
 * It retrieves the patient ID from the request parameters, stores the tests to the database using the daignosticBillDAO,
 * logs the execution, and returns a JSON response with the generated bill ID.
 * @param patient the ID of the patient for whom the tests are being booked
 * @return a ResponseEntity containing a JSON response with the generated bill ID
 */
@RequestMapping(value = "/dcadmin/storedb", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<String> storeToDatabase( @RequestParam int patient) {
    logger.info("Method to store booked tests to database is Called");

    // Store the booked tests to the database using daignosticBillDAO and retrieve the generated bill ID
    int billid = daignosticBillDAO.storeToDatabase(patient);

    logger.info("Generated Bill Id for booked tests: " + billid);

    // Return a ResponseEntity with the generated bill ID in JSON format
    return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(billid));
}


	/**
 * This method calculates the total bill for a specified patient.
 * It retrieves the total bills from the DiagnosticBillDAO by invoking the gettotalbills() method,
 * logs the execution, and returns a JSON response with the total tests and total bill.
 *
 * @param patient the ID of the patient for whom the total bill is being calculated

 * @return a ResponseEntity containing a JSON response with the total tests and total bill
 */
@RequestMapping(value = "/dcadmin/totalbills", method = RequestMethod.GET)
public ResponseEntity<String> totalBills(@RequestParam int patient) {
    logger.info("Method to calculate total bill is Called");

    // Retrieve the total bills from the DiagnosticBillDAO
    List<Object> totalBill = daignosticBillDAO.getTotalBills(patient);

    // Return a ResponseEntity with the total tests and total bill in JSON format
    return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(totalBill));
}


	/**
 * This method sends an email using the provided email address and content.
 * It calls the sendEmail1() method from the MailSend class, passing the request, response, email, and content as parameters.
 * It logs the execution and catches any exceptions that occur during the email sending process.
 *
 * @param request  the HttpServletRequest object
 * @param response the HttpServletResponse object
 * @param email    the email address to send the email to
 * @param content  the content of the email
 */
@RequestMapping(value = "/dcadmin/mailsend2", method = RequestMethod.POST)
public @ResponseBody void mailSend(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam String email, @RequestParam String content) {
    logger.info("Method to send Mail is Called");
    try {
        // Calls the sendEmail1() method from the MailSend class to send the email
        MailSend.sendEmailTestBooking(request, response, email, content);
        logger.info("No Exception Raised to send mail");
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
