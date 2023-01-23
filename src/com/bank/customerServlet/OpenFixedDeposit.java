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
 * Servlet implementation class OpenFixedDeposit
 */
@WebServlet("/openFixedDepositServlet")
public class OpenFixedDeposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpenFixedDeposit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		int accountNumber =Integer.parseInt(request.getParameter("account"));
		int period = Integer.parseInt(request.getParameter("duration"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		int status =customer.addFixedDeposit(accountNumber, period, amount);
		switch(status) {
		case 1 : {
			request.setAttribute("status","The deposit was successfull made");
			break;
		}
		case -1:{
			request.setAttribute("status", "The account which you have tried to use is not associated with you");
			break;
		}
		case -3:{
			request.setAttribute("status", "There was an server side error");
			break;
		}
		case 0:{
			request.setAttribute("status", "There is no enough fund to transfer amount");
			break;
		}
		}
		request.getRequestDispatcher("customer-home").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
