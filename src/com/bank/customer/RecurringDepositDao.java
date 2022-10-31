package com.bank.customer;

import com.db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RecurringDepositDao {
	ResourceBundle rb = ResourceBundle.getBundle("sql");
	public void prematureWithdrawl(int accountnumber, int total_amount,int depositid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = Database.getConnection();
			preparedStatement= connection.prepareStatement(rb.getString("db.transferAmount"));
			
			preparedStatement.setInt(1, total_amount);
			preparedStatement.setInt(2, accountnumber);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = connection.prepareStatement(rb.getString("db.transferAmount"));
			preparedStatement.setInt(1, -total_amount);
			preparedStatement.setInt(2, 1);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = connection.prepareStatement(rb.getString("db.deleteRecurringDeposit"));
			preparedStatement.setInt(1, depositid);
			preparedStatement.executeUpdate();
			preparedStatement= connection.prepareStatement(rb.getString("db.addTransaction"));
			int frombalance = getBalance(1);
			int tobalance = getBalance(accountnumber);
			preparedStatement.setInt(1,1);
			preparedStatement.setInt(2, accountnumber);
			preparedStatement.setInt(3,frombalance);
			preparedStatement.setInt(4, tobalance);
			preparedStatement.setInt(5,total_amount);
			preparedStatement.setString(6, "Premature Withdrawl From Bank");
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(NullPointerException e) {
			}
		}
		
	}
	private int getBalance(int accountnumber){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(rb.getString("db.getBalance"));
			preparedStatement.setInt(1, accountnumber);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int balance = resultSet.getInt("balance");
			return balance;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	public void changeAmount(int depositid, int amount) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(rb.getString("db.changeRecurringDeposit"));
			preparedStatement.setInt(1, amount);
			preparedStatement.setInt(2,depositid);
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("There was an error");
		}
		finally
		{
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
