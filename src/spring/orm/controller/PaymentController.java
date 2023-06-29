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
import spring.orm.model.input.AppointmentForm;
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

	public PaymentController() {
		// TODO Auto-generated constructor stub
	}

	/**
 * This method handles the payment for a test.
 * It receives the bill ID, amount, currency, and model as parameters.
 * It calls the makeTestPayment() method from the paymentServices object to process the payment.
 * It logs the execution and returns a ResponseEntity with the payment order.
 * If any exceptions occur during the payment process, appropriate error messages are returned.
 *
 * @param billid   the ID of the bill associated with the payment
 * @param amount   the amount to be paid
 * @param currency the currency of the payment
 * @return a ResponseEntity containing the payment order or an error message if an exception occurs
 */
@RequestMapping(value = "/dcadmin/testpayment", method = RequestMethod.POST, produces = "application/json")
@ResponseBody
public ResponseEntity<String> pay(String billid, String amount, String currency) {
    try {
        logger.info("Inside Test Payment Method");

        // Call the makeTestPayment() method from the paymentServices object to process the payment
        String order = paymentServices.makeTestPayment(billid, amount, currency, model);

        logger.info("Payment Order is: " + order);

        // Return a ResponseEntity with the payment order
        return ResponseEntity.ok(order);
    } catch (RazorpayException e) {
        logger.error("Error while processing payment: " + e.getMessage(), e);

        // Return a ResponseEntity with an appropriate error message for RazorpayException
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error occurred while processing payment");
    } catch (JSONException e) {
        logger.error("Error while processing payment: " + e.getMessage(), e);

        // Return a ResponseEntity with an appropriate error message for JSONException
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error occurred while processing payment");
    }
}


	/**
 * This method handles the refund for a payment.
 * It receives an AppointmentForm object containing the necessary information for the refund.
 * It calls the makeRefund() method from the paymentServices object to process the refund.
 * It logs the execution and returns the refund details.
 * If a JSONException or RazorpayException occurs during the refund process, an appropriate error message is returned.
 *
 * @param s the AppointmentForm object containing the refund information
 * @return the refund details, or an error message if an exception occurs
 */
public String payRefund(AppointmentForm s) {
    logger.info("Inside Payment Refund Method");
    String refund = null;
    try {
        // Call the makeRefund() method from the paymentServices object to process the refund
        refund = paymentServices.makeRefund(s);
    } catch (JSONException e) {
        logger.error("Error while processing payment: " + e.getMessage(), e);

        // Return an appropriate error message for JSONException
        return "Error occurred while processing payment";
    } catch (RazorpayException e) {
        logger.error("Error while processing payment: " + e.getMessage(), e);

        // Return an appropriate error message for RazorpayException
        return "Error occurred while processing payment";
    }
    logger.info("Payment Refund Details are: " + refund);
    return refund;
}


	/**
 * Handles the payment for an appointment.
 *
 * @param amount    The amount to be paid.
 * @param currency  The currency of the payment.
 * @param model     The model object.
 * @return          The ResponseEntity containing the payment order.
 */
@RequestMapping(value = { "admin/appnpayment", "admin/appnpayment", "patient/appnpayment" },
		method = RequestMethod.POST, produces = "application/json")
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
