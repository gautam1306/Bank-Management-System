package com.bank.customer;

public class RecurringDeposit {
	int depositid;
	int amount;
	int depositamount;
	String maturityDate;
	String startDate;
	int accountnumber;
	float intrestrate;
	float intrestamount;
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

	public float getIntrestrate() {
		return intrestrate;
	}

	public float getIntrestamount() {
		return intrestamount;
	}

	public int getCustomerID() {
		return customerID;
	}

	public RecurringDepositDao getDao() {
		return dao;
	}
	protected RecurringDeposit(int depositid, int amount, int depositamount, String maturityDate, String startDate,
			int accountnumber, float intrestrate, float intrestamount, int customerID) {
		super();
		this.depositid = depositid;
		this.amount = amount;
		this.depositamount = depositamount;
		this.maturityDate = maturityDate;
		this.startDate = startDate;
		this.accountnumber = accountnumber;
		this.intrestrate = intrestrate;
		this.intrestamount = intrestamount;
		this.customerID = customerID;
	}

	private RecurringDepositDao dao = new RecurringDepositDao();
//	public void functions(String password) {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Press 1 for premature withdrawal");
//		System.out.println("Press 2 to view the deposit account");
//		int x = Main.getint();
//		switch (x) {
//			case 1: {
//					System.out.println("Enter your customer password to process the payment");
//					String pwd= sc.next();
//					int total_amount = (int) (intrestamount+depositamount);
//					if(pwd.equals(password)) {
//						dao.prematureWithdrawl(accountnumber,total_amount, depositid);
//					}
//				else {
//					System.out.println("Please try again with the correct credential");
//				}
//				break;
//			}
//			case 2:{
//				System.out.println("Deposit ID                                 : "+depositid);
//				System.out.println("Monthly Investment Amount                  : "+amount);
//				System.out.println("Total Amount Deposited                     : "+depositamount);
//				System.out.println("Total Intrest                              : "+intrestamount );
//				System.out.println("Maturity Date                              : "+maturityDate);
//				System.out.println("Account Number Associated with the deposit : "+accountnumber );
//				break;
//			}
//			default:
//				throw new IllegalArgumentException("Unexpected value: " + x);
//		}
//	}
}
