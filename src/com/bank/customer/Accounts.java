package com.bank.customer;

import java.util.ArrayList;
import java.util.HashMap;

public class Accounts {
	private int overdraft;
	private int accountNumber;
	private int balance;
	private int transfer;
	private String accountType;
	private String ifsc;
	private AccountsDao dao = new AccountsDao();
	private int transactionlimit;
	private Card card;
	private HashMap<Integer, Beneficiary> beneficiaryList;
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Accounts(int accountNumber, int balance, int transfer, String accountType, String ifsc, int transactionlimit,
			int overdraft) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.transfer = transfer;
		this.accountType = accountType;
		this.ifsc = ifsc;
		this.transactionlimit = transactionlimit;
		this.overdraft = overdraft;
	}

	private void getbeneficiaryList() {
		beneficiaryList = dao.getBenificiaryList(accountNumber);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str;
		str = "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
		str = str.concat("Account Number   : " + accountNumber + "\n");
		str = str.concat("Account Type     : " + accountType + "\n");
		str = str.concat("IFSC code        : " + ifsc + "\n");
		str = str.concat("Today's Transfer : " + transfer + "\n");
		str = str.concat("Balance          : " + balance + "\n");
		str = str.concat(
				"----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		return str;
	}

	public int getOverdraft() {
		return overdraft;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public int getBalance() {
		return balance;
	}

	public int getTransfer() {
		return transfer;
	}

	public String getIfsc() {
		return ifsc;
	}

	public AccountsDao getDao() {
		return dao;
	}

	public int getTransactionlimit() {
		return transactionlimit;
	}

	public HashMap<Integer, Beneficiary> getBeneficiaryList() {
		if (beneficiaryList == null) {
			getbeneficiaryList();
		}
		return beneficiaryList;
	}

	public Card getCard() {
		return card;
	}

	public ArrayList<Transaction> getTransactions() {
		return dao.transactions(accountNumber, balance);
	}

	public void addBeneficiary(int beneficiaryaccount,int transferLimit, String nickname) {
		if(beneficiaryList==null) {
			getBeneficiaryList();
		}
		if (beneficiaryaccount== accountNumber || beneficiaryList.containsKey(beneficiaryaccount)) {
			System.out.println("Cann't add the same account number");
			return;
		}
		dao.addBeneficiary(accountNumber, beneficiaryaccount, transferLimit,nickname);
	}

	public int fundtransfer(int toAccountNumber, int amount, String description, String mode) {
		if (toAccountNumber != accountNumber) {
			if (balance + overdraft < amount) {
//				System.out.println("Insufficient balance");
				return 0;
			} else if (amount + transfer <= transactionlimit || accountType.equals("Current")) {
				dao.transferMethod(accountNumber, toAccountNumber, amount, 0, description, mode);
				return 1;
			} else {
//				System.out.println("this transaction exceeds Daily Transfer limit");
				return -2;
			}
		} else {
			return -1;
		}
	}

	public void beneficiaryfundtransfer(int toAccountNumber, int amount, String description, String mode) {
		if (beneficiaryList == null) {
			getbeneficiaryList();
		}
		int transferLimit = beneficiaryList.get(toAccountNumber).getTransactionlimit();
		int transfer = beneficiaryList.get(toAccountNumber).getTransfer();
		if (transferLimit >= transfer + amount || accountType.equals("Current")) {
			balance -= amount;
			dao.transferMethod(accountNumber, toAccountNumber, amount, 1, description, mode);

		}
	}
}
