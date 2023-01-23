package com.bank.customer;

import com.db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RecurringDepositDao {
	ResourceBundle rb = ResourceBundle.getBundle("sql");

	public int prematureWithdrawal(int accountnumber, int total_amount, int depositid) {
		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement = connection.prepareStatement(rb.getString("db.transferAmount"))) {
				preparedStatement.setInt(1, total_amount);
				preparedStatement.setInt(2, accountnumber);
				preparedStatement.executeUpdate();
			}
			try (PreparedStatement preparedStatement = connection.prepareStatement(rb.getString("db.transferAmount"))) {
				preparedStatement.setInt(1, -total_amount);
				preparedStatement.setInt(2, 1);
				preparedStatement.executeUpdate();
			}
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(rb.getString("db.deleteRecurringDeposit"))) {
				preparedStatement.setInt(1, depositid);
				preparedStatement.executeUpdate();
			}
			if(total_amount>0) {
			try (PreparedStatement preparedStatement = connection.prepareStatement(rb.getString("db.insertTransfer"))) {
				preparedStatement.setInt(1, 1);
				preparedStatement.setInt(2, accountnumber);
				preparedStatement.setInt(3, total_amount);
				preparedStatement.setString(4, "Premature Withdrawl From Bank");
				preparedStatement.setString(5,"IMPS");
				preparedStatement.executeUpdate();
			}}
//			System.out.println("Successfull");
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	public void changeAmount(int depositid, int amount) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(rb.getString("db.changeRecurringDeposit"));
			preparedStatement.setInt(1, amount);
			preparedStatement.setInt(2, depositid);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("There was an error");
		} finally {
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
