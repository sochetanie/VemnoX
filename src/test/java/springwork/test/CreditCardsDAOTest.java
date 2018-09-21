package springwork.test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import springwork.dao.*;
import springwork.model.*;

public class CreditCardsDAOTest {
	
	private CreditCardsDAO ccDAO;
	CreditCards cc, ccToCheck;
	int ccID, lastAddedCC;
	private List<CreditCards> allCreditCards = new ArrayList<CreditCards>();

	
	@Before
	public void setUp() {
		ccDAO = new CreditCardsDAO();
		cc = new CreditCards(505, "Kumar Roy", "1234123412341234", "11/19", "23456", "235", 24);
	}

	@Test
	public void addCreditCard() throws SQLException {
		ccID = ccDAO.addCreditCard(cc);
		allCreditCards = ccDAO.showALlCreditCards(24);
		lastAddedCC = allCreditCards.get(allCreditCards.size()-1).getCreditCardID();
		assertEquals(lastAddedCC, ccID);
    }
	
	
	 @Test
	    public void showALlCreditCards() throws SQLException {
		 ccDAO = new CreditCardsDAO();
		 allCreditCards = ccDAO.showALlCreditCards(20);
	     assertEquals(1,allCreditCards.size());
	    }
	 
	 @Test
	 	public void deleteCreditCard() throws SQLException {
		 allCreditCards = ccDAO.showALlCreditCards(24);
		 lastAddedCC = allCreditCards.get(allCreditCards.size()-1).getCreditCardID();
		 int id  = ccDAO.deleteCreditCard(lastAddedCC);
	     assertEquals(1,id);
	    }

}
