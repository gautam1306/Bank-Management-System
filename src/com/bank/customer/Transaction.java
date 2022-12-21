package com.bank.customer;

public class Transaction {
	private int transaction_number;
	private int from_account_number;
	private int to_account_number;
	private String date_of_transaction;
	private int type_of_transaction;
	private String description;
	private int transferamount;
	private String mode;
	private int balance;
	public Transaction(int transaction_number, int from_account_number, int to_account_number,
			String date_of_transaction, int type_of_transaction, String description, int transferamount, String mode,int balance) {
		super();
		this.transaction_number = transaction_number;
		this.from_account_number = from_account_number;
		this.to_account_number = to_account_number;
		this.date_of_transaction = date_of_transaction;
		this.type_of_transaction = type_of_transaction;
		this.description = description;
		this.transferamount = transferamount;
		this.mode = mode;
		this.balance = balance;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getTransaction_number() {
		return transaction_number;
	}
	public void setTransaction_number(int transaction_number) {
		this.transaction_number = transaction_number;
	}
	public int getFrom_account_number() {
		return from_account_number;
	}
	public void setFrom_account_number(int from_account_number) {
		this.from_account_number = from_account_number;
	}
	public int getTo_account_number() {
		return to_account_number;
	}
	public void setTo_account_number(int to_account_number) {
		this.to_account_number = to_account_number;
	}
	public String getDate_of_transaction() {
		return date_of_transaction;
	}
	public void setDate_of_transaction(String date_of_transaction) {
		this.date_of_transaction = date_of_transaction;
	}
	public int getType_of_transaction() {
		return type_of_transaction;
	}
	public void setType_of_transaction(int type_of_transaction) {
		this.type_of_transaction = type_of_transaction;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String discription) {
		this.description = discription;
	}
	public int getTransferamount() {
		return transferamount;
	}
	public void setTransferamount(int transferamount) {
		this.transferamount = transferamount;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
}
