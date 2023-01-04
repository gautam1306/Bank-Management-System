package com.bank.customer;

import java.util.ArrayList;
import java.util.HashMap;
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
	private HashMap<Integer, Beneficiary> beneficiaryList;

	public Customer(int customerId, String password,ArrayList<String> property) {
		customerID = customerId;
		this.password = password;
		if(property!=null){
			profile = new Profile(property.get(0),property.get(1),property.get(2),  property.get(3),  property.get(4),  property.get(5),  property.get(6),  property.get(7),  property.get(8),  property.get(9),Integer.parseInt(property.get(10)));
			accounts = dao.getAccounts(customerId);
			recurringDeposit= dao.getRecurringDeposit(customerId); 
			fixedDeposits = dao.getFixedDeposits(customerId);
		}
	}

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
		getLoansDB();
		return loans;
	}
	public void getLoansDB() {
		loans=dao.getloanAccounts(customerID);
	}
	public void setLoans(TreeMap<Integer, Loan> loans) {
		this.loans = loans;
	}

	public void view(){
		System.out.println(profile);
	}
	private void getbeneficiaryList() {
		beneficiaryList = dao.getBenificiaryList(customerID);
	}
	public int addBeneficiary(int beneficiaryaccount,int transferLimit, String nickname) {
		if(beneficiaryList==null) {
			getBeneficiaryList();
		}
		if (beneficiaryaccount== customerID || beneficiaryList.containsKey(beneficiaryaccount)) {
			System.out.println("Cann't add the same account number");
			return -1;
		}
		return dao.addBeneficiary(customerID, beneficiaryaccount, transferLimit,nickname);
	}
	public HashMap<Integer, Beneficiary> getBeneficiaryList() {
		if (beneficiaryList == null) {
			getbeneficiaryList();
		}
		return beneficiaryList;
	}

}
