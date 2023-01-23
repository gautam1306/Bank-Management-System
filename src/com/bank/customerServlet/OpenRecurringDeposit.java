package com.bank.customerServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.customer.Customer;

/**
 * Servlet implementation class OpenRecurringDeposit
 */
@WebServlet("/recurringdeposit")
public class OpenRecurringDeposit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OpenRecurringDeposit() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
//		customer.\
		int accountNumber = Integer.parseInt(request.getParameter("account"));
		int period = Integer.parseInt(request.getParameter("duration"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		int status = customer.addRecurringDeposit(accountNumber, period, amount);
		switch (status) {
		case 1:
			request.setAttribute("status", "The Recurring Deposit is successfully added");
			break;
		case -1:
			request.setAttribute("status",
					"The account that you specified for the recurring deposit is either not associated with this account or else it is an invalid account number");
			break;
		case 0:
			request.setAttribute("status", "There is an error in the data base");
		}
		request.getRequestDispatcher("customer-home").forward(request, response);
	}
}
