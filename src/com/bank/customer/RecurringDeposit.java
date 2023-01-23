package com.bank.customer;

public class RecurringDeposit {
	int depositid;
	int amount;
	int depositamount;
	String maturityDate;
	String startDate;
	int accountnumber;
	float interestrate;
	float interestamount;
	int customerID;
	public int getDepositid() {
		return depositid;
	}

	public int getAmount() {
		return amount;
	}

	public int getDepositamount() {
		return depositamount;
	}

	public String getMaturityDate() {
		return maturityDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public int getAccountnumber() {
		return accountnumber;
	}

	public float getInterestrate() {
		return interestrate;
	}

	public float getInterestamount() {
		return interestamount;
	}

	public int getCustomerID() {
		return customerID;
	}

	public RecurringDepositDao getDao() {
		return dao;
	}
	protected RecurringDeposit(int depositid, int amount, int depositamount, String maturityDate, String startDate,
			int accountnumber, float interestrate, float interestamount, int customerID) {
		super();
		this.depositid = depositid;
		this.amount = amount;
		this.depositamount = depositamount;
		this.maturityDate = maturityDate;
		this.startDate = startDate;
		this.accountnumber = accountnumber;
		this.interestrate = interestrate;
		this.interestamount = interestamount;
		this.customerID = customerID;
	}

	private RecurringDepositDao dao = new RecurringDepositDao();
	public int prematureWithdrawal() {
		int totalAmount= (int) (interestamount)+depositamount;
		return dao.prematureWithdrawal(accountnumber, totalAmount, depositid);
	}
}
