package com.bank.customer;

import com.db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.naming.spi.DirStateFactory.Result;
public class CustomerDao {
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("sql");
	public ArrayList<String> verify(int customerId, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.validateCustomer"));
			preparedStatement.setInt(1, customerId);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				ArrayList<String> property = new ArrayList<>();
				property.add(resultSet.getString("name"));
				property.add(resultSet.getString("date_of_birth"));
				property.add(resultSet.getString("mobile_number"));
				property.add(resultSet.getString("aadhaar_number"));
				property.add(resultSet.getString("pan_card"));
				property.add(resultSet.getString("gender"));
				property.add(resultSet.getString("address_house"));
				property.add(resultSet.getString("address_street"));
				property.add(resultSet.getString("address_district"));
				property.add(resultSet.getString("address_state"));
				property.add(Integer.toString(resultSet.getInt("address_pin_code")));
				return property;
		}else {
			return null;
		}}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("There was an error");
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
	public TreeMap<Integer, Accounts> getAccounts(int customerId) {
		Connection connection =null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		TreeMap<Integer, Accounts> accounts = new TreeMap<>();
		try {
			connection= Database.getConnection();
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.getAccounts"));
			preparedStatement.setInt(1, customerId);
			resultSet = preparedStatement.executeQuery();
//			System.out.println("Hi inside getAccounts");
			
			while(resultSet.next()) {
				int account_number = resultSet.getInt("account_number");
				Accounts account = new Accounts(resultSet.getInt("account_number"),resultSet.getInt("balance"),resultSet.getInt("today_transfer"),resultSet.getString("account_type"),resultSet.getString("branch_ifsc"),resultSet.getInt("transfer_limit"),resultSet.getInt("overdraft"));
				accounts.put(account_number, account);
//				System.out.println(account);
			}
			if(!accounts.isEmpty()) {
				return accounts;
			}
			else {
				return null;
			}
		}
		catch (SQLException e) {
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
	public TreeMap<Integer, RecurringDeposit> getRecurringDeposit(int customerId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.getRecurringDeposit"));
			preparedStatement.setInt(1,customerId);
			resultSet =preparedStatement.executeQuery();
			TreeMap< Integer,RecurringDeposit> recurringDeposits = new TreeMap<>();
			while(resultSet.next()) {
				int depositId = resultSet.getInt("deposit_id");
				int amount =resultSet.getInt("monthly_investment_amount");
				int accountnumber = resultSet.getInt("account_number");
				float intrestrate = resultSet.getFloat("interest_rate");
				String maturityperiod = resultSet.getString("maturity_period");
				int depositedamount = resultSet.getInt("deposit_amount");
				float intrestamount = resultSet.getFloat("total_interest_amount");
				String startDate = resultSet.getString("start_date");
				RecurringDeposit recurringDeposit = new RecurringDeposit(depositId,amount,depositedamount,maturityperiod,startDate ,accountnumber,intrestrate,intrestamount,customerId);
				recurringDeposits.put(depositId,recurringDeposit);
			}
			if(!recurringDeposits.isEmpty()) {
				return recurringDeposits;
			}
			else {
				return null;
			}}
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
		return null;
	}
	public TreeMap<Integer, FixedDeposits> getFixedDeposits(int customerId) {
		Connection connection =null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		TreeMap<Integer, FixedDeposits> fixedDeposits = new TreeMap<>();
		try {
			connection= Database.getConnection();
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.getFixedDeposit"));
			preparedStatement.setInt(1, customerId);
			resultSet = preparedStatement.executeQuery();
//			System.out.println("Hi inside getAccounts");
			
			while(resultSet.next()) {
				int depositid = resultSet.getInt("depositid");
				FixedDeposits fixeddeposit = new FixedDeposits(customerId,resultSet.getInt("depositid"),resultSet.getInt("accountnumber"),resultSet.getInt("amount"),resultSet.getString("maturityDate"),resultSet.getString("startDate"),resultSet.getFloat("intrestrate"));
				fixedDeposits.put(depositid,fixeddeposit);
				System.out.println(depositid);
			}
			if(!fixedDeposits.isEmpty()) {
				return fixedDeposits;
			}
			else {
				return null;
			}
		}
		catch (SQLException e) {
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
	public void addRecurringDeposit(int customerID,int accountnumber, int period, int amount) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.addRecurringDeposit"));
			preparedStatement.setInt(1, accountnumber);
			preparedStatement.setInt(2, period);
			preparedStatement.setInt(3, period);
			preparedStatement.setInt(4,period);
			preparedStatement.setInt(5, amount);
			preparedStatement.executeUpdate();
			System.out.println("Successfully Created a recurring deposit");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public void addFixedDeposit(int customerID,int accountnumber, int period, int amount) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.transferamount"));
			preparedStatement.setInt(1, -amount);
			preparedStatement.setInt(2, accountnumber);
			preparedStatement.setInt(3, amount);
			int x = preparedStatement.executeUpdate();
			if(x>=1) {
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.getBalance"));
			preparedStatement.setInt(1, accountnumber);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int accountbalance= resultSet.getInt("balance");
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.getBalance"));
			preparedStatement.setInt(1, accountnumber);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int bankbalance= resultSet.getInt("balance");
			if(accountbalance>amount) {
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.addFixedDeposit"));
			preparedStatement.setInt(1, accountnumber);
			preparedStatement.setInt(2, period);
			preparedStatement.setInt(3, period);
			preparedStatement.setInt(4,period);
			preparedStatement.setInt(5, amount);
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.transferamount"));
			preparedStatement.setInt(1, amount);
			preparedStatement.setInt(2, 1);
			preparedStatement.setInt(3, -amount);
			preparedStatement.executeUpdate();
			preparedStatement =connection.prepareStatement(resourceBundle.getString("db.insertTransfer"));
			preparedStatement.setInt(1, accountnumber);
	        preparedStatement.setInt(2, 1);
	        preparedStatement.setInt(3,amount);
	        preparedStatement.setString(5,"imps");
	        preparedStatement.setString(4,"For fixed deposit");
	        preparedStatement.executeUpdate();
	        System.out.println("The deposit is successfully made");
			}}
			else {
				System.out.println("Sorry You do not have enough balance to open a fixed deposit with this fund");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public void changePassword(int customerID, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		try {
			connection = Database.getConnection();
			preparedStatement  = connection.prepareStatement(resourceBundle.getString("db.updatePassword"));
			preparedStatement.setString(1, password);
			preparedStatement.setInt(2, customerID);
			preparedStatement.executeUpdate();
			System.out.println("The password is successfully changed");
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
	public void loanHistory(int customerID) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.getLoanHistory"));
			preparedStatement.setInt(1, customerID);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				System.out.println(resultSet.getInt("loan_id")+"   "+resultSet.getInt("loan_amount")+"  "+resultSet.getString("date_of_approval")+"  "+resultSet.getString("maturity_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
