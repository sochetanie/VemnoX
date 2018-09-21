package springwork.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import springwork.model.*;
import springwork.queries.DbaQueries;

public class CreditCardsDAO {
	
	public int addCreditCard(CreditCards creditCards) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		int id = 0;
		String[] col = {"creditCardID"};
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(DbaQueries.CreditCards.addCreditCard,col);
			stmt.setDouble(1, creditCards.getAmountAvailable());
			stmt.setString(2, creditCards.getCardHolder());
			stmt.setString(3, creditCards.getCardNumber());
			stmt.setString(4, creditCards.getExpirationDate());
			stmt.setString(5, creditCards.getZipCode());
			stmt.setString(6, creditCards.getCVV());
			stmt.setInt(7, creditCards.getUserID());
			stmt.setInt(8, creditCards.getCreditCardID());
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
	
	
	public List<CreditCards> showALlCreditCards(int userID) throws SQLException {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet result = null;
			
			List<CreditCards> allCreditCards = new ArrayList<CreditCards>();
			
			try {
				conn = OracleConnection.getConnection();
				stmt = conn.prepareStatement(DbaQueries.CreditCards.ShowALlCreditCards);
				stmt.setInt(1, userID);
				result = stmt.executeQuery();
				
				while (result.next()) {
					CreditCards creditCard = new CreditCards();
					creditCard.setAmountAvailable(result.getDouble(1));
					creditCard.setCardHolder(result.getString(2));
					creditCard.setCardNumber(result.getString(3));
					creditCard.setExpirationDate(result.getString(4));
					creditCard.setZipCode(result.getString(5));
					creditCard.setCVV(result.getString(6));
					creditCard.setUserID(result.getInt(7));
					creditCard.setCreditCardID(result.getInt(8));
					allCreditCards.add(creditCard);
				}
			} catch (ClassNotFoundException | IOException | SQLException e) {

				e.printStackTrace();
			}
			
			if(conn != null)
				conn.close();
			if(result != null)
				result.close();
			if(stmt != null)
				stmt.close();
			
			return allCreditCards;
		}

	
	public int deleteCreditCard(int creditCardID) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		int id = 0;
		String[] col = {"creditCardID"};
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(DbaQueries.CreditCards.DeleteCreditCard,col);
			stmt.setInt(1, creditCardID);
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
