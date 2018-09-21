package springwork.test;

import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.Test;
import springwork.dao.*;
import springwork.model.*;

public class TransferDAOTest {

	private TransferDAO tDAO = new TransferDAO();

	 @Test
	    public void addTransfer() throws SQLException {
	        Transfer transfer = new Transfer();
	        transfer.setUserID(24);
	        transfer.setTransferAmount(5.0);
	        transfer.setBankAccountID(5170);
	        int id = tDAO.addTransfer(transfer);
	        assertEquals(1,id);
	    }
	     

}
