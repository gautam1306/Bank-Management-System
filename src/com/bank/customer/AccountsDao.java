package com.bank.customer;

import com.db.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.ResourceBundle;

public class AccountsDao {
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("sql");    
    public int checkAccount(int acc) {
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
    	try {
        	connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(resourceBundle.getString("db.getAccountInfo"));
            preparedStatement.setInt(1, acc);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
            	int i = resultSet.getInt("balance");
            	return i;
            }
            return -1;
        } catch ( SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
    	finally {
        	try {
        		if(resultSet!=null) {
    			resultSet.close();}
        		if(preparedStatement!=null) {
    			preparedStatement.close();}
        		if(connection!=null) {
    			connection.close();}
    		} catch (SQLException  e) {
    			// TODO Auto-generated catch block
    			System.out.println(e);
    		}
		}
    }
    public void transactions(int accountNumber,int balance) {
        Formatter formatter = new Formatter();
//		formatter.format("%100s
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
        	connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(resourceBundle.getString("db.getTransaction"));
            formatter.format("|%15s|%19s|%17s|%19s|%15s|%16s|%19s|%10s|%40s|\n","Transaction ID","From Account Number","To Account Number","Date of Transaction","Transfer Amount","transaction type","mode of transaction","Balance","Description");
            preparedStatement.setInt(1,accountNumber);
            preparedStatement.setInt(2, accountNumber);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
            	
                formatter.format("|%15d|%19d|%17d|%19s|%15d|%16s|%19s|%10d|%40s|\n",resultSet.getInt("transaction_id"),resultSet.getInt("from_account_number"),resultSet.getInt("to_account_number"),resultSet.getString("date_of_transfer"),resultSet.getInt("transfer_amount"),(resultSet.getInt("from_account_number")==accountNumber?"debit":"credit"),resultSet.getString("mode_of_transaction"),balance,resultSet.getString("description"));
                balance +=(resultSet.getInt("from_account_number")==accountNumber?1:-1)*resultSet.getInt("transfer_amount");
            }
            System.out.println(formatter);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
        	try {
        		if(resultSet!=null) {
    			resultSet.close();}
        		if(preparedStatement!=null) {
    			preparedStatement.close();}
        		if(connection!=null) {
    			connection.close();}
    		} catch (SQLException  e) {
    			// TODO Auto-generated catch block
    			System.out.println(e);
    		}
		}
    }

    public int[] getBenificiary(int accountNumber, int acc) {
    	Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
    	try {
        	connection = Database.getConnection();
            preparedStatement= connection.prepareStatement(resourceBundle.getString("db.getBenificiary"));
            preparedStatement.setInt(1,accountNumber);
            preparedStatement.setInt(2,acc);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int[] arr = new int[3];
                arr[0]= resultSet.getInt(1);
                arr[1]= resultSet.getInt(2);
                arr[2]= resultSet.getInt(3);
                return arr;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    	finally {
        	try {
        		if(resultSet!=null) {
    			resultSet.close();}
        		if(preparedStatement!=null) {
    			preparedStatement.close();}
        		if(connection!=null) {
    			connection.close();}
    		} catch (SQLException  e) {
    			// TODO Auto-generated catch block
    			System.out.println(e);
    		}
		}
    }
    public void transferMethod(int accountNumber,int acc, int transfer,int beneficiary, String discription,String type) {
    	Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
    	try {
        	connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(resourceBundle.getString("db.getAccountInfo"));
            preparedStatement.setInt(1, acc);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            preparedStatement.close();
            if(beneficiary==0) {
                preparedStatement = connection.prepareStatement(resourceBundle.getString("db.updateTransfer"));
                preparedStatement.setInt(1,-transfer);
                preparedStatement.setInt(2,transfer);
                preparedStatement.setInt(3, accountNumber);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            else if(beneficiary==1) {
                preparedStatement= connection.prepareStatement(resourceBundle.getString("db.transferAmount"));
                preparedStatement.setInt(1,-transfer);
                preparedStatement.setInt(2,accountNumber);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                preparedStatement=connection.prepareStatement(resourceBundle.getString("db.updateBenificiary"));
                preparedStatement.setInt(1,transfer );
                preparedStatement.setInt(2, accountNumber);
                preparedStatement.setInt(3, acc);
                preparedStatement.setInt(4, transfer);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            preparedStatement=connection.prepareStatement(resourceBundle.getString("db.transferAmount"));
            preparedStatement.setInt(1, transfer);
            preparedStatement.setInt(2,acc);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            preparedStatement=connection.prepareStatement(resourceBundle.getString("db.insertTransfer"));
            preparedStatement.setInt(1, accountNumber);
            preparedStatement.setInt(2, acc);
            preparedStatement.setInt(3,transfer);
            preparedStatement.setString(4, discription);
            preparedStatement.setString(5, "imps");
            preparedStatement.executeUpdate();
            System.out.println("The transfer was successfull");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	finally {
        	try {
        		if(resultSet!=null) {
    			resultSet.close();}
        		if(preparedStatement!=null) {
    			preparedStatement.close();}
        		if(connection!=null) {
    			connection.close();}
    		} catch (SQLException  e) {
    			// TODO Auto-generated catch block
    			System.out.println(e);
    		}
		}
    }

    public boolean addBeneficiary(int accountNumber, int acc, int transferlimit) {
    	Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
    	try {
        	connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(resourceBundle.getString("db.addBeneficiary"));
            preparedStatement.setInt(1,accountNumber);
            preparedStatement.setInt(2,acc);
            preparedStatement.setInt(3,transferlimit);
            int x = preparedStatement.executeUpdate();
            if(x>0){
            	System.out.println("Account Successfully added");
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
        	String str = e.getSQLState();
//        	System.out.println(str);
        	if(str.equals("23505")) {
        		System.out.println("The beneficiary already exists");
        	}
        	if(str.equals("23503")) {
        		System.out.println("The beneficiary account does not exist");
        	}
        	return false;
//            throw new RuntimeException(e);
        }
    	finally {
        	try {
        		if(resultSet!=null) {
    			resultSet.close();}
        		if(preparedStatement!=null) {
    			preparedStatement.close();}
        		if(connection!=null) {
    			connection.close();}
    		} catch (SQLException  e) {
    			// TODO Auto-generated catch block
    			System.out.println(e);
    		}
		}
    }


	public ArrayList<Beneficiary> getBenificiaryList(int accountNumber) {
		ArrayList<Beneficiary> arr =  new ArrayList<>();
		Connection connection=null;
	    PreparedStatement preparedStatement=null;
	    ResultSet resultSet=null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.getBeneficiaryDetails"));
			preparedStatement.setInt(1, accountNumber);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Beneficiary bs = new Beneficiary();
				bs.accountnumber = resultSet.getInt(1);
				bs.firstName = resultSet.getString(2);
				bs.lastName = resultSet.getString(3);
				bs.ifsc = resultSet.getString(4);
				bs.transactionlimit = resultSet.getInt(5);
				arr.add(bs);
			}
			return arr;
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		finally {
        	try {
        		if(resultSet!=null) {
    			resultSet.close();}
        		if(preparedStatement!=null) {
    			preparedStatement.close();}
        		if(connection!=null) {
    			connection.close();}
    		} catch (SQLException  e) {
    			// TODO Auto-generated catch block
    			System.out.println(e);
    		}
		}
	}
	public void beneficiaryAdditionRequest(int accountNumber, int beneficiaryaccountnumber, int transferlimit, String beneficiaryifsc) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
		if(verifyBranch(beneficiaryifsc)) {
				connection = Database.getConnection();
				preparedStatement = connection.prepareStatement(resourceBundle.getString("db.verifyAccountRequest"));
				preparedStatement.setString(1, beneficiaryifsc);
				preparedStatement.setInt(2, beneficiaryaccountnumber);
				preparedStatement.executeUpdate();
				preparedStatement.close();
				preparedStatement = connection.prepareStatement(resourceBundle.getString("db.getrequestId"));
				preparedStatement.setInt(1, beneficiaryaccountnumber);
				resultSet = preparedStatement.executeQuery();
				resultSet.next();
				int requestid = resultSet.getInt(1);
				preparedStatement = connection.prepareStatement(resourceBundle.getString("db.addBeneficiaryRequest"));
				preparedStatement.setString(1, beneficiaryifsc);
				preparedStatement.setInt(2, beneficiaryaccountnumber);
				preparedStatement.setInt(3, accountNumber);
				preparedStatement.setInt(4, transferlimit);
				preparedStatement.setInt(5, requestid);
				preparedStatement.executeUpdate();
				System.out.println("Your Request has been successfully placed");
		}
		else {
			System.out.println("The Branch does not even exist");
		}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
        	try {
        		if(resultSet!=null) {
    			resultSet.close();}
        		if(preparedStatement!=null) {
    			preparedStatement.close();}
        		if(connection!=null) {
    			connection.close();}
    		} catch (SQLException  e) {
    			// TODO Auto-generated catch block
    			System.out.println(e);
    		}
		}
	}
	public boolean verifyBranch(String ifsc) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.checkBranch"));
			preparedStatement.setString(1, ifsc);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				if(resultSet!=null) {
				resultSet.close();}
				if(preparedStatement!=null) {
				preparedStatement.close();}
				if(connection!=null) {
				connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public ArrayList<Object> getCard(int accountnumber) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.getCardid"));
			preparedStatement.setInt(1, accountnumber);
			resultSet =preparedStatement.executeQuery();
			ArrayList<Object> detail = new ArrayList<>();
			if(resultSet.next()) {
				detail.add(resultSet.getInt("card_detail_id"));
				detail.add(resultSet.getBoolean("status")?"Active":"Inactive");
				detail.add(resultSet.getInt("transaction_limit"));
				detail.add(resultSet.getString("company_name"));
				detail.add(resultSet.getLong("card_number"));
				detail.add(resultSet.getString("expiry_date"));
			}
			else {
				return null;
			}
			return detail;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}finally {
        	try {
        		if(resultSet!=null) {
    			resultSet.close();}
        		if(preparedStatement!=null) {
    			preparedStatement.close();}
        		if(connection!=null) {
    			connection.close();}
    		} catch (SQLException  e) {
    			// TODO Auto-generated catch block
    			System.out.println(e);
    		}
		}
		return null;
	}
	public int loanPayment(int accountNumber,int loanID, int amount) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.getLoan"));
			preparedStatement.setInt(1, loanID);
			resultSet= preparedStatement.executeQuery();
			resultSet.next();
			int interestAmount = resultSet.getInt("interest_amount");
			int payableAmount = resultSet.getInt("payable_amount");
			int status = -1;
			preparedStatement.close();
			if(interestAmount+payableAmount<=amount) {
				
				amount = interestAmount+payableAmount;
				interestAmount = 0;
				payableAmount = 0;
				status= 2;
			}
			else if(interestAmount<amount) {
				payableAmount -= amount-interestAmount;
				interestAmount = 0;
				status =1;
			}
			else {
				interestAmount-=amount;
				status =0;
			}
			preparedStatement = connection.prepareStatement(resourceBundle.getString("db.updateLoan"));
			preparedStatement.setInt(1,interestAmount);
			preparedStatement.setInt(2, payableAmount);
			preparedStatement.setInt(3,loanID);
			preparedStatement.executeUpdate();
			transferMethod(accountNumber,1,amount,0,"Loan Part Payment","imps");
			System.out.println("Successfull");
			return status;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		finally {
        	try {
        		if(resultSet!=null) {
    			resultSet.close();}
        		if(preparedStatement!=null) {
    			preparedStatement.close();}
        		if(connection!=null) {
    			connection.close();}
    		} catch (SQLException  e) {
    			// TODO Auto-generated catch block
    			System.out.println(e);
    		}
		}
	}

}
