package com.bank.bankEmployeeServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.bankEmployee.Employee;
import com.bank.bankEmployee.EmployeeCapsule;
import com.bank.bankEmployee.EmployeeLogin;

/**
 * Servlet implementation class Employee
 */
@WebServlet("/EmployeeLogin")
public class EmployeeValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeValidation() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		String password = request.getParameter("password");
		EmployeeCapsule employee = new EmployeeLogin().validate(employeeId, password);
		HttpSession session = request.getSession();
		switch (employee.getEmployeeRole()) {
			case 3: {
				session.setAttribute("accountant", employee.getEmployee());
				response.sendRedirect("accountant-home");
				break;
			}
			case 2: {
				session.setAttribute("branchAdmin", employee.getEmployee());
				response.sendRedirect("branch_admin-home");
				break;
			}
			case 1: {
				session.setAttribute("manager", employee.getEmployee());
				response.sendRedirect("manager-home");
				break;
			} 
		}
		
	}

}
