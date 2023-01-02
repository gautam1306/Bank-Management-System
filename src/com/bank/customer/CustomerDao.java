package com.bank.customer;

import com.db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class CustomerDao {
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("sql");

	public ArrayList<String> verify(int customerId, String password) {
		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement1 = connection
					.prepareStatement(resourceBundle.getString("db.validateCustomer"))) {
				preparedStatement1.setInt(1, customerId);
				preparedStatement1.setString(2, password);
				try (ResultSet resultSet = preparedStatement1.executeQuery()) {
					if (resultSet.next()) {
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
					} else {
						return null;
					}
				}
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("There was an error");
			return null;
		}
	}

	public TreeMap<Integer, Accounts> getAccounts(int customerId) {
		TreeMap<Integer, Accounts> accounts = new TreeMap<>();
		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(resourceBundle.getString("db.getAccounts"))) {
				preparedStatement.setInt(1, customerId);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
//			System.out.println("Hi inside getAccounts");
					while (resultSet.next()) {
						int account_number = resultSet.getInt("account_number");
						Accounts account = new Accounts(resultSet.getInt("account_number"), resultSet.getInt("balance"),
								resultSet.getInt("today_transfer"), resultSet.getString("account_type"),
								resultSet.getString("branch_ifsc"), resultSet.getInt("transfer_limit"),
								resultSet.getInt("overdraft"));
						accounts.put(account_number, account);
//				System.out.println(account);
					}
					if (!accounts.isEmpty()) {
						return accounts;
					} else {
						return null;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public TreeMap<Integer, RecurringDeposit> getRecurringDeposit(int customerId) {

		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(resourceBundle.getString("db.getRecurringDeposit"))) {
				preparedStatement.setInt(1, customerId);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					TreeMap<Integer, RecurringDeposit> recurringDeposits = new TreeMap<>();
					while (resultSet.next()) {
						int depositId = resultSet.getInt("deposit_id");
						int amount = resultSet.getInt("monthly_investment_amount");
						int accountnumber = resultSet.getInt("account_number");
						float intrestrate = resultSet.getFloat("interest_rate");
						String maturityperiod = resultSet.getString("maturity_period");
						int depositedamount = resultSet.getInt("deposit_amount");
						float intrestamount = resultSet.getFloat("total_interest_amount");
						String startDate = resultSet.getString("start_date");
						RecurringDeposit recurringDeposit = new RecurringDeposit(depositId, amount, depositedamount,
								maturityperiod, startDate, accountnumber, intrestrate, intrestamount, customerId);
						recurringDeposits.put(depositId, recurringDeposit);
					}
					if (!recurringDeposits.isEmpty()) {
						return recurringDeposits;
					} else {
						return null;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public TreeMap<Integer, FixedDeposits> getFixedDeposits(int customerId) {
		TreeMap<Integer, FixedDeposits> fixedDeposits = new TreeMap<>();
		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(resourceBundle.getString("db.getFixedDeposit"))) {
				preparedStatement.setInt(1, customerId);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
//			System.out.println("Hi inside getAccounts");

					while (resultSet.next()) {
						int depositid = resultSet.getInt("depositid");
						FixedDeposits fixeddeposit = new FixedDeposits(customerId, resultSet.getInt("depositid"),
								resultSet.getInt("accountnumber"), resultSet.getInt("amount"),
								resultSet.getString("maturityDate"), resultSet.getString("startDate"),
								resultSet.getFloat("intrestrate"));
						fixedDeposits.put(depositid, fixeddeposit);
						System.out.println(depositid);
					}
					if (!fixedDeposits.isEmpty()) {
						return fixedDeposits;
					} else {
						return null;
					}
				}
			}
		} catch (SQLException e) {
			return null;
		}
	}

	public void addRecurringDeposit(int customerID, int accountnumber, int period, int amount) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.addRecurringDeposit"));
			preparedStatement.setInt(1, accountnumber);
			preparedStatement.setInt(2, period);
			preparedStatement.setInt(3, period);
			preparedStatement.setInt(4, period);
			preparedStatement.setInt(5, amount);
			preparedStatement.executeUpdate();
			System.out.println("Successfully Created a recurring deposit");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean addFixedDeposit(int customerID, int accountnumber, int period, int amount) {
		int x;
		int accountbalance;
		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(resourceBundle.getString("db.transferamount"))) {
				preparedStatement.setInt(1, -amount);
				preparedStatement.setInt(2, accountnumber);
				preparedStatement.setInt(3, amount);
				x = preparedStatement.executeUpdate();
			}
			if (x >= 1) {
				try (PreparedStatement preparedStatement = connection
						.prepareStatement(resourceBundle.getString("db.getBalance"))) {
					preparedStatement.setInt(1, accountnumber);
					try (ResultSet resultSet = preparedStatement.executeQuery()) {
						resultSet.next();
						accountbalance = resultSet.getInt("balance");
					}
				}
				try (PreparedStatement preparedStatement = connection
						.prepareStatement(resourceBundle.getString("db.getBalance"))) {
					preparedStatement.setInt(1, accountnumber);
					try (ResultSet resultSet = preparedStatement.executeQuery()) {
						resultSet.next();
					}
				}
				if (accountbalance > amount) {
					try (PreparedStatement preparedStatement = connection
							.prepareStatement(resourceBundle.getString("db.addFixedDeposit"))) {
						preparedStatement.setInt(1, accountnumber);
						preparedStatement.setInt(2, period);
						preparedStatement.setInt(3, period);
						preparedStatement.setInt(4, period);
						preparedStatement.setInt(5, amount);
						preparedStatement.executeUpdate();
					}
					try (PreparedStatement preparedStatement = connection
							.prepareStatement(resourceBundle.getString("db.transferamount"))) {
						preparedStatement.setInt(1, amount);
						preparedStatement.setInt(2, 1);
						preparedStatement.setInt(3, -amount);
						preparedStatement.executeUpdate();
					}
					try (PreparedStatement preparedStatement = connection
							.prepareStatement(resourceBundle.getString("db.insertTransfer"))) {
						preparedStatement.setInt(1, accountnumber);
						preparedStatement.setInt(2, 1);
						preparedStatement.setInt(3, amount);
						preparedStatement.setString(5, "imps");
						preparedStatement.setString(4, "For fixed deposit");
						preparedStatement.executeUpdate();
					}

				}
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public void changePassword(int customerID, String password) {
		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(resourceBundle.getString("db.updatePassword"))) {
				preparedStatement.setString(1, password);
				preparedStatement.setInt(2, customerID);
				preparedStatement.executeUpdate();
				System.out.println("The password is successfully changed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void loanHistory(int customerID) {

		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(resourceBundle.getString("db.getLoanHistory"))) {
				preparedStatement.setInt(1, customerID);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						System.out.println(resultSet.getInt("loan_id") + "   " + resultSet.getInt("loan_amount") + "  "
								+ resultSet.getString("date_of_approval") + "  "
								+ resultSet.getString("maturity_date"));
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public HashMap<Integer,Beneficiary> getBenificiaryList(int customerID) {
		HashMap<Integer,Beneficiary> arr = new HashMap<>();
		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(resourceBundle.getString("db.getBeneficiaryDetails"))) {
				preparedStatement.setInt(1, customerID);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						Beneficiary bs = new Beneficiary();
						bs.setAccountnumber(resultSet.getInt("beneficiaryaccount"));
						bs.setNickName(resultSet.getString("nickname"));
						bs.setIfsc(resultSet.getString("branch_ifsc"));
						bs.setTransactionlimit(resultSet.getInt("transferlimit"));
						bs.setTransfer(resultSet.getInt("transfer"));
						arr.put(bs.getAccountnumber(),bs);
					}
				}
			}
			return arr;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int addBeneficiary(int customerID, int acc, int transferlimit,String nickname) {
		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(resourceBundle.getString("db.addBeneficiary"))) {
				preparedStatement.setInt(1, customerID);
				preparedStatement.setInt(2, acc);
				preparedStatement.setInt(3, transferlimit);
				preparedStatement.setString(4,nickname);
				preparedStatement.setInt(5, acc);
				int x = preparedStatement.executeUpdate();
				if (x > 0) {
					System.out.println("Account Successfully added");
					return 1;
				}
				else {
					System.out.println("sorry");
				}
			}
		} catch (SQLException e) {
			String str = e.getSQLState();
			System.out.println("????");
//        	System.out.println(str);
			if (str.equals("23505")) {
				System.out.println("The beneficiary already exists");
				return 23505;
			}
			if (str.equals("23503")) {
				System.out.println("The beneficiary account does not exist");
				return 23503;
			}
		}
		return 0;
	}

	public TreeMap<Integer, Loan> getloanAccounts(int customerID) {
		TreeMap<Integer, Loan> loans =new TreeMap<Integer, Loan>();
		try(Connection connection = Database.getConnection()){
			try(PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("db.getLoanAccounts"))){
				preparedStatement.setInt(1, customerID);
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					while(resultSet.next()) {
						Loan loan=new Loan(resultSet.getInt("loan_id"),resultSet.getFloat("interest_amount"),resultSet.getFloat("loan_interest_rate"),resultSet.getString("lastdateofpayment"),resultSet.getInt("payable_amount"),resultSet.getInt("loan_amount"), true,resultSet.getString("loan_type"),customerID);
						loans.put(loan.loanID,loan);
					}
				}
			}
			return loans;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
