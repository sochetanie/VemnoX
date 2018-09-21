package springwork.daoi;

import java.sql.SQLException;

import springwork.model.Transfer;

public interface TransferDAOi {
	public int addTransfer(Transfer transfer) throws SQLException;
}
