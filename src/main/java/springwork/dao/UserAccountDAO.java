package springwork.dao;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.stream.*;


import springwork.daoi.*;
import springwork.model.*;
import springwork.queries.*;


public class UserAccountDAO implements UserAccountDAOi {

		public int registerUser(UserAccount userAccount) throws SQLException {

			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet result = null;
			int id = 0;
			String[] col = {"UserID"};
			try {
				conn = OracleConnection.getConnection();
				stmt = conn.prepareStatement(DbaQueries.UserAccount.REGISTERUSER,col);
				stmt.setString(1, userAccount.getFirstName());
				stmt.setString(2, userAccount.getLastName());
				stmt.setString(3, userAccount.getEmail());
				stmt.setString(4, userAccount.getPassword());
				stmt.setString(5, userAccount.getPhoneNumber());
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

	
		public UserAccount getUserByEmail(String email, String password) throws SQLException {
			UserAccount user = null;
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet result = null;

			try {
				conn = OracleConnection.getConnection();
	            stmt = conn.prepareStatement(DbaQueries.UserAccount.GETUSERBYEMAIL);
	            stmt.setString(1, email);
	            result = stmt.executeQuery();
	            if(result.next()){
	            	if(this.checkPassword(password, result.getString("password"))) {
		                user = new UserAccount();
		                user.setUserID(result.getInt(1));
		                user.setFirstName(result.getString(2));
		                user.setLastName(result.getString(3));
		                user.setEmail(result.getString(4));
		                user.setPassword(result.getString(5));
		                user.setPhoneNumber(result.getString(6));
		                user.setPictureID(result.getInt(7));
		                user.setBalance(result.getDouble(8));
		                user.setJoinDate(result.getString(9));
	            	}
	            }
	            result.close();
				
			} catch (ClassNotFoundException | IOException | SQLException e) {
				e.printStackTrace();
			} finally {
	            
	            if(stmt != null){
	                stmt.close();
	            }
	            if(conn != null){
	                conn.close();
	            }
	        }

			return user;	
		}
		
		
		public UserAccount findUserByID(int userID) throws SQLException {

			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet result = null;
			UserAccount user = null;
			
			try {
				conn = OracleConnection.getConnection();
				stmt = conn.prepareStatement(DbaQueries.UserAccount.FINDUSERBYID);
				stmt.setInt(1, userID);
				result = stmt.executeQuery();
				
				
				if(result.next())
					user = new UserAccount();
					user.setUserID(result.getInt("UserID"));
					user.setFirstName(result.getString("firstName"));
					user.setLastName(result.getString("lastName"));
					user.setEmail(result.getString("email"));
					user.setPassword(result.getString("password"));
					user.setPhoneNumber(result.getString("phoneNumber"));
					user.setPictureID(result.getInt("pictureID"));
					user.setBalance(result.getDouble("balance"));
					user.setJoinDate(result.getString("joinDate"));
					

			} catch (ClassNotFoundException | IOException | SQLException e) {
				e.printStackTrace();
			}
			
			if(conn != null)
				conn.close();
			if(result != null)
				result.close();
			if(stmt != null)
				stmt.close();
			
			return user;
		}
		
		
		public List<UserAccount> findUserByFirstNameAndLastName(String searchResult) throws SQLException {

			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet result = null;
			
			UserAccount user = null;
			List<UserAccount> findUsers = null;
			
			try {
				findUsers = new ArrayList<UserAccount>();
				conn = OracleConnection.getConnection();
				stmt = conn.prepareStatement(DbaQueries.UserAccount.SearchUsers);
				stmt.setString(1, searchResult.toLowerCase());
				result = stmt.executeQuery();
				
				while(result.next()) {
					user= new UserAccount();
					user.setUserID(result.getInt("userID"));
					user.setFirstName(result.getString("firstName"));
					user.setLastName(result.getString("lastName"));
					user.setJoinDate(result.getString("joinDate"));
					findUsers.add(user);
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
			
			return findUsers;
		}
			
		
		public int removeUser(int userID) throws SQLException {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet result = null;
			int id = 0;
			String[] col = {"userID"};
			try {
				conn = OracleConnection.getConnection();
				stmt = conn.prepareStatement(DbaQueries.UserAccount.DELETEUSER,col);
				stmt.setInt(1, userID);
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
			
		
		public boolean checkPassword(String passToValidate, String comparablePas) {
			return passToValidate.equals(comparablePas);
		}
		
		
		public int updateUserInfo(UserAccount userAccount, int userId) throws SQLException {
		       Connection conn = null;
		       PreparedStatement stmt = null;
		       int id = 0;
		       
		       try{
		           conn = OracleConnection.getConnection();
		           stmt = conn.prepareStatement(DbaQueries.UserAccount.UPDATEUSERINFO);
		           stmt.setString(1, userAccount.getFirstName());
		           stmt.setString(2, userAccount.getLastName());
		           stmt.setString(3, userAccount.getEmail());
		           stmt.setString(4, userAccount.getPassword());
		           stmt.setString(5, userAccount.getPhoneNumber());
		           stmt.setInt(6, userId);
		           id =stmt.executeUpdate();
		           
		       } catch (ClassNotFoundException | IOException | SQLException e){
		           e.printStackTrace();
		       } finally {

		           if(stmt != null){
		               stmt.close();
		           }
		           if(conn != null){
		               conn.close();
		           }
		       }
		       return id;
		   }
		
		
	
}




