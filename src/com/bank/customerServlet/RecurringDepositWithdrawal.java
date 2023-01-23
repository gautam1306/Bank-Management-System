package com.bank.customerServlet;

import java.io.IOException;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.bank.customer.Customer;
import com.bank.customer.RecurringDeposit;

/**
 * Servlet implementation class recurringDepositWithdrawal
 */
@WebServlet("/recurringDepositWithdrawal")
public class RecurringDepositWithdrawal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecurringDepositWithdrawal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		RecurringDeposit recurringDeposit = (RecurringDeposit)session.getAttribute("recurringDeposit");
		int status = recurringDeposit.prematureWithdrawal();
		switch(status) {
		case 1:
		{
			request.setAttribute("status","The Amount is successfully credited to your account");
			break;
		}
		case -1:
		{
			request.setAttribute("status", "There was an error inside database connectivity");
			break;
		}
		}
		request.getRequestDispatcher("customer-home").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

}
