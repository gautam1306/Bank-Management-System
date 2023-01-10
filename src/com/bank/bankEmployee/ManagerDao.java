package com.bank.bankEmployee;

import com.bank.Branches;
import com.db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;


public class ManagerDao {

	ResourceBundle resourceBundle = ResourceBundle.getBundle("sql");
	
	protected ArrayList<String> verify(int managerid){
		ArrayList<String> arr = new ArrayList<>();
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.getManagerDetails"));
			preparedStatement.setInt(1, managerid);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
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
			}
			else {
				resultSet.close();
				preparedStatement.close();
				connection.close();
				return null;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}finally {
			try{
				resultSet.close();
				preparedStatement.close();
				connection.close();
			}
			catch (SQLException e) {
				// TODO: handle exception
			}
		}
	} 
	protected boolean addBranch(String bankarea,String bankifsc,String address) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		try {
			connection =Database.getConnection();
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.addBranch"));
			preparedStatement.setString(1, bankifsc);
			preparedStatement.setString(2, bankarea);
			preparedStatement.setString(3, address);
			int i = preparedStatement.executeUpdate();
			if(i==0) {
				return false;
			}
			else {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			if(e.getErrorCode()==0) {
				System.out.println("This bank ifsc already exists");
			}
			else {
				e.printStackTrace();
			}
			return false;
		}
		finally {
			try{
				preparedStatement.close();
				connection.close();
			}
			catch (SQLException e) {
				// TODO: handle exception
			}
			catch(NullPointerException e){
				
			}
		}
	}
	protected void addEmployee(String name, String password, String ifsc,String date_of_birth, String aadhaar_number, String pan_card, int gender, String mobile_number, String address_house, String address_street, String address_district, String address_state, int address_pin_code) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.addEmployee"));
			preparedStatement.setString(1,name);
			preparedStatement.setString(2,aadhaar_number);
			preparedStatement.setString(3,pan_card);
			preparedStatement.setString(4,date_of_birth);
			preparedStatement.setInt(5,gender);
			preparedStatement.setString(6,mobile_number);
			preparedStatement.setString(7,password);
			preparedStatement.setInt(8,2);
			preparedStatement.setString(9,address_house);
			preparedStatement.setString(10,address_street);
			preparedStatement.setString(11,address_district);
			preparedStatement.setString(12,address_state);
			preparedStatement.setInt(13,address_pin_code);
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.addEmployeeBranchRelation"));
			preparedStatement.setString(1,aadhaar_number);
			preparedStatement.setString(2,ifsc);
			preparedStatement.executeUpdate();
			System.out.println("Successfully created the branch manager");

		} catch (SQLException e) {
			if(e.getErrorCode()==0) {
				System.out.println("Already there is an employee for this bank");
			}
			e.printStackTrace();
		}finally {
			try{
				preparedStatement.close();
				connection.close();
			}
			catch (SQLException e) {
				// TODO: handle exception
			}
		}
		
	}
	protected TreeMap<String,Branches> getBranches() {
		TreeMap<String,Branches> map = new TreeMap<>();
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		try {
			connection =Database.getConnection();
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.getBranches"));
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Branches branch = new Branches(resultSet.getString("branch_ifsc"),resultSet.getString("branch_name"),resultSet.getString("address_building"),resultSet.getString("address_street"),resultSet.getString("address_district"),resultSet.getString("address_state"),resultSet.getInt("address_pin_code"));
				map.put(resultSet.getString("branch_ifsc"),branch);
			}
			return map;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		finally {
			try{
				resultSet.close();
				preparedStatement.close();
				connection.close();
			}
			catch(SQLException e){
				System.out.println("There is a problem with database");
			}
			catch(NullPointerException e) {
				
			}
		}
	}

	protected void addBranch(String ifsc, String branch_name, String address_building, String address_street, String address_district, String address_state, int address_pin_code) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.addBranch"));
			preparedStatement.setString(1,ifsc);
			preparedStatement.setString(2,branch_name);
			preparedStatement.setString(3,address_building);
			preparedStatement.setString(4,address_street);
			preparedStatement.setString(5,address_district);
			preparedStatement.setString(6,address_state);
			preparedStatement.setInt(7,address_pin_code);
			preparedStatement.executeUpdate();
			System.out.println("The Branch was successfully registered");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
