package com.bank.bankEmployee;

import com.bank.Branches;
import com.bank.Profile;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Manager implements Employee{
	private int employeeId;
	private String password;
	private Profile profile;
	private TreeMap<String, Branches> branches;
	private ManagerDao dao = new ManagerDao();
	private int employeeRole;
	public Manager(int managerid,String password) {
		this.employeeId = managerid;
		this.password = password;
		intitialize();
	}
//	public void function() {
//        while (true){
//            System.out.println("Press 1 to add a new Bank Branch to the bank");
//            System.out.println("Press 2 to add a new employee to a bank branch");
//            System.out.println("Press 3 to print all the branches of this bank");
//            System.out.println("Press 4 to filter branches by district");
//            System.out.println("Press 5 to logout and go to main menu");
//            int s = Main.getint();
//            Scanner sc = new Scanner(System.in);
//            switch (s) {
//                case 1: {
//                    System.out.println("Enter the Bank ifsc code");
//                    String branch_ifsc = sc.nextLine();
//                    System.out.println("Enter the location of the new bank");
//                    String branch_area = sc.nextLine();
//                    System.out.println("Enter the Building name of the branch");
//                    String address_building = sc.nextLine();
//                    System.out.println("Enter the street in which the branch is present");
//                    String address_street  = sc.nextLine();
//                    System.out.println("Enter the district in which the branch is present");
//                    String address_district = sc.nextLine();
//                    System.out.println("Enter the State in which the bank is present");
//                    String address_state = sc.nextLine();
//                    System.out.println("Enter the Pin code of the bank");
//                    int address_pin_code = sc.nextInt();
//                    addBranch(branch_ifsc,branch_area,address_building, address_street,address_district,address_state,address_pin_code);
//
//                    break;
//                }
//                case 2:{
//                    System.out.println("Enter the name of the new employee");
//                    String name = sc.nextLine();
//                    System.out.println("Enter the password for the employee");
//                    String password = sc.nextLine();
//                    System.out.println("Enter the bank that is going to be associated with this Bank Manager");
//                    String ifsc = sc.next();
//                    System.out.println("Enter the date of birth (YYYY-MM-DD) in this format");
//                    String date_of_birth = sc.next();
//                    System.out.println("Enter the aadhaar number of the employee");
//                    String aadhaar_number = sc.next();
//                    System.out.println("Enter the Pan card code of the employee");
//                    String pan_card = sc.next();
//                    System.out.println("Enter 1 if the employee is a female \nEnter 2 if the employee is a male");
//                    int gender = sc.nextInt();sc.nextLine();
//                    System.out.println("Enter the Mobile Number of the employee that is going to be added(without country code)");
//                    String mobile_number = sc.nextLine();
//                    System.out.println("Enter the house name/ house number of the employee");
//                    String address_house = sc.nextLine();
//                    System.out.println("Enter the street in which the employee's house is present");
//                    String address_street = sc.nextLine();
//                    System.out.println("Enter the district in which the employee's house is present");
//                    String address_district = sc.nextLine();
//                    System.out.println("Enter the state in which the employee's house is present");
//                    String address_state = sc.nextLine();
//                    System.out.println("Enter the pin code of the area where employee's house is present");
//                    int address_pin_code = sc.nextInt();
//                    addBranchManager(name,password,ifsc,date_of_birth,aadhaar_number,pan_card,gender,mobile_number,address_house,address_street,address_district,address_state,address_pin_code);
//
//                    break;
//                }
//                case 3:
//                    viewBranches();
//                    break;
//                case 4:
//                    System.out.println("Enter the district name which you want to use to filter");
//                    String district = sc.nextLine();
//                    filterBranch(district);
//                    break;
//                case 5:
//                    System.out.println("Logged out successfully");
//                    return;
//                default:
//                    System.out.println("Please enter a valid option");
//            }
//        }
//    }
	private void intitialize(){
		ArrayList<String> property = dao.verify(employeeId);
		employeeRole=1;
		profile = new Profile(property.get(0),property.get(1),property.get(2), property.get(3), property.get(4), property.get(5), property.get(6), property.get(7), property.get(8), property.get(9),Integer.parseInt(property.get(10)));
	}
	public int getEmployeeRole() {
		return employeeRole;
	}
	void viewBranches(){
		if(branches==null){
		branches = dao.getBranches();}
		if(branches.isEmpty()) {
			System.out.println("There is no branch present for this bank");
			return;
		}
		for(String bank_ifsc : branches.keySet()) {
			System.out.println(branches.get(bank_ifsc));
		}
	}
	@Override
	public void viewEmployee() {
		System.out.println("Employee ID : "+employeeId);
		System.out.println("Employee Role : Bank Manager");
		System.out.println(profile);
	}

	public void addBranchManager(String name, String password, String ifsc,String date_of_birth, String aadhaar_number, String pan_card, int gender, String mobile_number, String address_house, String address_street, String address_district, String address_state, int address_pin_code) {
		if(branches==null){
			branches = dao.getBranches();
		}
		if(branches.containsKey(ifsc)){
			dao.addEmployee(name,password,ifsc,date_of_birth,aadhaar_number,pan_card,gender,mobile_number,address_house,address_street,address_district,address_state,address_pin_code);
		}
		else{
			System.out.println("Sorry the branch doesn't exist");
		}
	}
	public void addBranch(String ifsc,String branch_name, String address_building, String address_street, String address_district, String address_state, int address_pin_code){
		if(branches == null){
			branches = dao.getBranches();
		}
		if(branches.containsKey(ifsc)){
			System.out.println("This IFSC already exists");
		}
		else{
			branches.put(ifsc,new Branches(ifsc,branch_name,address_building,address_street,address_district,address_state,address_pin_code));
			dao.addBranch(ifsc,branch_name,address_building,address_street,address_district,address_state,address_pin_code);
		}
	}

	public int getEmployeeId() {
		return employeeId;
	}
	public String getPassword() {
		return password;
	}
	public Profile getProfile() {
		return profile;
	}
	public TreeMap<String, Branches> getBranches() {
		return branches;
	}
	public ManagerDao getDao() {
		return dao;
	}
	public void filterBranch(String district) {
		if (branches== null){
			branches=dao.getBranches();
		}
		for(String ifsc : branches.keySet()){
			if(branches.get(ifsc).address.district.equals(district)){
				System.out.println(branches.get(ifsc));
			}
		}
	}
}
