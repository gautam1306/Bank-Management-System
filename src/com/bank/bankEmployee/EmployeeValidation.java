package com.bank.bankEmployee;

import com.db.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeValidation {

	public int validate(int employeeID, String password) {
		ResourceBundle rb = ResourceBundle.getBundle("sql");
		try (Connection connection = Database.getConnection()) {
			try (PreparedStatement ps = connection.prepareStatement(rb.getString("db.validateEmployee"))) {
				ps.setInt(1, employeeID);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						if (rs.getString("password").equals(password)) {
							return rs.getInt("employee_role");
						} else {
//							System.out.println("The password is incorrect");
							return 0;
						}
					} else {
						return -1;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
}
