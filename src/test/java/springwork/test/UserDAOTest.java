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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDAOTest {
	UserAccountDAO uDAO;
	UserAccount user, uToCheck;
	int generatedId;
	boolean willDelete;
	int isUpDated;
	
	@Before
	public void setUp() {
		uDAO = new UserAccountDAO();
		user = new UserAccount("Ivanka", "Trump", "ivanka.trump@gmail.com","imarichgirl666","3334567867");
	}

	@Test
	public void testASCreateUser() throws SQLException, ClassNotFoundException, IOException {
		if(generatedId!=0) 
			uDAO.removeUser(generatedId);
		generatedId = uDAO.registerUser(user);
		uToCheck = uDAO.findUserByID(generatedId);
		assertEquals(uToCheck.getFirstName(), user.getFirstName());
		assertEquals(uToCheck.getLastName(), user.getLastName());
		assertEquals(uToCheck.getEmail(), user.getEmail());
		assertEquals(uToCheck.getPassword(), user.getPassword()); 
		assertEquals(uToCheck.getPhoneNumber(), user.getPhoneNumber()); 
	}
    
	@Test
	public void testBGetUserByGmail() throws SQLException {
		if(generatedId==0) 
			generatedId = uDAO.registerUser(user);
		uToCheck = uDAO.getUserByEmail(user.getEmail(), user.getPassword());
		assertEquals(user.getEmail(), uToCheck.getEmail());
		assertEquals(user.getPassword(), uToCheck.getPassword());
	}

	@Test
	public void testCFindUserByID() throws SQLException {
		if(generatedId==0) 
			generatedId = uDAO.registerUser(user);
		uToCheck = uDAO.findUserByID(generatedId);
		
		assertEquals(uToCheck.getUserID(), is(generatedId));
		assertEquals(uToCheck.getFirstName(), user.getFirstName());
		assertEquals(uToCheck.getLastName(), user.getLastName());
		assertEquals(uToCheck.getEmail(), user.getEmail());
		assertEquals(uToCheck.getPassword(), user.getPassword()); 
		assertEquals(uToCheck.getPhoneNumber(), user.getPhoneNumber()); 
	
	}
	
	
	@Test
	public void testDFindUserByFirstNameAndLastName() throws SQLException {
		if(generatedId==0) 
			generatedId = uDAO.registerUser(user);
		List<UserAccount> uToCheck = uDAO.findUserByFirstNameAndLastName("Ivanka Trump");
		
		assertThat(uToCheck, notNullValue());
		for (UserAccount userAccount : uToCheck) {
			assertEquals(userAccount.getFirstName(), user.getFirstName());
			assertEquals(userAccount.getLastName(), user.getLastName());
		}
		
	}
	
	
	@Test
	public void testEUpdateUserInfo() throws SQLException, ClassNotFoundException, IOException {
		if(generatedId==0) 
			generatedId = uDAO.registerUser(user);
	
		uToCheck = uDAO.findUserByID(generatedId);
		System.out.println("testUpdateUserInfo "+generatedId);
		isUpDated = uDAO.updateUserInfo(uToCheck, generatedId);

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
		generatedId = uDAO.registerUser(user);
	   System.out.println(generatedId);
	      uDAO.removeUser(generatedId);
	   }
	   else {
		   System.out.println("ur.getUserID() "+ur.getUserID());
		   uDAO.removeUser(ur.getUserID());
	   }
	}
	
	
}	
