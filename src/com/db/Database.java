package com.db;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Database {
	private static DataSource ds = null; 
	public static Connection getConnection() {
		Context ctx;
		
		if(ds==null){try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/Bank");
			return ds.getConnection();
		} catch (NamingException |SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		else {
			try {
				return ds.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
	}
}
//