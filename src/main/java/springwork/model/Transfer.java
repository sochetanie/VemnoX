package springwork.model;

public class Transfer {
	
	private int transferID;
	private int userID; 
	private double transferAmount;
	private int bankAccountID;
	
	
	public Transfer(int transferID, int userID, double transferAmount, int bankAccountID) {
		this.transferID = transferID;
		this.userID = userID;
		this.transferAmount = transferAmount;
		this.bankAccountID = bankAccountID;
	}
	
	
	public Transfer(int userID, double transferAmount, int bankAccountID) {
		this.userID = userID;
		this.transferAmount = transferAmount;
		this.bankAccountID = bankAccountID;
	}

	public Transfer() { }

	public int getTransferID() {
		return transferID;
	}

	public void setTransferID(int transferID) {
		this.transferID = transferID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public double getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}

	public int getBankAccountID() {
		return bankAccountID;
	}

	public void setBankAccountID(int paymentOptionID) {
		this.bankAccountID = bankAccountID;
	}
	
	
}




