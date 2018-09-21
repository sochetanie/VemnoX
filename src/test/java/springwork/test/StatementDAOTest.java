package springwork.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import springwork.dao.*;
import springwork.model.*;

public class StatementDAOTest {

	private StatementDAO sDAO;
	private List<Statement> showAllStatement = new ArrayList<Statement>();
	private List<Statement> showStatement = new ArrayList<Statement>();


	@Test
	public void showAllStatement() throws SQLException {
		 sDAO = new StatementDAO();
		 showAllStatement = sDAO.showAllStatement(196);
	     assertEquals(1,showAllStatement.size());
	    }
	
	
	 @Test
	    public void showStatement() throws SQLException {
		 sDAO = new StatementDAO();
		 showStatement = sDAO.showStatement(24, "09-17-18", "09-19-18"); 
		 // going to show for two days 17 and 19 
		 // (in code i'm adding one day to the toDate because oracle show only until start of the day 09-18-18 00:00)
	     assertEquals(6,showStatement.size());
	    }
	 

}
