package springwork.queries;

public class DbaQueries {
	
	public static class UserAccount {
		
		public final static String REGISTERUSER= "INSERT INTO useraccount (firstname,lastname,email,password,PhoneNumber,joindate) VALUES(?,?,?,?,?,sysdate)";
		
		public final static String GETUSERBYEMAIL = "SELECT * FROM UserAccount WHERE email=?";
		public final static String FINDUSERBYID = "SELECT * FROM UserAccount WHERE userID=?" ;
		
		public final static String SearchUsers = "select userID, firstName, lastName, joinDate from " + 
				"VIEW_SEARCH_RESULT where REGEXP_LIKE (foolname, ?)";
		
		public final static String DELETEUSER = "DELETE FROM UserAccount WHERE userID=?";
		public final static String UPDATEUSERINFO = "UPDATE UserAccount SET firstname=?, lastname=?, email=?,password=?, phoneNumber=?  WHERE userID=?";
	
		public final static String showALlPaymentsTransfers = "SELECT * FROM VIEW_STATEMENT WHERE userid=? and DATETIME BETWEEN TO_DATE(?, 'MM-DD-YY') AND TO_DATE(?, 'MM-DD-YY')";
		public final static String showALlStatements = "SELECT * FROM VIEW_STATEMENT WHERE userid=?";
	
	}
	
	
	public static class Payment {
		public final static String SHOWALLPAYMENTS = "Select * from Payment p " + 
				"join useraccount ua on p.fromuserid = ua.userid " + 
				"join useraccount ua2 on p.touserid = ua2.userid " + 
				"where (FromUserID=? or ToUserID=?) order by datetime desc";
		
		public final static String SHOWLATESTPAYMENST = "select * from payment where rownum<=10 order by datetime desc;";
		
	}
	
	
	public static class Transfer {
		public final static String SHOWALLTRANSFERS = "Select * from transfer where userid=? order by datetime desc";
	}
	


	public static class CreditCards {
		public final static String addCreditCard = "INSERT INTO creditcards (amountavailable, cardholder, cardnumber, expirationdate, zipcode, cvv, UserID) VALUES (?, ?, ?, to_date(?,'MM/YY'), ?, ?, ?)";
		public final static String DeleteCreditCard = "delete from creditcards where  creditCardID=?";
		public final static String ShowALlCreditCards = "select * from CREDITCARDS where UserID=?";
	}
	
	public static class BankAccounts {
		public final static String addBankAccount = "INSERT " 
				+"INTO BANKACCOUNTS (amountavailable, RoutingNumber, AccountNumber, UserID) " 
				+"VALUES (?, ?, ?, ?)";
		public final static String DeleteBankAccount = "DELETE FROM bankaccounts WHERE bankaccountid=?";
		public final static String ShowALlBankAccounts = "select * from BankAccounts where UserID=?";
	}
	
	
	
}
