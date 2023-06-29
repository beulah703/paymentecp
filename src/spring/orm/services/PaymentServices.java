package spring.orm.services;

import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Refund;

import spring.orm.model.input.AppointmentForm;

@Service
public class PaymentServices {
	@Autowired
	public PaymentServices(HttpSession httpSession) {
		super();
		this.httpSession = httpSession;
	}

	private HttpSession httpSession;
	private static final Logger logger = LoggerFactory.getLogger(PaymentServices.class);

	/**
 * Makes a payment for a test.
 *
 * @param billid    The ID of the bill.
 * @param amount    The amount to be paid.
 * @param currency  The currency of the payment.
 * @param model     The model object.
 * @return          The payment order details as a string.
 * @throws RazorpayException    If an error occurs during the payment process.
 * @throws JSONException        If an error occurs while creating the JSON object.
 */
public String makeTestPayment(String billid, String amount, String currency, Model model)
        throws RazorpayException, JSONException {
    logger.info("Inside Services make Test Payment Method");
    logger.info("billid" + " " + billid + " " + "amount" + " " + amount + " " + "currency" + " " + currency);
    
    // Initialize Razorpay client
    RazorpayClient razorpayClient = new RazorpayClient("rzp_test_wTvwL5iaSRljth", "AvneRMjZuce3P1NzgAM18omy");
    
    // Create options for the payment
    JSONObject options = new JSONObject();
    int amt = Integer.parseInt(amount) * 100;
    options.put("amount", amt); // Amount in paise (e.g., 1000 paise = Rs 10)
    options.put("currency", "INR");
    
    // Generate a unique receipt ID
    billid = billid + System.currentTimeMillis();
    options.put("receipt", billid);
    
    // Create the order using Razorpay client
    Order order = razorpayClient.Orders.create(options);
    
    // Store the generated receipt ID in the session
    httpSession.setAttribute("ticketNumber", billid);
    
    logger.info("Order is" + " " + order);
    
    // Return the payment order details as a string
    return order.toString();
}

/**
 * Makes a payment for an appointment.
 *
 * @param amount    The amount to be paid.
 * @param currency  The currency of the payment.
 * @param model     The model object.
 * @return          The payment order details as a string.
 * @throws RazorpayException    If an error occurs during the payment process.
 * @throws JSONException        If an error occurs while creating the JSON object.
 */
public String makeAppointmentPayment(String amount, String currency, Model model)
        throws RazorpayException, JSONException {
    logger.info("Inside Services make Appointment Payment Method");
    logger.info("amount" + " " + amount + " " + "currency" + " " + currency);
    
    // Initialize Razorpay client
    RazorpayClient razorpayClient = new RazorpayClient("rzp_test_wTvwL5iaSRljth", "AvneRMjZuce3P1NzgAM18omy");
    
    // Create options for the payment
    JSONObject options = new JSONObject();
    int amt = Integer.parseInt(amount) * 100;
    options.put("amount", amt); // Amount in paise (e.g., 1000 paise = Rs 10)
    options.put("currency", "INR");
    
    // Create the order using Razorpay client
    Order order = razorpayClient.Orders.create(options);
    
    logger.info("Order is" + " " + order);
    
    // Return the payment order details as a string
    return order.toString();
}


	/**
 * Initiates a refund for an appointment.
 *
 * @param appointment The appointment details.
 * @return            The refund details as a string.
 * @throws RazorpayException    If an error occurs during the refund process.
 * @throws JSONException        If an error occurs while creating the JSON object.
 */
public String makeRefund(AppointmentForm appointment) throws RazorpayException, JSONException {
    logger.info("Inside Make Refund Method");
    
    // Initialize Razorpay client
    RazorpayClient razorpay = new RazorpayClient("rzp_test_wTvwL5iaSRljth", "AvneRMjZuce3P1NzgAM18omy");
    
    // Create refund request
    JSONObject refundRequest = new JSONObject();
    refundRequest.put("payment_id", appointment.getAppnrefer()); // Payment ID to be refunded
    refundRequest.put("speed", "optimum"); // Speed of refund: instant, immediate, or optimum
    refundRequest.put("amount", appointment.getAppnfee()); // Refund amount, can be partial or full
    
    String paymentId = appointment.getAppnrefer();
    
    // Perform refund
    Refund refund = razorpay.Refunds.create(refundRequest);
    
    logger.info("Refund ID: " + refund.get("id"));
    logger.info("Refund Status: " + refund.get("status"));
    logger.info("Refund Amount: " + refund.get("amount"));
    
    // Add more log statements or return the refund details as needed
    
    return refund.toString(); // Return refund details as a string (you can customize the format)
}


}
