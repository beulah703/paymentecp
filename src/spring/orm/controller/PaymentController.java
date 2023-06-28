package spring.orm.controller;

import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.razorpay.RazorpayException;

import spring.orm.contract.SpecializationDAO;
import spring.orm.services.PaymentServices;

@Controller
public class PaymentController {
	private SpecializationDAO specializationDAO;

	private HttpSession httpSession;

	private PaymentServices paymentServices;
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	public PaymentController(SpecializationDAO specializationDAO, HttpSession httpSession,
			PaymentServices paymentServices) {
		super();
		this.specializationDAO = specializationDAO;
		this.httpSession = httpSession;
		this.paymentServices = paymentServices;
	}

	@RequestMapping(value = "/dcadmin/testpayment", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> pay(String billid, String amount, String currency, Model model) {
		try {
			logger.info("Inside Test Payment Method");
			String order = paymentServices.makeTestPayment(billid, amount, currency, model);
			logger.info("Payment Order is: " + order);
			return ResponseEntity.ok(order);
		} catch (RazorpayException e) {
			logger.error("Error while processing payment: " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error occurred while processing payment");
		} catch (JSONException e) {
			logger.error("Error while processing payment: " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error occurred while processing payment");
		}
	}

	@RequestMapping(value = { "admin/appnpayment", "admin/appnpayment",
			"patient/appnpayment" }, method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> pay(String amount, String currency, Model model) {
		try {
			logger.info("Inside Appointment Payment Method");
			String order = paymentServices.makeAppointmentPayment(amount, currency, model);
			logger.info("Payment Order is: " + order);
			return ResponseEntity.ok(order);
		} catch (RazorpayException e) {
			logger.error("Error while processing payment: " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error occurred while processing payment");
		} catch (JSONException e) {
			logger.error("Error while processing payment: " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error occurred while processing payment");
		}
	}

}
