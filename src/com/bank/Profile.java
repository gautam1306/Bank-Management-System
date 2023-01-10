package com.bank;

import java.util.ArrayList;
import java.util.Formatter;

public class Profile {
    private String name;
    private String dateofBirth;
    private String mobileNumber;
    private String aadhaarNumber;
    private String panCardCode;
    private String gender;
	private Address address;
	public String getAadhaar(){
    	return aadhaarNumber;
    }
	public Profile(String name, String dateofBirth, String mobileNumber, String aadhaarNumber,
			String panCardCode, String gender,String address_house,String address_street,String address_district,String address_state,int address_pin_code) {
		super();
		this.name = name;
		this.dateofBirth = dateofBirth;
		this.mobileNumber = mobileNumber;
		this.aadhaarNumber = aadhaarNumber;
		this.panCardCode = panCardCode;
		this.gender = gender;
		this.address = new Address(address_house,address_street,address_district,address_state,address_pin_code);
	}
	@Override
	public String toString() {
		Formatter formatter = new Formatter();
		formatter.format("Name            : %s\n" ,name);
		formatter.format("Address         : %s\n" ,address);
		formatter.format("Mobile Number   : %s\n" ,mobileNumber);
		formatter.format("Adhaar Number   : %s\n",aadhaarNumber);
		formatter.format("PAN Card Number : %s\n",panCardCode);
		formatter.format("Gender          : %s\n",gender);
		formatter.format("Date of Birth   : %s\n",dateofBirth);
		return formatter.toString();
	}
    
}
