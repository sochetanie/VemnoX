package springwork.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import springwork.dao.*;
import springwork.model.*;

public class BankAccountDAOTest {

	private BankAccountDAO baDAO;
	BankAccount ba, baToCheck;
	int baID, lastAddedBA;
	private List<BankAccount> allBankAccounts = new ArrayList<BankAccount>();

	
	@Before
	public void setUp() {
		baDAO = new BankAccountDAO();
		ba = new BankAccount(6666, "12345678", "1234123412341234", 20);
	}

	@Test
	public void addCreditCard() throws SQLException {
		baID = baDAO.addBankAccount(ba);
		allBankAccounts = baDAO.showBankAccounts(20);
		lastAddedBA = allBankAccounts.get(allBankAccounts.size()-1).getBankAccountID();
		assertEquals(lastAddedBA, baID);
    }
	
	
	 @Test
	    public void showALlCreditCards() throws SQLException {
		 baDAO = new BankAccountDAO();
		 allBankAccounts = baDAO.showBankAccounts(20);
	     assertEquals(1,allBankAccounts.size());
	    }
	 
	 @Test
	 	public void deleteCreditCard() throws SQLException {
		 allBankAccounts = baDAO.showBankAccounts(20);
		 lastAddedBA = allBankAccounts.get(allBankAccounts.size()-1).getBankAccountID();
		 System.out.println(lastAddedBA);
		 int id  = baDAO.deleteBankAccount(lastAddedBA);
		 System.out.println(id);
	     assertEquals(1,id);
	    }
	 

}
