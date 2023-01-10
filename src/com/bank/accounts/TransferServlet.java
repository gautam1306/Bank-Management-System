package com.bank.accounts;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.customer.Accounts;
import com.bank.customer.Customer;

/**
 * Servlet implementation class TransferServlet
 */
@WebServlet("/transferServlet")
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int amount = Integer.parseInt(request.getParameter("amount"));
		int toAccountNumber = Integer.parseInt(request.getParameter("toaccount"));
		int fromaccount=Integer.parseInt(request.getParameter("account"));
		HttpSession session = request.getSession();
		Customer customer= (Customer)session.getAttribute("customer");
		Accounts account = customer.getAccounts().get(fromaccount);
		String description = request.getParameter("description");
		String mode = request.getParameter("mode");
		int result = account.fundtransfer(toAccountNumber, amount, description, mode);
		if(result ==1) {
//			success
			session.setAttribute("status", "Transaction was successfull");
		}
		if(result==-2) {
			session.setAttribute("status", "This transaction exceeds the daily transfer limit");
//			Daily transfer limit
		}
		if(result==0) {
//			insufficient funds
			session.setAttribute("status", "This account has insufficient funds for this transaction");
		}
		if(result ==-1) {
			session.setAttribute("status", "The account number is invalid for this entry");
//			enter a different account number
		}
		response.sendRedirect("customer-dashboard");
	}

}
