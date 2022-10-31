package com.bank.customer;

import java.util.ArrayList;

public class Card {
	int cardid;
	long cardnumber;
	String expirydate;
	int transactionlimit;
	String cardcompany;
	String status;
	
	public Card(ArrayList<Object> detail){
		this.cardid = (int) detail.get(0); 
		this.status = (String) detail.get(1);
		this.transactionlimit = (int) detail.get(2);
		this.cardcompany = (String) detail.get(3);
		this.cardnumber = (long) detail.get(4);
		this.expirydate = (String) detail.get(5);		
	}
	@Override
	public String toString() {
		String card = "Card Number : "+cardnumber+"\nCard Company : "+cardcompany+"\nTransaction Limit : "+transactionlimit+"\nExpiry Date : "+expirydate;
		return card;
	}
}
