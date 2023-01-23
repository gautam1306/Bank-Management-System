package com.bank.customerfilter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.customer.Customer;

/**
 * Servlet Filter implementation class RecurringDepositFilter
 */
@WebFilter("/RecurringDepositFilter")
public class RecurringDepositFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public RecurringDepositFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request1 = (HttpServletRequest) request;
		HttpServletResponse response1 = (HttpServletResponse) response;
		HttpSession session = request1.getSession(false);
		String loginURI = request1.getContextPath() + "/invest";
		Customer customer = (Customer) session.getAttribute("customer");
//        System.out.println(loginURI);
		int depositID = Integer.parseInt(request1.getParameter("depositID"));
		boolean loggedIn = session != null && session.getAttribute("recurringDeposit") != null;
//		System.out.println("inside filter");
		if (loggedIn) {
			chain.doFilter(request1, response1);
		} else if (customer.getRecurringDeposit().containsKey(depositID)) {
			session.setAttribute("recurringDeposit", customer.getRecurringDeposit().get(depositID));
			chain.doFilter(request1, response1);
		} else {
			response1.sendRedirect("invest");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
