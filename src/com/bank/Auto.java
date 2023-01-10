package com.bank;

import com.db.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.time.LocalDate;

public class Auto {
	ResourceBundle resourceBundle = ResourceBundle.getBundle("sql");

	public void auto() {
		LocalDate date = java.time.LocalDate.now();
		String d = date.toString();
		// System.out.println(d);
		Statement statement = null;
		ResourceBundle resourceBundle = ResourceBundle.getBundle("sql");
		Connection connection = null;
		Scanner file;
		try {
			connection = Database.getConnection();
			File f = new File("date.txt");
			file = new Scanner(f);
			String date1 = file.next();
//	        System.out.println(date1);
			connection = Database.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(resourceBundle.getString("db.deactivateLoan"));
			if (!date1.equals(d)) {

				statement.executeUpdate(resourceBundle.getString("db.updateTransferAmount"));
				statement.executeUpdate(resourceBundle.getString("db.updateBeneficiaryTransfer"));

			}
			if (!date1.subSequence(5, 7).equals(d.subSequence(5, 7))) {
				statement.executeUpdate(resourceBundle.getString("db.addIntrestRecurringDeposit"));
				monthlyinvestmentdebit();
				statement.executeUpdate(resourceBundle.getString("db.addLoanIntrest"));
				loanEMI();
			}
			FileWriter fw = new FileWriter(f);
			fw.write(d);
			fw.close();
			file.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
		}

	}

	private void loanEMI() {
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;
		try {
			connection = Database.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(resourceBundle.getString("db.getActiveLoanAccounts"));
			while (resultSet.next()) {
				autoLoanRepayment(resultSet.getInt("loan_id"), resultSet.getInt("account_number"),
						resultSet.getInt("payable_amount"), resultSet.getFloat("loan_interest_rate"),
						resultSet.getInt("months"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void autoLoanRepayment(int loan_id, int account_number, int payable_amount, float interest_rate, int days) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = Database.getConnection();
			connection.setAutoCommit(false);
			int emi_amount = (int) ((payable_amount + (payable_amount * interest_rate / 1200)) * days / 30);
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.transferFund"));
			preparedStatement.setInt(1, -emi_amount);
			preparedStatement.setInt(2, account_number);
			preparedStatement.setInt(3, emi_amount);
			int x = preparedStatement.executeUpdate();
			if (x == 0) {
				connection.rollback();
			}
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.updateLoan"));
			int loan_amount = (int) (emi_amount - (payable_amount * interest_rate / 1200));
			preparedStatement.setInt(2, payable_amount - loan_amount);
			preparedStatement.setInt(1, 0);
			preparedStatement.setInt(3, loan_id);
			preparedStatement.executeQuery();
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.transferamount"));
			preparedStatement.setInt(1, emi_amount);
			preparedStatement.setInt(2, 1);
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.insertTransfer"));
			preparedStatement.setInt(1, account_number);
			preparedStatement.setInt(2, 1);
			preparedStatement.setInt(3, emi_amount);
			preparedStatement.setString(4, "The pay towards loan EMI");
			preparedStatement.setString(5, "imps");
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			// TODO: handle exception
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {

		}
	}

	@SuppressWarnings("resource")
	private void monthlyinvestmentdebit() {
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.getBalance"));
			preparedStatement.setInt(1, 1);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			resultSet.close();
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.getAccountnumberRecurringDeposit"));
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int depositID = resultSet.getInt("deposit_id");
				int transfer = resultSet.getInt("monthly_investment_amount");
				int accountNumber = resultSet.getInt("account_number");
				int balance = resultSet.getInt("balance");

				String discription = "For Recurring Deposit";
				int acc = 1;
				if (transfer <= balance) {
					preparedStatement = connection.prepareStatement(resourceBundle.getString("db.transferAmount"));
					preparedStatement.setInt(1, -transfer);
					preparedStatement.setInt(2, accountNumber);
					preparedStatement.executeUpdate();
					preparedStatement.close();
					preparedStatement = connection.prepareStatement(resourceBundle.getString("db.transferAmount"));
					preparedStatement.setInt(1, transfer);
					preparedStatement.setInt(2, acc);
					preparedStatement.executeUpdate();
					preparedStatement.close();
					preparedStatement = connection.prepareStatement(resourceBundle.getString("db.insertTransfer"));
					preparedStatement.setInt(1, accountNumber);
					preparedStatement.setInt(2, acc);
					preparedStatement.setInt(3, transfer);
					preparedStatement.setString(4, discription);
					preparedStatement.setString(5, "imps");
					preparedStatement.executeUpdate();
					preparedStatement = connection
							.prepareStatement(resourceBundle.getString("db.addMonthlyinvestment"));
					preparedStatement.setInt(1, depositID);
					preparedStatement.executeUpdate();
				}
//		            System.out.println("The transfer was successfull");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
		}
	}
}
