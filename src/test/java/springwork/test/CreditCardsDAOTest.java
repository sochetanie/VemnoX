package springwork.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import springwork.dao.*;
import springwork.model.*;

public class CreditCardsDAOTest {
	
	private CreditCardsDAO ccDAO;
	private List<CreditCards> aLlCreditCards = new ArrayList<CreditCards>();

	@Test
	public void addCreditCard() throws SQLException {
		ccDAO = new CreditCardsDAO();
		CreditCards creditCard = new CreditCards();
		creditCard.setAmountAvailable(505);
		creditCard.setCardHolder("Kumar Roy");
		creditCard.setCardNumber("1234123412341234");
		creditCard.setExpirationDate("11/19");
		creditCard.setZipCode("23456");
		creditCard.setCVV("235");
		creditCard.setUserID(24);
        int id = ccDAO.addCreditCard(creditCard);
        int result = creditCard.getCreditCardID();
        assertEquals(result,id);
    }
	
	
	 @Test
	    public void showALlCreditCards() throws SQLException {
		 ccDAO = new CreditCardsDAO();
		 aLlCreditCards = ccDAO.showALlCreditCards(20);
	     assertEquals(1,aLlCreditCards.size());
	    }




}
