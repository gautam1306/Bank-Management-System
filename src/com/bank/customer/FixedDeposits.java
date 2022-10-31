package com.bank.customer;
import java.util.Scanner;

public class FixedDeposits {
	private int depositId;
	private int accountnumber;
	private int amount;
	private String maturityDate;
	private String startDate;
	private float intrestRate;
	private int customerId;
	private FixedDepositDao dao = new FixedDepositDao();
	public FixedDeposits(int customerId,int depositId, int accountnumber, int amount, String maturityDate, String startDate, float intrestRate ) {
		this.customerId = customerId;
		this.depositId =depositId;
		this.accountnumber = accountnumber;
		this.amount =amount;
		this.maturityDate = maturityDate;
		this.startDate = startDate;
		this.intrestRate = intrestRate;
	}
	public void function(String password) {
		System.out.println("Press 1 to get Details of the account");
		System.out.println("Press 2 for a premature withdrawl of the deposit");
		int x=1;
		switch (x) {
		case 1: {
			System.out.println("Customer ID         : "+customerId);
			System.out.println("Deposit ID          : "+depositId);
			System.out.println("Account Number      : "+accountnumber);
			System.out.println("Amount Invested     : "+amount);
			System.out.println("Date of Deposit     : "+startDate);
			System.out.println("Maturity Date       : "+maturityDate);
			System.out.println("Rate of Intrest(PA) : "+intrestRate);
			break;
		}
		case 2:{
			System.out.println("Enter the Password to proceed with your request");
			Scanner sc = new Scanner(System.in);
			String pass = sc.next();
			if(pass.equals(password)) {
				dao.transferamount(depositId,accountnumber,intrestRate);
			}
			else {
				System.out.println("Please Try Again with the correct credentials");
			}
		}
		default:
			System.out.println("Enter a valid option");
		}
		
	}
}
