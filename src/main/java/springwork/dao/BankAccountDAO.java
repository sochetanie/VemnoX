package springwork.dao;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import springwork.model.*;
import springwork.queries.DbaQueries;

public class BankAccountDAO {
	
	public int addBankAccount(BankAccount bankAccount) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		int id = 0;
		String[] col = {"bankAccountID"};
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(DbaQueries.BankAccounts.addBankAccount,col);
			stmt.setDouble(1, bankAccount.getAmountAvailable());
			stmt.setString(2, bankAccount.getRoutingNumber());
			stmt.setString(3, bankAccount.getAccountNumber());
			stmt.setInt(4, bankAccount.getUserID());
			stmt.setInt(5, bankAccount.getBankAccountID());
			stmt.executeUpdate();
			
			result = stmt.getGeneratedKeys();
			
			if(result.next())
				id = result.getInt(1);
			
		} catch (ClassNotFoundException | IOException | SQLException e) {

			e.printStackTrace();
		}
		
		if(conn != null)
			conn.close();
		if(result != null)
			result.close();
		if(stmt != null)
			stmt.close();
		
		return id;
		
	}
	
	
	public List<BankAccount> showBankAccounts(int userID) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result1 =null;
		
		List<BankAccount> allBankAccounts = new ArrayList<BankAccount>();
		
		try {
			conn =  OracleConnection.getConnection();
			stmt = conn.prepareStatement(DbaQueries.BankAccounts.ShowALlBankAccounts);
			stmt.setInt(1, userID);
			result1 = stmt.executeQuery();
			
			while (result1.next()) {
				BankAccount bankAccount = new BankAccount();
				bankAccount.setAmountAvailable(result1.getDouble(1));
				bankAccount.setRoutingNumber(result1.getString(2));
				bankAccount.setAccountNumber(result1.getString(3));
				bankAccount.setBankAccountID(result1.getInt(4));
				bankAccount.setUserID(result1.getInt(5));
				allBankAccounts.add(bankAccount);
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {

			e.printStackTrace();
		}
		
		if(conn != null)
			conn.close();
		if(result1 != null)
			result1.close();
		if(stmt != null)
			stmt.close();
		
		return allBankAccounts;
	}
	
	
	
	public int deleteBankAccount(int bankAccountID) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		int id = 0;
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(DbaQueries.BankAccounts.DeleteBankAccount);
			stmt.setInt(1, bankAccountID);
			id =stmt.executeUpdate();
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		
		if(conn != null)
			conn.close();
		if(result != null)
			result.close();
		if(stmt != null)
			stmt.close();
		
		return id;
	}

}
