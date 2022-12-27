package com.bank.accounts;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.customer.Accounts;

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
		int toAccountNumber = Integer.parseInt(request.getParameter("account_number"));
		HttpSession session = request.getSession();
		Accounts account = (Accounts)session.getAttribute("account");
		String description = request.getParameter("description");
		String mode = request.getParameter("mode");
		int result = account.fundtransfer(toAccountNumber, amount, description, mode);
		if(result ==1) {
//			success
		}
		if(result==-2) {
//			Daily transfer limit
		}
		if(result==0) {
//			insufficient funds
		}
		if(result ==-1) {
//			enter a different account number
		}
	}

}
