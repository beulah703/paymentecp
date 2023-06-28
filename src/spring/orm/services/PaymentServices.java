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

@Service
public class PaymentServices {
	@Autowired
	public PaymentServices(HttpSession httpSession) {
		super();
		this.httpSession = httpSession;
	}

	private HttpSession httpSession;
	private static final Logger logger = LoggerFactory.getLogger(PaymentServices.class);

	public String makeTestPayment(String billid, String amount, String currency, Model model)
			throws RazorpayException, JSONException {
		logger.info("Inside Services make Test Payment Method");
		logger.info("billid" + " " + billid + " " + "amount" + " " + amount + " " + "currency" + " " + currency);
		RazorpayClient razorpayClient = new RazorpayClient("rzp_test_wTvwL7iaSRljth", "AvneRMjZuce3P1NzgAM18omy");
		JSONObject options = new JSONObject();
		int amt = Integer.parseInt(amount) * 100;
		options.put("amount", amt); // Amount in paise (e.g., 1000 paise = Rs 10)
		options.put("currency", "INR");

		billid = billid + System.currentTimeMillis();
		options.put("receipt", billid);
		Order order = razorpayClient.Orders.create(options);
		httpSession.setAttribute("ticketNumber", billid);
		logger.info("Order is" + " " + order);
		return order.toString();
	}

	public String makeAppointmentPayment(String amount, String currency, Model model)
			throws RazorpayException, JSONException {
		logger.info("Inside Services make Appointment Payment Method");
		logger.info("amount" + " " + amount + " " + "currency" + " " + currency);
		RazorpayClient razorpayClient = new RazorpayClient("rzp_test_wTvwL7iaSRljth", "AvneRMjZuce3P1NzgAM18omy");
		JSONObject options = new JSONObject();
		int amt = Integer.parseInt(amount) * 100;
		options.put("amount", amt); // Amount in paise (e.g., 1000 paise = Rs 10)
		options.put("currency", "INR");

		Order order = razorpayClient.Orders.create(options);
		logger.info("Order is" + " " + order);

		return order.toString();
	}
}
