package springwork.dao;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import springwork.model.*;
import springwork.queries.*;


public class PaymentDAO {
	
	public int addPayment(Payment payment) throws SQLException {
		Connection conn = null;
		
		CallableStatement preparedStatement = null; // Allows to use store procedure
		int returnVal = 0;
		
		try {
			conn = OracleConnection.getConnection();
//			System.out.printf("Fid ="+payment.getFromUserID()+" \n");
//			System.out.printf("Tid ="+payment.getToUserID()+" \n");
//			System.out.printf("amount ="+payment.getPaymentAmount()+" \n");
//			System.out.printf("desc ="+payment.getDescription()+" \n");
//			System.out.printf("payOptId ="+payment.getPaymentOptionID()+" \n");
			
			
			preparedStatement = conn.prepareCall("{call PaymentFromTo(?,?,?,?,?,?)}"); // store procedure
			
			preparedStatement.setInt(1, payment.getFromUserID());
			preparedStatement.setInt(2, payment.getToUserID());
			preparedStatement.setDouble(3, payment.getPaymentAmount());
			preparedStatement.setString(4, payment.getDescription());
			preparedStatement.setInt(5, payment.getPaymentOptionID());
			preparedStatement.registerOutParameter(6, Types.NUMERIC);
			preparedStatement.executeUpdate();
			returnVal = preparedStatement.getInt(6); // 0-fail, 1-success
						
		} catch (ClassNotFoundException | IOException | SQLException e) {

			e.printStackTrace();
		}
		
		if(conn != null)
			conn.close();
		if(preparedStatement != null)
			preparedStatement.close();
		
		return returnVal;
		
	}
	
	
	
	public List<UserAccount> showALlPayments(int userID) throws SQLException  {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		List<UserAccount> payments = new ArrayList<UserAccount>();
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(DbaQueries.Payment.SHOWALLPAYMENTS);
			stmt.setInt(1, userID);
			stmt.setInt(2, userID);

			result = stmt.executeQuery();
			
			while(result.next()) {
				Payment payment = new Payment();
				UserAccount userAccount = new UserAccount();
				
				payment.setPaymentID(result.getInt("paymentID"));
				payment.setFromUserID(result.getInt("fromUserID"));
				payment.setToUserID(result.getInt("toUserID"));
				payment.setPaymentAmount(result.getDouble("paymentAmount"));
				payment.setDateTime(result.getString("dateTime"));
				payment.setDescription(result.getString("description"));
				payment.setPaymentOptionID(result.getInt("paymentOptionID"));
				userAccount.setPayment(payment);
				
				userAccount.setFirstName(result.getString("firstName"));
				userAccount.setLastName(result.getString("lastName"));
				payments.add(userAccount);
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
		
		
		return payments;
	}
	
	

}
