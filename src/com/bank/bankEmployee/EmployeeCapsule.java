package com.bank.bankEmployee;

public class EmployeeCapsule {
	private int employeeRole;
	private Employee employee;
	public int getEmployeeRole() {
		return employeeRole;
	}
	public Employee getEmployee() {
		return employee;
	}
	public EmployeeCapsule(int employeeRole, Employee employee) {
		super();
		this.employeeRole = employeeRole;
		this.employee = employee;
	}
	
}
