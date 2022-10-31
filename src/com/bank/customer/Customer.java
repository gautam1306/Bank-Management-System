package com.bank.customer;

import com.bank.*;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Customer{
	int customerID;
	String password;
	private Profile profile;
	private TreeMap<Integer, Accounts> accounts;
	private CustomerDao dao = new CustomerDao();
	private TreeMap<Integer, FixedDeposits> fixedDeposits;
	private TreeMap<Integer, RecurringDeposit> recurringDeposit;
	private TreeMap<Integer, Loan> loans;
	public Customer(int customerId, String password,ArrayList<String> property) {
		customerID = customerId;
		this.password = password;
		if(property==null) {
			System.out.println("Incorrect Credentials");
		}
		else{
			profile = new Profile(property.get(0),property.get(1),property.get(2),  property.get(3),  property.get(4),  property.get(5),  property.get(6),  property.get(7),  property.get(8),  property.get(9),Integer.parseInt(property.get(10)));
			accounts = dao.getAccounts(customerId);
			recurringDeposit= dao.getRecurringDeposit(customerId); 
			fixedDeposits = dao.getFixedDeposits(customerId);
		}
	}
//	public void function() {
//		while(true) {
//			System.out.println("Press 1 to List the accounts associated with this Customer and enter into one of the account using account number");
//			System.out.println("Press 2 to list the different fixed deposit accounts and enter using the deposit id");
//			System.out.println("Press 3 to see the recurring deposit");
//			System.out.println("Press 4 to add a recurring deposit");
//			System.out.println("Press 5 to add a fixed deposit");
//			System.out.println("Press 6 to change the password");
//			System.out.println("Press 7 to view Customer");
//			System.out.println("Press 8 to view loan history");
//			System.out.println("Press 9 to logout of the session");
//			int x = Main.getint();
//			switch (x) {
//			case 1: {
//				if(accounts.isEmpty()) {
//					System.out.println("You dont have an account.Please visit the nearby branch to create an account");
//					break;
//				}
//				System.out.println("Account Number | Type of Account");
//				for(int i:accounts.keySet()) {
//					System.out.println(i+"  |  "+accounts.get(i).getAccountType());
//				}
//				System.out.println("Enter the Account number that should be entered");
//				int y = Main.getint();
//				if(accounts.containsKey(y)) {
//				accounts.get(y).functions();
//				}
//				else {
//					System.out.println("You Have entered an invalid account");
//				}
//				break;
//			}
//			case 2:{
//				if(fixedDeposits==null) {
//					System.out.println("You don't have a fixed deposit account");
//					break;
//				}
//				for(int i:fixedDeposits.keySet()) {
//					System.out.println(i);
//				}
//				System.out.println("Enter the deposit id that should be entered");
//				int y = Main.getint();
//				break;}
//			case 3:
//				{if (recurringDeposit.isEmpty()){
//					System.out.println("You don't have any reccuring deposit");
//				}else{
//					for(int i:recurringDeposit.keySet()) {
//					System.out.println(i);
//				}
//				System.out.println("Enter the deposit id that should be entered");
//				int y = Main.getint();
//				if(recurringDeposit.containsKey(y)) {
//				recurringDeposit.get(y).functions(password);}
//				else {
//					System.out.println("You Have entered an invalid deposit id");
//				}
//				}break;}
//			case 4:{
//				System.out.println("Enter the Monthly investment amount");
//				int amount = Main.getint();
//				System.out.println("The interval of your deposit(Months)");
//				int period = Main.getint();
//				System.out.println("Enter the Account Number which is going to be associated with this deposit.\nNote it should be an account associated with this customerId");
//				int accountnumber = Main.getint();
//				if(accounts.containsKey(accountnumber)) {
//					dao.addRecurringDeposit(customerID,accountnumber,period,amount);
//				}
//				else {
//					System.out.println("Sorry You have entered an accountnumber that is not associated with your account");
//				}break;}
//			case 5:
//				{
//					System.out.println("Enter the investment amount");
//					int amount = Main.getint();
//					System.out.println("The interval of your deposit(Months)");
//					int period = Main.getint();
//					System.out.println("Enter the Account Number which is going to be associated with this deposit and that account should have the amount required for the deposit.\nNote it should be an account associated with this customerId");
//					int accountnumber = Main.getint();
//					if(accounts.containsKey(accountnumber)) {
//						dao.addFixedDeposit(customerID,accountnumber,period,amount);
//					}
//					else {
//						System.out.println("Sorry You have entered an accountnumber that is not associated with your account");
//					}
//					break;
//				}
//			case 6:{
//				System.out.println("Enter Your Aadhaar number for changing your password");
//				String aadhaar = new Scanner(System.in).next();
//				if(aadhaar.equals(profile.getAadhaar())) {
//					System.out.println("Enter the new Password");
//					String password = new Scanner(System.in).next();
//					dao.changePassword(customerID,password);
//				}
//				break;
//			}
//			case 7:{
//				view();
//				break;
//			}
//			case 8:
//			{
//				dao.loanHistory(customerID);
//			}
//			case 9:
//				return;
//			default:
//				System.out.println("Please try with a valid option");
//			}
//		}
//	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public TreeMap<Integer, Accounts> getAccounts() {
		return accounts;
	}

	public void setAccounts(TreeMap<Integer, Accounts> accounts) {
		this.accounts = accounts;
	}

	public CustomerDao getDao() {
		return dao;
	}

	public void setDao(CustomerDao dao) {
		this.dao = dao;
	}

	public TreeMap<Integer, FixedDeposits> getFixedDeposits() {
		return fixedDeposits;
	}

	public void setFixedDeposits(TreeMap<Integer, FixedDeposits> fixedDeposits) {
		this.fixedDeposits = fixedDeposits;
	}

	public TreeMap<Integer, RecurringDeposit> getRecurringDeposit() {
		return recurringDeposit;
	}

	public void setRecurringDeposit(TreeMap<Integer, RecurringDeposit> recurringDeposit) {
		this.recurringDeposit = recurringDeposit;
	}

	public TreeMap<Integer, Loan> getLoans() {
		return loans;
	}

	public void setLoans(TreeMap<Integer, Loan> loans) {
		this.loans = loans;
	}

	public void view(){
		System.out.println(profile);
	}
}
