package com.bank.customerServlet;

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
 * Servlet implementation class Beneficiarytransfer
 */
@WebServlet("/beneficiarytransfer")
public class Beneficiarytransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Beneficiarytransfer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		Accounts account = customer.getAccounts().get(Integer.parseInt(request.getParameter("account")));
		int toAccountNumber = Integer.parseInt(request.getParameter("accountNumber"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String description = request.getParameter("description");
		String mode = request.getParameter("mode");
		System.out.println(customer);
		int transferLimit = customer.getBeneficiaryList().get(toAccountNumber)!=null?customer.getBeneficiaryList().get(toAccountNumber).getTransactionlimit():-1;
		if(transferLimit!=-1) {
		int transfer = customer.getBeneficiaryList().get(toAccountNumber).getTransfer();
		int status = account.beneficiaryfundtransfer(toAccountNumber, amount, description, mode,transferLimit,transfer);
		if(status==0) {
			request.setAttribute("status","insufficient funds");
		}
		if(status==1) {
			request.setAttribute("status","transfer was successfull");
			customer.getBeneficiaryList().get(toAccountNumber).setTransfer(transfer+amount);
			account.setBalance(account.getBalance()-amount);
		}
		if(status==-1) {
			request.setAttribute("status","You are trying to access the same account number");
		}}
		else {
			request.setAttribute("status", "This account is not added to the beneficiary");
		}
		request.getRequestDispatcher("customer-home").forward(request, response);
		
	}

}
