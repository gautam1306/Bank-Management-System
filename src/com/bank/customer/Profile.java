package com.bank.customer;

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
	public String getName() {
		return name;
	}
	public String getDateofBirth() {
		return dateofBirth;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public String getAadhaarNumber() {
		return aadhaarNumber;
	}
	public String getPanCardCode() {
		return panCardCode;
	}
	public String getGender() {
		return gender;
	}
	public Address getAddress() {
		return address;	
	}
	 	
}
