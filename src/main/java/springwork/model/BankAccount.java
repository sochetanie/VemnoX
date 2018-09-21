package springwork.model;

public class BankAccount {
	
	private double amountAvailable;
	private String routingNumber;
	private String accountNumber;
	private int userID;
	private int bankAccountID;

	public BankAccount() {
	}

	public BankAccount(double amountAvailable, String routingNumber, String cccountNumber, int userID,
			int bankAccountID) {
		this.amountAvailable = amountAvailable;
		this.routingNumber = routingNumber;
		this.accountNumber = cccountNumber;
		this.userID = userID;
		this.bankAccountID = bankAccountID;
	}
	
	
	public BankAccount(double amountAvailable, String routingNumber, String accountNumber, int userID) {
		this.amountAvailable = amountAvailable;
		this.routingNumber = routingNumber;
		this.accountNumber = accountNumber;
		this.userID = userID;
	}

	public double getAmountAvailable() {
		return amountAvailable;
	}

	public void setAmountAvailable(double amountAvailable) {
		this.amountAvailable = amountAvailable;
	}

	public String getRoutingNumber() {
		return routingNumber;
	}

	public void setRoutingNumber(String routingNumber) {
		this.routingNumber = routingNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getBankAccountID() {
		return bankAccountID;
	}

	public void setBankAccountID(int bankAccountID) {
		this.bankAccountID = bankAccountID;
	}

	
}


