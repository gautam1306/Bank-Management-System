package com.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.db.Database;

public class MonthlyRoutine implements Runnable {

	@Override
	public void run() {
		try(Connection connection = Database.getConnection()){
		ResourceBundle resourceBundle = ResourceBundle.getBundle("sql");
		try(PreparedStatement preparedStatement=connection.prepareStatement(resourceBundle.getString("db.addIntrestRecurringDeposit"))){
			preparedStatement.executeUpdate();
			monthlyinvestmentdebit();}
		try(PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("db.addLoanIntrest"))){
		preparedStatement.executeUpdate();}
		loanEMI();
	}catch (SQLException e) {
		e.printStackTrace();
	}
}
	private void loanEMI() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("sql");
		try (Connection connection = Database.getConnection()) {
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement
						.executeQuery(resourceBundle.getString("db.getActiveLoanAccounts"))) {
					while (resultSet.next()) {
						autoLoanRepayment(resultSet.getInt("loan_id"), resultSet.getInt("account_number"),
								resultSet.getInt("payable_amount"), resultSet.getFloat("loan_interest_rate"),
								resultSet.getInt("months"));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void autoLoanRepayment(int loan_id, int account_number, int payable_amount, float interest_rate, int days) {
		int x;
		ResourceBundle resourceBundle = ResourceBundle.getBundle("sql");
		try (Connection connection = Database.getConnection()) {
			connection.setAutoCommit(false);
			int emi_amount = (int) ((payable_amount + (payable_amount * interest_rate / 1200)) * days / 30);
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(resourceBundle.getString("db.transferFund"))) {
				preparedStatement.setInt(1, -emi_amount);
				preparedStatement.setInt(2, account_number);
				preparedStatement.setInt(3, emi_amount);
				x = preparedStatement.executeUpdate();
			}
			if (x == 0) {
				connection.rollback();
			}
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(resourceBundle.getString("db.updateLoan"))) {
				int loan_amount = (int) (emi_amount - (payable_amount * interest_rate / 1200));
				preparedStatement.setInt(2, payable_amount - loan_amount);
				preparedStatement.setInt(1, 0);
				preparedStatement.setInt(3, loan_id);
				preparedStatement.executeQuery();
			}

			try (PreparedStatement preparedStatement = connection
					.prepareStatement(resourceBundle.getString("db.transferamount"))) {
				preparedStatement.setInt(1, emi_amount);
				preparedStatement.setInt(2, 1);
			}
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(resourceBundle.getString("db.insertTransfer"))) {
				preparedStatement.setInt(1, account_number);
				preparedStatement.setInt(2, 1);
				preparedStatement.setInt(3, emi_amount);
				preparedStatement.setString(4, "The pay towards loan EMI");
				preparedStatement.setString(5, "imps");
				preparedStatement.executeUpdate();
			}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void monthlyinvestmentdebit() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("sql");
		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(resourceBundle.getString("db.getAccountnumberRecurringDeposit"))) {
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						int depositID = resultSet.getInt("deposit_id");
						int transfer = resultSet.getInt("monthly_investment_amount");
						int accountNumber = resultSet.getInt("account_number");
						int balance = resultSet.getInt("balance");

						String discription = "For Recurring Deposit";
						int acc = 1;
						if (transfer <= balance) {
							try (PreparedStatement preparedStatement1 = connection
									.prepareStatement(resourceBundle.getString("db.transferAmount"))) {
								preparedStatement1.setInt(1, -transfer);
								preparedStatement1.setInt(2, accountNumber);
								preparedStatement1.executeUpdate();
							}
							try (PreparedStatement preparedStatement1 = connection
									.prepareStatement(resourceBundle.getString("db.transferAmount"))) {
								preparedStatement1.setInt(1, transfer);
								preparedStatement1.setInt(2, acc);
								preparedStatement1.executeUpdate();
							}
							try (PreparedStatement preparedStatement1 = connection
									.prepareStatement(resourceBundle.getString("db.insertTransfer"))) {
								preparedStatement1.setInt(1, accountNumber);
								preparedStatement1.setInt(2, acc);
								preparedStatement1.setInt(3, transfer);
								preparedStatement1.setString(4, discription);
								preparedStatement1.setString(5, "imps");
								preparedStatement1.executeUpdate();
							}
							try (PreparedStatement preparedStatement1 = connection
									.prepareStatement(resourceBundle.getString("db.addMonthlyinvestment"))) {
								preparedStatement1.setInt(1, depositID);
								preparedStatement1.executeUpdate();
							}
						}
					}
//		            System.out.println("The transfer was successfull");
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
