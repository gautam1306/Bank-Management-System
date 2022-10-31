package com.bank.customer;

import java.util.Formatter;

public class Beneficiary {
	int accountnumber;
	String firstName;
	String lastName;
	String ifsc;
	int transactionlimit;
	@Override
	public String toString() {
		Formatter format = new Formatter();
		format.format("|%5d|%20s|%20s|%20s|%10d|",accountnumber,firstName,lastName,ifsc,transactionlimit);
		return format.toString();
 	}
	
}
