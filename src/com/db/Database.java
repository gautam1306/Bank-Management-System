package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Database {
	public static Connection getConnection() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("sql");
		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
			Connection connection=DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.password"));  
			return connection;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
//