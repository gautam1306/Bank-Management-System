package com.bank.customerServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import com.bank.customer.Accounts;
import com.bank.customer.Customer;

/**
 * Servlet implementation class RepaymentServlet
 */
@WebServlet("/repaymentservlet")
public class RepaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session =  request.getSession();
		int loanID = (int) session.getAttribute("loanID");
		int accountnumber = Integer.parseInt(request.getParameter("account"));
		Customer customer = (Customer) session.getAttribute("customer");
		int amount =Integer.parseInt(request.getParameter("amount"));
		Accounts account = customer.getAccounts().get(accountnumber);
		int x = account.loanrepayment(customer.getLoans().get(loanID),amount);
		if(x==1) {
			request.setAttribute("status","insufficient funds");
		}
		if(x==0) {
			request.setAttribute("status","transfer was successfull");
		}
		if(x==-1) {
			request.setAttribute("status","You are trying to access somebody elses loan account");
		}
		request.getRequestDispatcher("customer-home").forward(request, response);
		
	}

}
