package springwork.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import springwork.dao.UserAccountDAO;
import springwork.model.UserAccount;

public class UserDAOTest {
	UserAccountDAO uDAO;
	UserAccount user, uToCheck;
	int uID;
	boolean isUpDated, willDelete;
	
	@Before
	public void setUp() {
		uDAO = new UserAccountDAO();
		user = new UserAccount("Tanjimul", "Bhuiyan", "TanjimulBhuiyan@gmail.com", "Bhuiyan@2018", "3475674747");
	}
	  
	@Test
	public void registerUser() throws SQLException  {
		uID = uDAO.registerUser(user);
		uToCheck = uDAO.findUserByID(uID);
        assertEquals(uToCheck.getUserID(), uID);
	}
    
	@Test
	public void testBGetUserByGmail() throws SQLException {
		uToCheck = uDAO.getUserByEmail(user.getEmail(), user.getPassword());
		assertEquals(user.getEmail(), uToCheck.getEmail());
		assertEquals(user.getPassword(), uToCheck.getPassword());
	}

	@Test
	public void testCFindUserByID() throws SQLException {
		uToCheck = uDAO.findUserByID(uID);
		
		assertEquals(uToCheck.getUserID(), is(uID));
		assertEquals(uToCheck.getFirstName(), user.getFirstName());
		assertEquals(uToCheck.getLastName(), user.getLastName());
		assertEquals(uToCheck.getEmail(), user.getEmail());
		assertEquals(uToCheck.getPassword(), user.getPassword()); 
		assertEquals(uToCheck.getPhoneNumber(), user.getPhoneNumber()); 
	
	}
	
	
	@Test
	public void testDFindUserByFirstNameAndLastName() throws SQLException {
		List<UserAccount> uToCheck = uDAO.findUserByFirstNameAndLastName("Ivanka Trump");
		
		assertThat(uToCheck, notNullValue());
		for (UserAccount userAccount : uToCheck) {
			assertEquals(userAccount.getFirstName(), user.getFirstName());
			assertEquals(userAccount.getLastName(), user.getLastName());
		}
		
	}
	
	
	@Test
	public void testEUpdateUserInfo() throws SQLException, ClassNotFoundException, IOException {

		uToCheck = uDAO.findUserByID(uID);
		System.out.println("testUpdateUserInfo "+uID);
//		isUpDated = uDAO.updateUserInfo(uToCheck, uID);

		assertThat(uToCheck.getFirstName(), is("Ivankas"));
		assertThat(uToCheck.getLastName(), is("Trumps"));
		assertThat(uToCheck.getEmail(), is("iva.trump@gmail.com"));
		assertThat(uToCheck.getPassword(), is("imarischgirl666"));
		assertThat(uToCheck.getPhoneNumber(), is("3333456786")); 

		assertEquals(isUpDated, 1);
	
	}
	
	@Test
	public void testFRemoveUser() throws SQLException  {
		UserAccount ur =  uDAO.getUserByEmail("ivanka.trump@gmail.com", "imarischgirl666");
		   System.out.println("ur.getUserID() "+ur.getUserID());
	   if(ur.getUserID() < 1) {
		uID = uDAO.registerUser(user);
	   System.out.println(uID);
	      uDAO.removeUser(uID);
	   }
	   else {
		   System.out.println("ur.getUserID() "+ur.getUserID());
		   uDAO.removeUser(ur.getUserID());
	   }
	}
	
	
}	
