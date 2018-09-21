package springwork.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

import org.junit.Test;

import springwork.dao.*;
import springwork.model.*;

public class PaymentDAOTest {
	
	private PaymentDAO pDAO = new PaymentDAO();
	private List<UserAccount> allPayments = new ArrayList<UserAccount>();

	 @Test
	    public void addPayment() throws SQLException {
	        Payment payment = new Payment();
	        payment.setFromUserID(24);
	        payment.setToUserID(2);
	        payment.setPaymentAmount(5.0);
	        payment.setPaymentOptionID(5170);
	        payment.setDescription(null);
	        int id = pDAO.addPayment(payment);
	        assertEquals(1,id);
	    }

	 
	 @Test
	    public void showALlPayments() throws SQLException {
		 pDAO = new PaymentDAO();
		 allPayments = pDAO.showALlPayments(196);
	     assertEquals(0,allPayments.size());
	    }

	 
}
