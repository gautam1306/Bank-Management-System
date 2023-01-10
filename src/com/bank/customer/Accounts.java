package com.bank.customer;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	private void setCard() {
		ArrayList<Object> list = new ArrayList<>();
		if(!list.isEmpty()) {
		card = new Card(list);}
		else {
			card=null;
		}
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


	public Card getCard() {
		if(card==null) {
			setCard();
		}
		return card;
	}

	public ArrayList<Transaction> getTransactions() {
		return dao.transactions(accountNumber, balance);
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
	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int beneficiaryfundtransfer(int toAccountNumber, int amount, String description, String mode,int transferLimit,int transfer) {
		if (toAccountNumber != accountNumber) {
			if (balance + overdraft < amount) {
//				System.out.println("Insufficient balance");
				return 0;
			} else if (amount + transfer <= transactionlimit || accountType.equals("Current")) {
				dao.transferMethod(accountNumber, toAccountNumber, amount, 1, description, mode);
				return 1;
			} else {
//				System.out.println("this transaction exceeds Daily Transfer limit");
				return -2;
			}
		} else {
			return -1;
		}
	}
	public int loanrepayment(Loan loan,int amount) {
		if(amount>balance) {
			return 1;
		}
		else if(loan==null) {
			return -1;
		}
		
		else {
			float interestamount;
			int payableamount;
			if((float)amount>loan.interestPayable) {
				interestamount =0;
			}
			else {
				interestamount = loan.interestPayable-amount;
			}
			if(loan.payableAmount<(amount-(int)loan.interestPayable)) {
				amount = loan.payableAmount +(int)loan.interestPayable;
				payableamount=0;
			}
			else {
				payableamount =(loan.interestPayable-amount<0?loan.payableAmount-amount+(int)loan.interestPayable:loan.payableAmount);
			}
			loan.interestPayable = interestamount;
			loan.payableAmount = payableamount;
			dao.loanPayment(accountNumber, loan, amount);
			return 0;
		}
		
	}
}
