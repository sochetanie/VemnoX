package springwork.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Payment {
	
	private int paymentID;
	private int fromUserID;
	private int toUserID;
	private double paymentAmount;
	private String dateTime;
	private String description;
	private int paymentOptionID;


	public Payment(int paymentID, int fromUserID, int toUserID, double paymentAmount, String dateTime,
			String description, int paymentOptionID) {
		this.paymentID = paymentID;
		this.fromUserID = fromUserID;
		this.toUserID = toUserID;
		this.paymentAmount = paymentAmount;
		this.dateTime = dateTime;
		this.description = description;
		this.paymentOptionID= paymentOptionID;
	}


	public Payment(int fromUserID, int toUserID, double paymentAmount, String description, int paymentOptionID) {
		this.fromUserID = fromUserID;
		this.toUserID = toUserID;
		this.paymentAmount = paymentAmount;
		this.description = description;
		this.paymentOptionID = paymentOptionID;
	}

	public Payment() { }


	public int getPaymentID() {
		return paymentID;
	}


	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}


	public int getFromUserID() {
		return fromUserID;
	}


	public void setFromUserID(int fromUserID) {
		this.fromUserID = fromUserID;
	}


	public int getToUserID() {
		return toUserID;
	}


	public void setToUserID(int toUserID) {
		this.toUserID = toUserID;
	}


	public double getPaymentAmount() {
		return paymentAmount;
	}


	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}


	public String getDateTime() {
		dateTime = dateTime.split(" ")[0];
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		LocalDate ld = LocalDate.parse(dateTime, dtf);
		dateTime = dtf.format(ld);
		return dateTime;
	}


	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getPaymentOptionID() {
		return paymentOptionID;
	}


	public void setPaymentOptionID(int paymentOptionID) {
		this.paymentOptionID = paymentOptionID;
	}


	
	

}



