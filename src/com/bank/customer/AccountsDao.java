package com.bank.customer;

import com.db.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AccountsDao {
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("sql");

	public int checkAccount(int acc) {
		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(resourceBundle.getString("db.getAccountInfo"))) {
				preparedStatement.setInt(1, acc);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						int i = resultSet.getInt("balance");
						return i;
					}
				}
			}
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public ArrayList<Transaction> transactions(int accountNumber, int balance) {
//		formatter.format("%100s
		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(resourceBundle.getString("db.getTransaction"))) {
				preparedStatement.setInt(1, accountNumber);
				preparedStatement.setInt(2, accountNumber);
				ArrayList<Transaction> transactions = new ArrayList<Transaction>();
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						Transaction transaction = new Transaction(resultSet.getInt("transaction_id"),
								resultSet.getInt("from_account_number"), resultSet.getInt("to_account_number"),
								resultSet.getString("date_of_transfer"), resultSet.getInt("transfer_type"),
								resultSet.getString("description"), resultSet.getInt("transfer_amount"),
								resultSet.getString("mode_of_transaction"), balance);
						balance += ((resultSet.getInt("from_account_number") == accountNumber ? 1 : -1)
								* resultSet.getInt("transfer_amount"));
//						System.out.println(balance);
						transactions.add(transaction);
					}
					return transactions;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void transferMethod(int accountNumber, int acc, int transfer, int beneficiary, String discription,
			String mode) {
		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(resourceBundle.getString("db.getAccountInfo"))) {
				preparedStatement.setInt(1, acc);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					resultSet.next();
				}
			}
			if (beneficiary == 0) {
				try (PreparedStatement preparedStatement = connection
						.prepareStatement(resourceBundle.getString("db.updateTransfer"))) {
					preparedStatement.setInt(1, -transfer);
					preparedStatement.setInt(2, transfer);
					preparedStatement.setInt(3, accountNumber);
					preparedStatement.executeUpdate();
				}
			} else if (beneficiary == 1) {
				try (PreparedStatement preparedStatement = connection
						.prepareStatement(resourceBundle.getString("db.transferAmount"))) {
					preparedStatement.setInt(1, -transfer);
					preparedStatement.setInt(2, accountNumber);
					int x = preparedStatement.executeUpdate();
					System.out.println(x);
				}
				try (PreparedStatement preparedStatement = connection
						.prepareStatement(resourceBundle.getString("db.updateBenificiary"))) {
					preparedStatement.setInt(1, transfer);
					preparedStatement.setInt(2, accountNumber);
					preparedStatement.setInt(3, acc);
					preparedStatement.executeUpdate();
				}
			}
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(resourceBundle.getString("db.transferAmount"))) {
				preparedStatement.setInt(1, transfer);
				preparedStatement.setInt(2, acc);
				preparedStatement.executeUpdate();
			}
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(resourceBundle.getString("db.insertTransfer"))) {
				preparedStatement.setInt(1, accountNumber);
				preparedStatement.setInt(2, acc);
				preparedStatement.setInt(3, transfer);
				preparedStatement.setString(4, discription);
				preparedStatement.setString(5, mode);
				if (transfer > 0) {
					preparedStatement.executeUpdate();
				}
			}
			System.out.println("The transfer was successfull");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean verifyBranch(String ifsc) {
		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(resourceBundle.getString("db.checkBranch"))) {
				preparedStatement.setString(1, ifsc);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						return true;
					} else {
						return false;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<Object> getCard(int accountnumber) {
		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(resourceBundle.getString("db.getCardid"))) {
				preparedStatement.setInt(1, accountnumber);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					ArrayList<Object> detail = new ArrayList<>();
					if (resultSet.next()) {
						detail.add(resultSet.getInt("card_detail_id"));
						detail.add(resultSet.getBoolean("status") ? "Active" : "Inactive");
						detail.add(resultSet.getInt("transaction_limit"));
						detail.add(resultSet.getString("company_name"));
						detail.add(resultSet.getLong("card_number"));
						detail.add(resultSet.getString("expiry_date"));
					} else {
						return null;
					}
					return detail;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void loanPayment(int accountNumber, Loan loan, int amount) {
		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(resourceBundle.getString("db.updateLoan"))) {
				preparedStatement.setFloat(1, loan.interestPayable);
				preparedStatement.setInt(2, loan.payableAmount);
				preparedStatement.setInt(3, loan.loanID);
				preparedStatement.executeUpdate();
			}
			transferMethod(accountNumber, 1, amount, 0, "Loan Part Payment towards the loan ID:" + loan.loanID, "imps");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
