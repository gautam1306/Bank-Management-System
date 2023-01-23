package com.bank.bankEmployee;

import com.db.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class AccountantDao {
	private ResourceBundle rb = ResourceBundle.getBundle("sql");

	public ArrayList<String> getEmployee(int employeeID) {
		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(rb.getString("db.getEmployeeDetails"))) {
				preparedStatement.setInt(1, employeeID);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					resultSet.next();
					ArrayList<String> arr = new ArrayList<>();
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
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
//        	System.out.println(e);
			return null;
		}
	}

	public int createCustomer(String name, String aadhaar_number, String date_of_birth, String mobile_number,
			String pan_card, int gender, String address_house, String address_street, String address_district,
			String address_state, int address_pin_code, String password) {
		int profileid;
		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement = connection.prepareStatement(rb.getString("db.addProfile"))) {
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, aadhaar_number);
				preparedStatement.setString(3, pan_card);
				preparedStatement.setString(4, date_of_birth);
				preparedStatement.setInt(5, gender);
				preparedStatement.setString(6, mobile_number);
				preparedStatement.setString(7, address_house);
				preparedStatement.setString(8, address_street);
				preparedStatement.setString(9, address_district);
				preparedStatement.setString(10, address_state);
				preparedStatement.setInt(11, address_pin_code);
				preparedStatement.executeUpdate();
			}
			try (PreparedStatement preparedStatement = connection.prepareStatement(rb.getString("db.getProfileId"))) {
				preparedStatement.setString(1, aadhaar_number);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					resultSet.next();
					profileid = resultSet.getInt(1);
				}
			}
			try (PreparedStatement preparedStatement = connection.prepareStatement(rb.getString("db.addcustomer"))) {
				preparedStatement.setInt(2, profileid);
				preparedStatement.setString(1, password);
				preparedStatement.executeUpdate();
			}
			try (PreparedStatement preparedStatement = connection.prepareStatement(rb.getString("db.getCustomerid"))) {
				preparedStatement.setInt(1, profileid);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					resultSet.next();
					int customerid = resultSet.getInt(1);
					return customerid;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getErrorCode());
			return -1;
		}
	}

	public void addAccount(int id, String ifsc, int m, int overdraftLimit, int transferLimit) {
		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement = connection.prepareStatement(rb.getString("db.addAccount"))) {
				preparedStatement.setInt(1, id);
				preparedStatement.setString(2, ifsc);
				preparedStatement.setInt(3, m);
				preparedStatement.setInt(4, transferLimit);
				preparedStatement.setInt(5, overdraftLimit);
				preparedStatement.setInt(6, id);
				int i = preparedStatement.executeUpdate();
				if (i == 1) {
					System.out.println("The account has been successfully created");
				} else {
					System.out.println("There is an account in this branch with same specifications");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if (e.getErrorCode() == 0) {
				System.out.println("This account already exist or else there is no such customer id");
			}
//			e.printStackTrace();
		}
	}

	public void depositAmount(int amount, int acc, String discription) {
		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement = connection.prepareStatement(rb.getString("db.transferAmount"))) {
				preparedStatement.setInt(1, amount);
				preparedStatement.setInt(2, acc);
				preparedStatement.executeUpdate();
			}
			try (PreparedStatement preparedStatement = connection.prepareStatement(rb.getString("db.insertTransfer"))) {
				preparedStatement.setInt(1, 1);
				preparedStatement.setInt(2, acc);
				preparedStatement.setInt(3, amount);
				preparedStatement.setString(4, discription);
				preparedStatement.setString(5, "RTGS");
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("There was an error");
		}
	}

	public void deleteAccount(int accountnumber) {
		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement = connection.prepareStatement(rb.getString("db.deleteAccount"))) {
				preparedStatement.setInt(1, accountnumber);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			e.printStackTrace();
		}
	}

	public TreeMap<Integer, CardCompany> getCardCompany() {
		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement = connection.prepareStatement(rb.getString("db.getCardCompany"))) {
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					TreeMap<Integer, CardCompany> company = new TreeMap<>();
					while (resultSet.next()) {
						CardCompany card = new CardCompany(resultSet.getInt(1), resultSet.getString(2));
						company.put(card.companyid, card);
					}
					return company;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void addDebitCard(int accountnumber, int transferlimit, int companyid) {
		int cardid;
		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement = connection.prepareStatement(rb.getString("db.insertcard"))) {
				preparedStatement.setInt(2, companyid);
				preparedStatement.setInt(1, transferlimit);
				preparedStatement.setInt(3, accountnumber);
				preparedStatement.executeUpdate();
			}
			try (PreparedStatement preparedStatement = connection.prepareStatement(rb.getString("db.getCardid"))) {
				preparedStatement.setInt(1, accountnumber);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					resultSet.next();
					cardid = resultSet.getInt(1);
				}
			}
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(rb.getString("db.setCardIdToAccounts"))) {
				preparedStatement.setInt(1, cardid);
				preparedStatement.setInt(2, accountnumber);
				preparedStatement.executeUpdate();
				System.out.println("Card Successfully generated");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
