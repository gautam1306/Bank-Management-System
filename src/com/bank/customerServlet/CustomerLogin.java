package com.bank.customerServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.customer.Customer;
import com.bank.customer.CustomerValidation;

@WebServlet("/customer")
public class CustomerLogin extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		int customerID = Integer.parseInt(request.getParameter("customerID"));
		String password = request.getParameter("password");
		response.getWriter().print(customerID);
//		response.getWriter().print(request.getParameter("password"));
		Customer customer = new CustomerValidation().verify(customerID,password);
		HttpSession session = request.getSession();
		if(customer!=null) {
			Cookie cookie = new Cookie("customerId",Integer.toString(customerID));
			response.addCookie(cookie);
			session.setAttribute("customer", customer);
			response.sendRedirect("customer-dashboard");	
		}
		else {
			request.setAttribute("errorMessage", "Invalid user or password");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
		}catch(NumberFormatException e) {
			response.sendRedirect("home");
		}
	}
}
