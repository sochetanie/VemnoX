package springwork.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UserAccount {
	
	private int userID; 
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phoneNumber;
	private int pictureID;
	private double balance;
	private String joinDate;
	private Payment payment;
	private Transfer transfer;
	

	public UserAccount(int userID, String firstName, String lastName, String email, String password, String phoneNumber,
			int pictureID, double balance, String joinDate, Payment payment, Transfer transfer) {
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.pictureID = pictureID;
		this.balance = balance;
		this.joinDate = joinDate;
		this.payment = payment;
		this.transfer = transfer;
	}

	public UserAccount() { }

	public UserAccount(int userID, String firstName, String lastName, String joinDate) {
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.joinDate = joinDate;
	}

	public UserAccount(String firstName, String lastName, String email, String password, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getPictureID() {
		return pictureID;
	}

	public void setPictureID(int pictureID) {
		this.pictureID = pictureID;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getJoinDate() {
		joinDate = joinDate.split(" ")[0];
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH);
		LocalDate ld = LocalDate.parse(joinDate, dtf);
		joinDate = dtf2.format(ld);
		
		return joinDate; 
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Transfer getTransfer() {
		return transfer;
	}

	public void setTransfer(Transfer transfer) {
		this.transfer = transfer;
	}
	
	

}



