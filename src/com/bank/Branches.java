package com.bank;

import java.util.Formatter;

public class Branches
{
    public String ifsc;
    public String location;

    public Address address;

    public Branches(String ifsc, String location, String address_house, String address_street, String address_district, String address_state, int address_pin_code) {
        this.ifsc = ifsc;
        this.location = location;
        this.address = new Address(address_house,address_street,address_district,address_state,address_pin_code);
    }


    @Override
    public String toString() {
    	Formatter format = new Formatter();
    	format.format("|%40s|%14s|",location,ifsc);
    	return format.toString();
    }
}
