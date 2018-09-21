package springwork.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import springwork.model.*;
import springwork.queries.DbaQueries;

public class StatementDAO {
	
	public List<Statement> showStatement(int userID, String fromDate, String toDate) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		List<Statement> statements = new ArrayList<Statement>();
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(DbaQueries.UserAccount.showALlPaymentsTransfers);
			stmt.setInt(1, userID);
			stmt.setString(2, fromDate);
			stmt.setString(3, toDate);
			result = stmt.executeQuery();
			
			while (result.next()) {
				Statement statement = new Statement();
				statement.setId(result.getInt(1));
				statement.setUserid(result.getInt(2));
				statement.setAmount(result.getDouble(3));
				statement.setDatetime(result.getDate(4));
				statement.setType(result.getString(5));
				statement.setStatus(result.getString(6));
				statements.add(statement);
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
		
		return statements;
	
	
	}
	
	
	public List<Statement> showAllStatement(int userID) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		List<Statement> statements = new ArrayList<Statement>();
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(DbaQueries.UserAccount.showALlStatements);
			stmt.setInt(1, userID);
			result = stmt.executeQuery();
			
			while (result.next()) {
				Statement statement = new Statement();
				statement.setId(result.getInt(1));
				statement.setUserid(result.getInt(2));
				statement.setAmount(result.getDouble(3));
				statement.setDatetime(result.getDate(4));
				statement.setType(result.getString(5));
				statement.setStatus(result.getString(6));
				statements.add(statement);
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
		
		return statements;
	
	
	}
	
	

}
