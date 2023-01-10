package com.bank.bankEmployee;

import com.db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class BranchAdminDao {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("sql");
    public TreeMap<Integer,LoanType> getLoanTypes(){
        Connection connection =null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        TreeMap<Integer,LoanType> loanType = new TreeMap<>();
        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(resourceBundle.getString("db.getLoanTypes"));
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                loanType.put(resultSet.getInt("loan_type_id"),new LoanType(resultSet.getInt("loan_type_id"),resultSet.getFloat("interest_rate"),resultSet.getString("loan_type")));
            }
        } catch (SQLException e) {
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
        return loanType;
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
            preparedStatement.setInt(8,3);
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
            System.out.println("Successfully created the Accountant");

        } catch (SQLException e) {
            e.printStackTrace();
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
    public ArrayList<String> getEmployee(int employeeID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet =null;
        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(resourceBundle.getString("db.getEmployeeDetails"));
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
	public int addLoanAccount(int accountNumber, int loanID, int timePeriod, int loanAmount, float interestRate) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.addLoanAccount"));
			preparedStatement.setInt(1,accountNumber);
			preparedStatement.setInt(2, loanAmount);
			preparedStatement.setFloat(3,interestRate);
			preparedStatement.setInt(4,timePeriod);
			preparedStatement.setInt(5, loanAmount);
			preparedStatement.setInt(6, loanID);
			preparedStatement.executeUpdate();
			new AccountantDao().depositAmount(loanAmount, accountNumber,"The transfer amount of the loan");
			return 1;
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
}
