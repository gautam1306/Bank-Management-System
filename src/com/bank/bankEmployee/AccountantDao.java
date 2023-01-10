package com.bank.bankEmployee;

import com.db.Database;
import com.bank.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.TreeMap;

public class AccountantDao {
	private ResourceBundle rb = ResourceBundle.getBundle("sql");
	public ArrayList<String> getEmployee(int employeeID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet =null;
        try {
        	connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(rb.getString("db.getEmployeeDetails"));
            preparedStatement.setInt(1,employeeID);
            resultSet = preparedStatement.executeQuery();
			resultSet.next();
			ArrayList<String> arr = new ArrayList<>();
//                System.out.println(resultSet.getInt(1));
			arr.add(resultSet.getString("branch_ifsc"));
			arr.add(resultSet.getString("name"));
			arr.add(resultSet.getString("date_of_birth"));
			arr.add(resultSet.getString("mobile_number"));
			arr.add(resultSet.getString("aadhaar_number"));
			arr.add(resultSet.getString("pan_card"));
			arr.add(resultSet.getString("gender"));
			arr.add(resultSet.getString("address_house"));
			arr.add(resultSet.getString("address_street"));
			arr.add(resultSet.getString("address_district"));
			arr.add(resultSet.getString("address_state"));
			arr.add(Integer.toString(resultSet.getInt("address_pin_code")));
			return arr;
        } catch (SQLException e) {
        	e.printStackTrace();
//        	System.out.println(e);
            return null;
        }finally {
        	try {
        		if(resultSet!=null) {
    			resultSet.close();}
        		if(preparedStatement!=null) {
    			preparedStatement.close();}
        		if(connection!=null) {
    			connection.close();}
    		} catch (SQLException  e) {
    			// TODO Auto-generated catch block
    			System.out.println(e);
    		}
		}
    }
	public int createCustomer(String name, String aadhaar_number, String date_of_birth, String mobile_number, String pan_card,int gender,String address_house,String address_street,String address_district,String address_state,int address_pin_code) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(rb.getString("db.addProfile"));
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, aadhaar_number);
			preparedStatement.setString(3, pan_card);
			preparedStatement.setString(4,date_of_birth);
			preparedStatement.setInt(5, gender);
			preparedStatement.setString(6, mobile_number);
			preparedStatement.setString(7,address_house);
			preparedStatement.setString(8,address_street);
			preparedStatement.setString(9,address_district);
			preparedStatement.setString(10,address_state);
			preparedStatement.setInt(11,address_pin_code);
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement(rb.getString("db.getProfileId"));
			preparedStatement.setString(1, aadhaar_number);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int  profileid = resultSet.getInt(1);
			preparedStatement = connection.prepareStatement(rb.getString("db.addcustomer"));
			preparedStatement.setInt(2,profileid);
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the password for the customer portal");
			String password = sc.next();
			preparedStatement.setString(1, password);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = connection.prepareStatement(rb.getString("db.getCustomerid"));
			preparedStatement.setInt(1, profileid);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int customerid = resultSet.getInt(1);
			return customerid;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getErrorCode());
			return -1;
		}
		finally {
        	try {
        		if(resultSet!=null) {
    			resultSet.close();}
        		if(preparedStatement!=null) {
    			preparedStatement.close();}
        		if(connection!=null) {
    			connection.close();}
    		} catch (SQLException  e) {
    			// TODO Auto-generated catch block
    			System.out.println(e);
    		}
		}
		
	}
	public void addAccount(int id, String ifsc, int m,int overdraftLimit,int transferLimit) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(rb.getString("db.addAccount"));
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2,ifsc);
			preparedStatement.setInt(3, m);
			preparedStatement.setInt(4, transferLimit);
			preparedStatement.setInt(5,overdraftLimit);
			preparedStatement.setInt(6, id);
			int i =preparedStatement.executeUpdate();
			if(i==1) {
				System.out.println("The account has been successfully created");
			}
			else{
				System.out.println("There is an account in this branch with same specifications");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if(e.getErrorCode()==0) {
				System.out.println("This account already exist or else there is no such customer id");
			}
//			e.printStackTrace();
		}
		finally {
        	try {
        		
        		if(preparedStatement!=null) {
    			preparedStatement.close();}
        		if(connection!=null) {
    			connection.close();}
    		} catch (SQLException  e) {
    			// TODO Auto-generated catch block
    			System.out.println(e);
    		}
		}
		
	}
	public void depositAmount(int amount, int acc,String discription) {
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		try {
			connection = Database.getConnection();
			preparedStatement= connection.prepareStatement(rb.getString("db.transferAmount"));
			preparedStatement.setInt(1, amount);
			preparedStatement.setInt(2, acc);
			preparedStatement.executeUpdate();preparedStatement.close();
			preparedStatement= connection.prepareStatement(rb.getString("db.addTransaction"));
			int frombalance = getBalance(1);
			int tobalance = getBalance(acc);
			preparedStatement.setInt(1,1);
			preparedStatement.setInt(2, acc);
			preparedStatement.setInt(3,frombalance);
			preparedStatement.setInt(4, tobalance);
			preparedStatement.setInt(5,amount);
			preparedStatement.setString(6, discription);
			preparedStatement.executeUpdate();
		}
		catch(SQLException e){
			System.out.println("There was an error");
		}
		finally {
        	try {
        		if(preparedStatement!=null) {
    			preparedStatement.close();}
        		if(connection!=null) {
    			connection.close();}
    		} catch (SQLException  e) {
    			// TODO Auto-generated catch block
    			System.out.println(e);
    		}
		}
	}
	private int getBalance(int account_number){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(rb.getString("db.getBalance"));
			preparedStatement.setInt(1, account_number);
			rs = preparedStatement.executeQuery();
			rs.next();
			int balance = rs.getInt("balance");
			return balance;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		finally {
        	try {
        		if(preparedStatement!=null) {
    			preparedStatement.close();}
        		if(connection!=null) {
    			connection.close();}
    		} catch (SQLException  e) {
    			// TODO Auto-generated catch block
    			System.out.println(e);
    		}
		}
		
		
	}
	public void deleteAccount(int accountnumber) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try
		{
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(rb.getString("db.deleteAccount"));
			preparedStatement.setInt(1, accountnumber);
			preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e.getErrorCode());
			e.printStackTrace();
		}
		finally {

        	try {
        		if(preparedStatement!=null) {
    			preparedStatement.close();}
        		if(connection!=null) {
    			connection.close();}
    		} catch (SQLException  e) {
    			// TODO Auto-generated catch block
    			System.out.println(e);
    		}
		
		}
	}

	public TreeMap<Integer, CardCompany> getCardCompany(){
		Connection connection = null;
		PreparedStatement preparedStatement =null;
		ResultSet  resultSet = null;
		try {
			connection = Database.getConnection();
			preparedStatement= connection.prepareStatement(rb.getString("db.getCardCompany"));
			resultSet = preparedStatement.executeQuery();
			TreeMap<Integer, CardCompany> company = new TreeMap<>();
			while(resultSet.next()) {
				CardCompany card = new CardCompany(resultSet.getInt(1),resultSet.getString(2));
				company.put(card.companyid,card);
			}
			return company;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
        	try {
        		if(resultSet!=null) {
    			resultSet.close();}
        		if(preparedStatement!=null) {
    			preparedStatement.close();}
        		if(connection!=null) {
    			connection.close();}
    		} catch (SQLException  e) {
    			// TODO Auto-generated catch block
    			System.out.println(e);
    		}
		}
	}
	public void addDebitCard(int accountnumber, int transferlimit, int companyid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(rb.getString("db.insertcard"));
			preparedStatement.setInt(2, companyid);
			preparedStatement.setInt(1, transferlimit);
			preparedStatement.setInt(3,accountnumber);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = connection.prepareStatement(rb.getString("db.getCardid"));
			preparedStatement.setInt(1, accountnumber);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int cardid = resultSet.getInt(1);
			preparedStatement= connection.prepareStatement(rb.getString("db.setCardIdToAccounts"));
			preparedStatement.setInt(1, cardid);
			preparedStatement.setInt(2, accountnumber);
			preparedStatement.executeUpdate();
			System.out.println("Card Successfully generated");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
        	try {
        		if(resultSet!=null) {
    			resultSet.close();}
        		if(preparedStatement!=null) {
    			preparedStatement.close();}
        		if(connection!=null) {
    			connection.close();}
    		} catch (SQLException  e) {
    			// TODO Auto-generated catch block
    			System.out.println(e);
    		}
		}
		
	}
}
