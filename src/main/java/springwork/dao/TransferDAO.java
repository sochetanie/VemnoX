package springwork.dao;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import springwork.model.*;
import springwork.queries.*;

public class TransferDAO {
	
	public int addTransfer(Transfer transfer) throws SQLException {
		Connection conn = null;
		CallableStatement preparedStatement = null; // Allows to use store procedure
		int id = 0;
		
		try {
			conn = OracleConnection.getConnection();
			
			preparedStatement = conn.prepareCall("{call TransferToBank(?,?,?,?)}"); // store procedure
			
			preparedStatement.setInt(1, transfer.getUserID());
			preparedStatement.setDouble(2, transfer.getTransferAmount());
			preparedStatement.setInt(3, transfer.getBankAccountID());
			preparedStatement.registerOutParameter(4, Types.NUMERIC);
			preparedStatement.executeUpdate();
			id = preparedStatement.getInt(4); // 0-fail, 1-success
						
		} catch (ClassNotFoundException | IOException | SQLException e) {

			e.printStackTrace();
		}
		
		if(conn != null)
			conn.close();
		if(preparedStatement != null)
			preparedStatement.close();
		
		return id;
	}
	
	

}
