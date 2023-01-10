package com.bank.bankEmployee;

public class CardCompany {
	public CardCompany(int companyid, String company) {
		this.companyid = companyid;
		this.company = company;
	}
	int companyid;
	String company;
	@Override
	public String toString() {
		String com = companyid+"  "+company;
		return com;
	}
}
