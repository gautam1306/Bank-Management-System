package com.bank.employeeServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.startup.PasswdUserDatabase;

import com.bank.PasswordEncoder;
import com.bank.bankEmployee.Accountant;
import com.bank.bankEmployee.Employee;
import com.bank.bankEmployee.EmployeeCapsule;
import com.bank.customer.Accounts;

/**
 * Servlet implementation class AddCustomer
 */
@WebServlet("/addCustomerServlet")
public class AddCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Accountant employee = (Accountant) session.getAttribute("accountant");
		int gender = (request.getParameter("gender") == "female" ? 1 : 2);
		String password = new PasswordEncoder().encode(request.getParameter("password"));
		int status = employee.createCustomer(request.getParameter("customerName"), request.getParameter("aadharNumber"),
				request.getParameter("dob"), request.getParameter("number"), request.getParameter("pan"), gender,
				request.getParameter("address_house"), request.getParameter("address_street"),
				request.getParameter("address_district"), request.getParameter("address_state"),
				Integer.parseInt(request.getParameter("address_pincode")), password);
		switch (status) {
		case 1: {
			request.setAttribute("status", "customer has been successfully created");
			break;
		}
		case -1:{
			
		}
		}

	}

}
