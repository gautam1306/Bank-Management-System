package com.bank.customer;

public class Beneficiary {
	private int accountnumber;
	private String nickName;
	private String ifsc;
	private int transactionlimit;
	private int transfer;
	public int getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(int accountnumber) {
		this.accountnumber = accountnumber;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String firstName) {
		this.nickName = firstName;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public int getTransactionlimit() {
		return transactionlimit;
	}
	public void setTransactionlimit(int transactionlimit) {
		this.transactionlimit = transactionlimit;
	}
	public int getTransfer() {
		return transfer;
	}
	public void setTransfer(int transfer) {
		this.transfer = transfer;
	}
	
}
