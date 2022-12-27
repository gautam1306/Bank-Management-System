package com.bank.accounts;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.customer.Accounts;
import com.bank.customer.Transaction;

/**
 * Servlet implementation class TransactionHistory
 */
@WebServlet("/transactionhistory")
public class TransactionHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Accounts  account = (Accounts) session.getAttribute("account");
		ArrayList<Transaction>transactions = account.getTransactions();
		request.setAttribute("transactions", transactions);
		request.getRequestDispatcher("/transaction").forward(request, response);
	}

}
