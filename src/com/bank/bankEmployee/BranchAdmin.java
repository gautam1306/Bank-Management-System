package com.bank.bankEmployee;

import com.bank.Main;
import com.bank.Profile;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class BranchAdmin implements Employee{
    TreeMap<Integer,LoanType> loanType;
    BranchAdminDao dao = new BranchAdminDao();
    private int employeeID;
    private String password;
    private String ifsc;
    private Profile profile;

    public BranchAdmin(int employeeID, String password) {
        this.employeeID = employeeID;
        this.password = password;
        initialize();
    }
    public void function(){
        while (true){
            System.out.println("Press 1 if you want to add an employee in your branch.");
            System.out.println("Press 2 if you want to provide loan to an customer of our branch.");
            System.out.println("Press 3 if you want to logout");
            Scanner sc =  new Scanner(System.in);
            int c = Main.getint();
            switch (c) {
                case 1 : {
                    System.out.println("Enter the name of the new employee");
                    String name = sc.nextLine();
                    System.out.println("Enter the password for the employee");
                    String password = sc.nextLine();
                    System.out.println("Enter the date of birth (YYYY-MM-DD) in this format");
                    String date_of_birth = sc.next();
                    System.out.println("Enter the aadhaar number of the employee");
                    String aadhaar_number = sc.next();
                    System.out.println("Enter the Pan card code of the employee");
                    String pan_card = sc.next();
                    System.out.println("Enter 1 if the employee is a female \nEnter 2 if the employee is a male");
                    int gender = sc.nextInt();sc.nextLine();
                    System.out.println("Enter the Mobile Number of the employee that is going to be added(without country code)");
                    String mobile_number = sc.nextLine();
                    System.out.println("Enter the house name/ house number of the employee");
                    String address_house = sc.nextLine();
                    System.out.println("Enter the street in which the employee's house is present");
                    String address_street = sc.nextLine();
                    System.out.println("Enter the district in which the employee's house is present");
                    String address_district = sc.nextLine();
                    System.out.println("Enter the state in which the employee's house is present");
                    String address_state = sc.nextLine();
                    System.out.println("Enter the pin code of the area where employee's house is present");
                    int address_pin_code = sc.nextInt();
                    addEmployee(name,password,date_of_birth,aadhaar_number,pan_card,gender,mobile_number,address_house,address_street,address_district,address_state,address_pin_code);
                    break;
                }
                case 2 :
                    System.out.println("Enter the account number of the customer by which he would be paying his loan back");
                    int accountNumber = Main.getint();
                    System.out.println("Enter the type of loan id that is going to be taken by the Customer");
                    listLoan();
                    int loanID = Main.getint();
                    if(!verifyLoanID(loanID)) {
                        System.out.println("Please try again with a correct loan id");
                        return;
                    }
                    System.out.println("Enter the time taken for repayment in months");
                    int timePeriod = Main.getint();
                    System.out.println("Enter the loan amount that is required for the customer");
                    int loanAmount = Main.getint();
                    addLoanAccount(accountNumber,loanID,timePeriod,loanAmount);
                case 3:
                	return;
            }
        }
    }
    public void initialize(){
        ArrayList<String> arr =  dao.getEmployee(employeeID);
        ifsc =  arr.get(0);
        profile = new Profile( arr.get(1),arr.get(2),arr.get(3), arr.get(4), arr.get(5), arr.get(6),arr.get(7),arr.get(8), arr.get(9),arr.get(10),Integer.parseInt(arr.get(11)));
        System.out.println("Welcome to HDFC Bank");
    }
    public void addEmployee(String name, String password,String date_of_birth, String aadhaar_number, String pan_card, int gender, String mobile_number, String address_house, String address_street, String address_district, String address_state, int address_pin_code) {
        dao.addEmployee(name,password,this.ifsc,date_of_birth,aadhaar_number,pan_card,gender,mobile_number,address_house,address_street,address_district,address_state,address_pin_code);
    }

    public void listLoan() {
        if(loanType == null){
            loanType = dao.getLoanTypes();
        }
        for(int i : loanType.keySet()){
            System.out.println(i+"    "+loanType.get(i));
        }
    }

    public boolean verifyLoanID(int loanID) {
        if(loanType.containsKey(loanID)){
            return true;
        }
        return false;
    }

    @Override
    public void viewEmployee() {
        System.out.println("Employee ID : "+employeeID);
        System.out.println("Employee Role : Branch Admin");
        System.out.println(profile);
    }
	public void addLoanAccount(int accountNumber, int loanID, int timePeriod, int loanAmount) {
		dao.addLoanAccount(accountNumber,loanID,timePeriod,loanAmount,loanType.get(loanID).interestRate);
	}
}
