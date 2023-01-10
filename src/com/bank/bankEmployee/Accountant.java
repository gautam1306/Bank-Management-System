package com.bank.bankEmployee;

import com.bank.Profile;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Accountant implements Employee {
	private int employeeID;
	private AccountantDao dao = new AccountantDao();
	private Profile profile;
	private TreeMap<Integer, CardCompany> company;
	String ifsc;
	private String passwords;

	public Accountant(int employeeID, String passwords) {
		this.employeeID = employeeID;
		this.passwords = passwords;
		initialize();
	}

	public void function() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Press 1 to create a new customer");
			System.out.println("Press 2 to create a new Account using customer id");
			System.out.println("Press 3 to deposit amount to the Customers Account using account number");
			System.out.println("Press 4 to close an existing account");
			System.out.println("Press 5 to issue a debit card  to an account holder");
			System.out.println("Press 6 to logout");
			int x = Main.getint();
			switch (x) {
			case 1: {
				System.out.println("Please Enter customer Name");
				String name = sc.nextLine();
				System.out.println("Please Enter customer aadhaar number");
				String aadhaar = sc.nextLine();
				System.out.println("Please Enter customer Date of Birth in the format of yyyy-mm-dd");
				String dob = sc.nextLine();
				System.out.println("Please Enter customer Mobile Number");
				String number = sc.nextLine();
				System.out.println("Please Enter customer Pancard Details");
				String pan = sc.nextLine();
				System.out.println("Enter 1 if customer are a female");
				System.out.println("Enter 2 if customer are a male");
				int gender = Main.getint();
				System.out.println("Enter the house name/ house number of the customer");
				String address_house = sc.nextLine();
				System.out.println("Enter the street in which the customer house is present");
				String address_street = sc.nextLine();
				System.out.println("Enter the district in which the customer house is present");
				String address_district = sc.nextLine();
				System.out.println("Enter the state in which the customer house is present");
				String address_state = sc.nextLine();
				System.out.println("Enter the pin code of the area where customer house is present");
				int address_pin_code = sc.nextInt();

				int customerid = createCustomer(name, aadhaar, dob, number, pan, gender, address_house, address_street,
						address_district, address_state, address_pin_code);
				if (customerid != -1) {
					System.out.println("Customer Successfully Created and this is your customer id : " + customerid);
				}
				break;
			}
			case 2: {
				System.out.println("Please Enter Your customer id");
				int customer_id = Main.getint();
				// System.out.println("Choose the nearest possible bank location which you can
				// access");
				System.out.println("Press 1 for creating a Savings Account");
				System.out.println("Press 2 for creating a Current Account");
				int m = Main.getint();
				int transferLimit = 0;
				int overdraftLimit = 0;
				if (m == 2) {
					System.out.println("Enter the overdraft limit of the account");
					overdraftLimit = Main.getint();
				}
				if (m == 1) {
					System.out.println("Enter the transfer limit of the account");
					transferLimit = Main.getint();
				}
				addAccount(customer_id, m, overdraftLimit, transferLimit);
				break;
			}
			case 3: {
				System.out.println("Please Enter the Account Number");
				int acc = Main.getint();
				System.out.println("Please Enter the Amount to be deposited");
				int amount = Main.getint();
				depositAmount(amount, acc);
				break;
			}
			case 4: {
				System.out.println("Enter the account number of the user that should be closed");
				int accountNumber = Main.getint();
				dao.deleteAccount(accountNumber);
			}
			case 5: {
				System.out.println("Enter the accountnumber that needs the debit card");
				int accountnumber = Main.getint();
				System.out.println("Enter the transaction limit of that card(in ruppees)");
				int transferlimit = Main.getint();
				System.out.println("Enter the id of the card you want");
				int companyid = Main.getint();
				addDebitCard(accountnumber, transferlimit, companyid);
				break;
			}
			case 6: {
				return;
			}
			default: {
				// sc.close();
				System.out.println("Enter a valid option");
				break;
			}
			}
		}
	}

	private void initialize() {
		ArrayList<String> arr = dao.getEmployee(employeeID);
		ifsc = (String) arr.get(0);
		profile = new Profile((String) arr.get(1), (String) arr.get(2), (String) arr.get(3), (String) arr.get(4),
				(String) arr.get(5), (String) arr.get(6), (String) arr.get(7), (String) arr.get(8), (String) arr.get(9),
				(String) arr.get(10), Integer.parseInt(arr.get(11)));
		System.out.println("Welcome to HDFC Bank");
	}

	public void viewEmployee() {
		System.out.println("Employee ID : " + employeeID);
		System.out.println("Employee Role : Accountant");
		System.out.println("Branch IFSC : " + ifsc);
		System.out.println(profile);
	}

	public int createCustomer(String name, String aadhaar_number, String date_of_birth, String mobile_number,
			String pancard, int gender, String address_house, String address_street, String address_district,
			String address_state, int address_pin_code) {
		if (verifyaadhaar(aadhaar_number)) {
			return dao.createCustomer(name, aadhaar_number, date_of_birth, mobile_number, pancard, gender,
					address_house, address_street, address_district, address_state, address_pin_code);
		} else {
			System.out.println("Incorrect aadhaar number format");
			return -1;
		}
	}

	private boolean verifyaadhaar(String aadhaar_number) {
		String regex = "[0-9]+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(aadhaar_number);
		if (m.matches() && aadhaar_number.length() == 12) {
			return true;
		} else {
			return false;
		}
	}

	private void addAccount(int customer_id, int m, int overdraftLimit, int transferLimit) {
		dao.addAccount(customer_id, ifsc, m, overdraftLimit, transferLimit);
	}

	private void addDebitCard(int accountnumber, int transferlimit, int companyid) {
		if (company.containsKey(companyid)) {
			dao.addDebitCard(accountnumber, transferlimit, companyid);
		} else {
			System.out.println("Sorry the company id does not exist");
		}
	}

	private void viewCardCompany() {
		if (company == null) {
			company = dao.getCardCompany();
		}
		for (int i : company.keySet()) {
			System.out.println(company.get(i));
		}
	}

	private void depositAmount(int amount, int account) {
		dao.depositAmount(amount, account, "Deposit through bank");
	}
}
