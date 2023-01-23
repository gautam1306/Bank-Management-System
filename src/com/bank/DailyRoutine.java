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

public class DailyRoutine implements Runnable {
	ResourceBundle resourceBundle = ResourceBundle.getBundle("sql");
	@Override
	public void run() {
		try (Connection connection = Database.getConnection()) {
			try (Statement statement = connection.createStatement()) {
				statement.executeUpdate(resourceBundle.getString("db.deactivateLoan"));
				statement.executeUpdate(resourceBundle.getString("db.updateTransferAmount"));
				statement.executeUpdate(resourceBundle.getString("db.updateBeneficiaryTransfer"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
