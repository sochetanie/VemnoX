package springwork.daoi;

import java.sql.SQLException;
import java.util.List;

import springwork.model.*;


public interface UserAccountDAOi {
	
	int registerUser(UserAccount userAccount) throws SQLException;
	UserAccount getUserByEmail(String email,String password) throws SQLException;
	List<UserAccount> findUserByFirstNameAndLastName(String searchResult) throws SQLException; 
	int removeUser(int userID) throws SQLException;
	UserAccount findUserByID(int userID) throws SQLException;
	int updateUserInfo(UserAccount userAccount, int userId) throws SQLException;
	
}