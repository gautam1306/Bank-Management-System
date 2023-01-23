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
 * Servlet implementation class AddBeneficiary
 */
@WebServlet("/addBeneficiaryServlet")
public class AddBeneficiary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBeneficiary() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession();
		Customer customer = (Customer)session.getAttribute("customer");
		int beneficiaryaccount = Integer.parseInt(request.getParameter("beneficiaryAccount"));
		System.out.println(beneficiaryaccount);
		String nickname = request.getParameter("nickname");
		int transferLimit = Integer.parseInt(request.getParameter("transferLimit"));
		int statuscode = customer.addBeneficiary(beneficiaryaccount, transferLimit,nickname);
		String status = null;
		if(statuscode==1) {
			status="Account Successfully added to beneficiary";
		}
		if(statuscode==23505) {
			status = "The beneficiary already exists";
		}
		if(statuscode==23503) {
			status ="The beneficiaries account does not exist inside this bank";
		}
		request.setAttribute("status",status); 
		request.getRequestDispatcher("customer-home").forward(request, response);
	}
}
