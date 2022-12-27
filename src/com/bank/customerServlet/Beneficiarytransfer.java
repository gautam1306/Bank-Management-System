package com.bank.customerServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.bank.customer.Accounts;

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
		Accounts account = (Accounts)session.getAttribute("account");
		int toAccountNumber = Integer.parseInt(request.getParameter("accountNumber"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String description = request.getParameter("description");
		String mode = request.getParameter("mode");
		account.beneficiaryfundtransfer(toAccountNumber, amount, description, mode);
		
	}

}
