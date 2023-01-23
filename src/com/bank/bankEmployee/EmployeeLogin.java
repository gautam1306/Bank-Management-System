package com.bank.bankEmployee;

public class EmployeeLogin {
	public EmployeeCapsule validate(int employeeID, String password) {
		int role = new EmployeeValidation().validate(employeeID, password);
		
		switch (role) {
		case 1: {
			Manager manager = new Manager(employeeID, password);
			return new EmployeeCapsule(role,manager);
		}
		case 3: {
			Accountant accountant = new Accountant(employeeID, password);
			return new EmployeeCapsule(role, accountant);
		}
		case 2: {
			BranchAdmin branchAdmin = new BranchAdmin(employeeID, password);
//            branchAdmin.function();
			return new EmployeeCapsule(role, branchAdmin);
		}
		default: {
			System.out.println("The credentials are incorrect");
			return null;
		}
		}
	}
}
