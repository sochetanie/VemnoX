package springwork.daoi;

import java.sql.SQLException;
import springwork.model.BankAccount;

public interface BankAccountDAOi {
	public int addBankAccount(BankAccount bankAccount) throws SQLException;
	public BankAccount showALlBankAccounts() throws SQLException; 
	public int deleteBankAccount(int bankAccountID) throws SQLException;
}
