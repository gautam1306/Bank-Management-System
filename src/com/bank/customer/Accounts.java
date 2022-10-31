package com.bank.customer;



import java.util.ArrayList;
import java.util.Scanner;

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
//	public void functions() {
//		ArrayList<Object> cardDetail = dao.getCard(accountNumber);
//		if(cardDetail!=null) {
//			card=new Card(cardDetail);
//		}
//    	System.out.println("Successfully logged in");
//    	
//        Scanner sc = new Scanner(System.in);
//	    while(true) {
//	        System.out.println("Press 1 to transfer amount to a benificiary");
//	        System.out.println("Press 2 to transfer amount using the account number");
//	        System.out.println("Press 3 to display your balance");
//	        System.out.println("Press 4 to display transaction history");
//	        System.out.println("Press 5 to add Beneficiary");
//	        System.out.println("Press 6 to view the account details");
//			System.out.println("Press 7 to view all your beneficiary");
//			System.out.println("Press 8 to view the debit card detail");
//			System.out.println("Press 9 for loan repayment using loan id");
//	        System.out.println("Press 10 to the home page");
//		    int x = Main.getint();
//			switch (x) {
//			    case 1:
//			    {  System.out.println("Enter the account number of the benificiary");
//			        int acc = Main.getint();
//			        System.out.println("Enter the amount to be transfered to the benificiary");
//			        int amount = Main.getint();
//			        if(balance<amount){
//			            System.out.println("The balance is insufficient for this transaction");
//			            break;
//			        }
//			        int[] arr = dao.getBenificiary(accountNumber,acc);
//			        if(arr==null){
//			            System.out.println("There is no benificiary with this number associated with you");
//			        }
//			        else{
//			            int accountbalance = arr[0];
//			            int transferLimit= arr[1];
//			            int transfertaken = arr[2];
//			            if(transferLimit>=transfertaken+amount ||  accountType.equals("Current")){
//			                balance-=amount;
//			                accountbalance+= amount;
//			                System.out.println("Enter the reason for transfer");
//			                String discription = sc.next();
//			                System.out.println("Press 1 for IMPS/nPress 2 for NEFT/nPress 3 for RTGS");
//			                int type= sc.nextInt();
//			                String mode = type==1?"imps":(type==2?"neft":"rtgs");
//			                dao.transferMethod(accountNumber,acc,amount,1,discription,mode);
//			            }
//			        }}
//			        break;
//			    case 2:
//			    { System.out.println("Enter the account number to which the money has to be transfered");
//			        int acc = sc.nextInt();
//			        System.out.println("Enter the amount to be transfered");
//			        int amount = sc.nextInt();
//			        if ( acc != accountNumber) {
//			            if (balance+overdraft< amount ) {
//			                System.out.println("Insufficient balance");
//			            }
//			            else if (amount+transfer<=transactionlimit ||  accountType.equals("Current")) {
//			                System.out.println("Enter the reason for transfer");
//			                String discription = sc.next();
//			                System.out.println("Press 1 for IMPS\nPress 2 for NEFT\nPress 3 for RTGS");
//			                int type= sc.nextInt();
//			                String mode = type==1?"imps":(type==2?"neft":"rtgs");
//			                dao.transferMethod(accountNumber, acc, amount,0,discription,mode);
//			            } else {
//			                System.out.println("this transaction exceeds Daily Transfer limit");
//			            }
//			        } else {
//			            System.out.println("the Account number is incorrect");
//			        }}
//			        break;
//			    case 3:
//			        System.out.println("The Balance of your account is " + balance);
//			        break;
//			    case 4:
//			        dao.transactions(accountNumber,balance);
//			        break;
//			    case 5:
//			        addBeneficiary();
//			        break;
//			    case 6:
//			    	getDetails();
//			    	break;
//			    case 7:
//			    	getbeneficiaryList();
//			        break;
//			    case 8:
//			    	if(card!=null) {System.out.println(card);}
//			    	else {
//			    		System.out.println("You don't have a debit card");
//			    	}
//			    	break;
//			    case 9:
//			    {
//			    	System.out.println("Enter the loan ID for which you are going to do a part payment");
//			    	int loanID = sc.nextInt();
//			    	System.out.println("Enter the amount you are going to pay towards the part payment");
//			    	int amount=sc.nextInt();
//			    	int errorcode;
//			    	if(amount<=balance) {
//			    		errorcode = dao.loanPayment(accountNumber,loanID,amount);
//			    		System.out.println(errorcode);
//			    	}
//			    	
//			    	else {
//			    		System.out.println("You don't have enough balance for this transaction");
//			    	}
//			    }
//			    case 10:
//			    	System.out.println("Logged out successfully");
//			    	return;
//			    default: 
//			        break;
//			        }
//			}
//	}
    public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Accounts(int accountNumber, int balance, int transfer, String accountType, String ifsc,int transactionlimit, int overdraft) {
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
		ArrayList<Beneficiary> arr =  dao.getBenificiaryList(accountNumber);
		int i=0;
		while(arr.size()>i) {
			System.out.println(arr.get(i));
			i++;
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str;
		str= "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
    	str = str.concat("Account Number   : "+accountNumber+"\n");
    	str = str.concat("Account Type     : "+accountType+"\n");
    	str = str.concat("IFSC code        : "+ifsc+"\n");
    	str = str.concat("Today's Transfer : "+transfer+"\n");
    	str = str.concat("Balance          : "+balance+"\n");
    	str = str.concat("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	
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
	public Card getCard() {
		return card;
	}
	private void getDetails() {
    	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    	System.out.println("Account Number   : "+accountNumber );
    	System.out.println("Account Type     : "+accountType);
    	System.out.println("IFSC code        : "+ifsc );
    	System.out.println("Today's Transfer : "+transfer);
    	System.out.println("Balance          : "+balance);
    	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}
//	private void addBeneficiary(){
//		System.out.println("Press 1 if the beneficiary's account is in the same bank");
//		System.out.println("Press 2 if the beneficiary's account is in a diffeerent bank");
//		int option = Main.getint();
//		switch(option) {
//			case 1:{
//		        System.out.println("Please enter the account number that should be added to your beneficiary");
//		        int acc = Main.getint();
//		        System.out.println("Please enter the maximum transferlimit for this beneficiary");
//		        int transferlimit =Main.getint();
//		        if(acc== accountNumber) {
//		        	System.out.println("Cann't add the same account number");
//		        	return;
//		        }
//		        dao.addBeneficiary(accountNumber,acc,transferlimit);
//	        }
//			case 2:{
//				System.out.println("Please enter the account number that should be added to your beneficiary");
//				int acc = Main.getint();
//		        System.out.println("Please enter the maximum transferlimit for this beneficiary");
//		        int transferlimit =Main.getint();
//		        System.out.println("Please enter the IFSC of the bank account");
//		        String beneficiaryifsc = new Scanner(System.in).next();
//		        if(beneficiaryifsc.length()!=11) {
//		        	System.out.println("Please try again with a valid ifsc");
//		        }
//		        dao.beneficiaryAdditionRequest(accountNumber,acc,transferlimit,beneficiaryifsc);
//			}
//		}
//    }
}
