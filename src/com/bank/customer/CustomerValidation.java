package com.bank.customer;

import com.db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerValidation {
	ResourceBundle resourceBundle = ResourceBundle.getBundle("sql");
	public Customer verify(int customerId, String password) {
		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(resourceBundle.getString("db.validateCustomer"))) {
				preparedStatement.setInt(1, customerId);
				preparedStatement.setString(2, password);
				try (ResultSet rs = preparedStatement.executeQuery()) {
					if (rs.next()) {
						ArrayList<String> property = new ArrayList<>();
						property.add(rs.getString("name"));
						property.add(rs.getString("date_of_birth"));
						property.add(rs.getString("mobile_number"));
						property.add(rs.getString("aadhaar_number"));
						property.add(rs.getString("pan_card"));
						property.add(rs.getString("gender"));
						property.add(rs.getString("address_house"));
						property.add(rs.getString("address_street"));
						property.add(rs.getString("address_district"));
						property.add(rs.getString("address_state"));
						property.add(Integer.toString(rs.getInt("address_pin_code")));
						return new Customer(customerId, password, property);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
