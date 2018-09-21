package springwork.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import springwork.dao.*;
import springwork.model.*;

public class BankAccountTest {

	private BankAccountDAO baDAO;
	private List<BankAccount> allBankAccounts = new ArrayList<BankAccount>();

	@Test
	public void addCreditCard() throws SQLException {
		baDAO = new BankAccountDAO();
		BankAccount bankAccount = new BankAccount();
		bankAccount.setAmountAvailable(6666);
		bankAccount.setRoutingNumber("12345678");
		bankAccount.setAccountNumber("345678345678");
		bankAccount.setUserID(20);
        int id = baDAO.addBankAccount(bankAccount);
        assertEquals(1,id);
    }
	
	
	 @Test
	    public void showBankAccounts() throws SQLException {
		 baDAO = new BankAccountDAO();
		 allBankAccounts = baDAO.showBankAccounts(24);
	     assertEquals(2,allBankAccounts.size());
	    }
	 
	 @Test
	 	public void deleteBankAccount() throws SQLException {
		 baDAO = new BankAccountDAO();
		 int id  = baDAO.deleteBankAccount(6220);
	     assertEquals(1,id);
	    }
	 

}
