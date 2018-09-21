package springwork.model;

import java.time.*;
import java.time.format.*;
import java.util.*;

public class CreditCards {
	
	private double amountAvailable;
	private String cardHolder;
	private String cardNumber;
	private String expirationDate;
	private String zipCode;
	private String cVV;
	private int userID;
	private int creditCardID;
	
	
	public double getAmountAvailable() {
		return amountAvailable;
	}
	public void setAmountAvailable(double amountAvailable) {
		this.amountAvailable = amountAvailable;
	}
	public String getCardHolder() {
		return cardHolder;
	}
	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCVV() {
		return cVV;
	}
	public void setCVV(String cVV) {
		this.cVV = cVV;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getCreditCardID() {
		return creditCardID;
	}
	public void setCreditCardID(int creditCardID) {
		this.creditCardID = creditCardID;
	}
	
	public CreditCards() {
	}
	
	public CreditCards(double amountAvailable, String cardHolder, String cardNumber, String expirationDate,
			String zipCode, String cVV, int userID, int creditCardID) {
		this.amountAvailable = amountAvailable;
		this.cardHolder = cardHolder;
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.zipCode = zipCode;
		this.cVV = cVV;
		this.userID = userID;
		this.creditCardID = creditCardID;
	}
	
	
	public CreditCards(double amountAvailable, String cardHolder, String cardNumber, String expirationDate,
			String zipCode, String cVV, int userID) {
		this.amountAvailable = amountAvailable;
		this.cardHolder = cardHolder;
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.zipCode = zipCode;
		this.cVV = cVV;
		this.userID = userID;
	}


	

}




