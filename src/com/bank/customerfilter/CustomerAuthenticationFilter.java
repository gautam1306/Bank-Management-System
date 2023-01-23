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

/**
 * Servlet Filter implementation class CustomerAuthenticationFilter
 */
public class CustomerAuthenticationFilter implements Filter {

    /**
     * Default constructor. 
     */

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;
        HttpSession session = request1.getSession(false);
        String loginURI = request1.getContextPath() + "/home";
//        System.out.println(loginURI);
        boolean loggedIn = session != null && session.getAttribute("customer") != null;
        boolean loginRequest = request1.getRequestURI().equals(loginURI);
        if (loggedIn || loginRequest) {
            chain.doFilter(request1, response1);
        } else {
            response1.sendRedirect(loginURI);
        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
}
