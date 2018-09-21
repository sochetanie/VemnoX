package springwork.model;

public class Comments {
	
	private int commentID;
	private int paymentID;
	private int userID;
	
	
	public Comments(int commentID, int paymentID, int userID) {
		this.commentID = commentID;
		this.paymentID = paymentID;
		this.userID = userID;
	}

	public Comments() { }

	public int getCommentID() {
		return commentID;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	} 
	


}



