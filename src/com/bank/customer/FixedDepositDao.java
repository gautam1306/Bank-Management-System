package com.bank.customer;

import com.db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class FixedDepositDao {
	private ResourceBundle rb = ResourceBundle.getBundle("sql");
	public void transferamount(int depositId, int accountnumber, float intrestRate) {
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			con = Database.getConnection();
			ps = con.prepareStatement(rb.getString("db.getFixedDepositDetail"));
			ps.setInt(1, depositId);
			rs = ps.executeQuery();
			int amount = rs.getInt("amount");
			int days = rs.getInt("days");
			double intrest = amount*intrestRate*days/365.0;
			int total_amount = amount+(int)intrest;
			ps= con.prepareStatement(rb.getString("db.transferAmount"));
			ps.setInt(1, total_amount);
			ps.setInt(2, accountnumber);
			ps.executeUpdate();
			ps = con.prepareStatement(rb.getString("db.transferAmount"));
			ps.setInt(1, -total_amount);
			ps.setInt(2, 1);
			ps.executeUpdate();
			ps = con.prepareStatement(rb.getString("db.deleteFixedDeposit"));
			ps.setInt(1, depositId);
			ps.executeUpdate();
			ps= con.prepareStatement(rb.getString("db.addTransaction"));
			int frombalance = getBalance(1);
			int tobalance = getBalance(accountnumber);
			ps.setInt(1,1);
			ps.setInt(2, accountnumber);
			ps.setInt(3,frombalance);
			ps.setInt(4, tobalance);
			ps.setInt(5,total_amount);
			ps.setString(6, "Premature Withdrawl From Bank");
			ps.executeUpdate();
		}
		catch (SQLException e) {
			
		}
	}
	private int getBalance(int accountnumber){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = Database.getConnection();
			ps = con.prepareStatement(rb.getString("db.getBalance"));
			ps.setInt(1, accountnumber);
			rs = ps.executeQuery();
			rs.next();
			int balance = rs.getInt("balance");
			return balance;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
}
