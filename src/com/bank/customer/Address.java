package com.bank.customer;

import java.util.Formatter;

public class Address {
    public String house;
    public String street;
    public String district;
    public int pin_code;
    public String state;

    public Address(String address_house, String address_street, String address_district, String address_state, int address_pin_code) {
        this.house = address_house;
        this.street = address_street;
        this.district = address_district;
        this.state = address_state;
        this.pin_code = address_pin_code;
    }

    @Override
    public String toString() {
        Formatter formatter =  new Formatter();
        formatter.format("%s,\n%s,\n%s,%s\nPin Code- %d ,",this.house,this.street,this.district,this.state,this.pin_code);
        return formatter.toString();
    }
}
