package com.bank.bankEmployee;

import com.db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeValidation {

    public int validate(int employeeID, String password) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResourceBundle rb = ResourceBundle.getBundle("sql");
        try {
            connection = Database.getConnection();
            ps = connection.prepareStatement(rb.getString("db.validateEmployee"));
            ps.setInt(1,employeeID);
            rs = ps.executeQuery();
            if(rs.next()){
                if(rs.getString("password").equals(password)){
                    return rs.getInt("employee_role");
                }
                else {
                    System.out.println("The password is incorrect");
                }
            }
            else{
                System.out.println("The Employee ID does not exist");
            }
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            catch (NullPointerException e){

            }
        }

    }
}
