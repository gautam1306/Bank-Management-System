package com.bank.customer;

public class Loan {
    int loanID;
    float interestPayable;
    float interestRate;
    String maturityDate;
    int payableAmount;
    int loanAmount;
    boolean status;
    String loanType;
    int customerID;
	public Loan(int loanID, float interestPayable, float interestRate, String maturityDate, int payableAmount,
			int loanAmount, boolean status, String loanType, int customerID) {
		super();
		this.loanID = loanID;
		this.interestPayable = interestPayable;
		this.interestRate = interestRate;
		this.maturityDate = maturityDate;
		this.payableAmount = payableAmount;
		this.loanAmount = loanAmount;
		this.status = status;
		this.loanType = loanType;
		this.customerID = customerID;
	}
	public int getLoanID() {
		return loanID;
	}
	public float getInterestPayable() {
		return interestPayable;
	}
	public float getInterestRate() {
		return interestRate;
	}
	public String getMaturityDate() {
		return maturityDate;
	}
	public int getPayableAmount() {
		return payableAmount;
	}
	public int getLoanAmount() {
		return loanAmount;
	}
	public boolean isStatus() {
		return status;
	}
	public String getLoanType() {
		return loanType;
	}
	public int getCustomerID() {
		return customerID;
	}
    
}