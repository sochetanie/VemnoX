package springwork.daoi;

import java.sql.SQLException;
import springwork.model.Payment;


public interface PaymentDAOi {
	
	int addPayment(Payment payment) throws SQLException;
	Payment showStatement(String dateTime) throws SQLException;
	Payment showALlPayments() throws SQLException; 

}
