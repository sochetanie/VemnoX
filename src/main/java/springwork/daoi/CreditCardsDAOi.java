package springwork.daoi;

import java.sql.SQLException;

import springwork.model.CreditCards;

public interface CreditCardsDAOi {
	
	public int addCreditCard(int paymentOptionID) throws SQLException;
	public CreditCards deleteCreditCardn(int paymentOptionID) throws SQLException;
	public CreditCards showALlCreditCard() throws SQLException;
	public int deletePaymentOpt(int creditCardID) throws SQLException;

}
