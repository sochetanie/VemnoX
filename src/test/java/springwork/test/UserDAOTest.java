package springwork.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import springwork.dao.UserAccountDAO;
import springwork.model.UserAccount;

public class UserDAOTest {
	UserAccountDAO uDAO;
	UserAccount user, uToCheck;
	int uID, result;
	
	@Before
	public void setUp() {
		uDAO = new UserAccountDAO();
		user = new UserAccount("Tanjimul", "Bhuiyan", "TanjimulBhuiyan@gmail.com", "Bhuiyan@2018", "3475674747");
	}
	  
	@Test
	public void registerUser() throws SQLException  {
		uID = uDAO.registerUser(user);
		uToCheck = uDAO.findUserByID(uID);
		assertEquals(uToCheck.getFirstName(), user.getFirstName());
		assertEquals(uToCheck.getLastName(), user.getLastName());
		assertEquals(uToCheck.getEmail(), user.getEmail());
		assertEquals(uToCheck.getPassword(), user.getPassword());
		assertEquals(uToCheck.getPhoneNumber(), user.getPhoneNumber());
        assertEquals(uToCheck.getUserID(), uID);
	}
    
	@Test
	public void userByGmail() throws SQLException {
		uToCheck = uDAO.getUserByEmail(user.getEmail(), user.getPassword());
		assertEquals(uToCheck.getEmail(), user.getEmail());
		assertEquals(uToCheck.getPassword(), user.getPassword());
	}

	@Test
	public void findUserByID() throws SQLException {
		uID = uDAO.registerUser(user);
		uToCheck = uDAO.findUserByID(uID);
		assertEquals(uToCheck.getUserID(), uID);
		assertEquals(uToCheck.getEmail(), user.getEmail());
		assertEquals(uToCheck.getPassword(), user.getPassword());
	}
	
	
	@Test
	public void findUserByFirstNameAndLastName() throws SQLException {
		List<UserAccount> uToCheck = uDAO.findUserByFirstNameAndLastName("Tanjimul Bhuiyan");
		assertThat(uToCheck, notNullValue());
		for (UserAccount userAccount : uToCheck) {
			assertEquals(userAccount.getFirstName(), user.getFirstName());
			assertEquals(userAccount.getLastName(), user.getLastName());
		}
		
	}
	
	
	@Test
	public void updateUserInfo() throws SQLException  {
		uToCheck = uDAO.findUserByID(220);
		uToCheck.setEmail("t.bhuiyan@gmail.com");
		int result = uDAO.updateUserInfo(uToCheck, uToCheck.getUserID());
		assertEquals(uToCheck.getUserID(), 220);
		assertThat(uToCheck.getEmail(), is("t.bhuiyan@gmail.com"));
		assertEquals(result, 1);
	}
        
	
	@Test
	public void removeUser() throws SQLException  {
		 int id  = uDAO.removeUser(220);
	     assertEquals(1,id);
	}
	
	
}	
