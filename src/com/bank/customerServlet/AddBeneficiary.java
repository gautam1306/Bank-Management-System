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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession();
		Customer customer = (Customer)session.getAttribute("customer");
		int beneficiaryaccount = Integer.parseInt(request.getParameter("beneficiaryAccount"));
		System.out.println(beneficiaryaccount);
		String nickname = request.getParameter("nickname");
		int transferLimit = Integer.parseInt(request.getParameter("transferLimit"));
		customer.addBeneficiary(beneficiaryaccount, transferLimit,nickname);
		
	}
}
