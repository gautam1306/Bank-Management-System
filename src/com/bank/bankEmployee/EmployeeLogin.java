package com.bank.bankEmployee;

import java.util.Scanner;

public class EmployeeLogin {
    public void validate(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Employee ID");
        int employeeID = sc.nextInt();
        System.out.println("Enter the Password");
        String password = sc.next();
        int role = new EmployeeValidation().validate(employeeID,password);
        if(role==1){
            Manager manager = new Manager(employeeID,password);
            manager.function();
        }
        if(role==3){
            Accountant accountant = new Accountant(employeeID,password);
            accountant.function();
        }
        if(role==2){
            BranchAdmin branchAdmin = new BranchAdmin(employeeID,password);
            branchAdmin.function();
        }
        if(role==-1) {
        	System.out.println("The credentials are incorrect") ;
        }
    }
}
