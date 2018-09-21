package springwork.model;

import java.util.Date;

public class Statement {
	private int id;
	private int userid;
	private Double amount;
	private Date datetime;
	private String type;
	private String status;
	   
	   
	public Statement(int id, int userid, Double amount, Date datetime, String type, String status) {
		this.id = id;
		this.userid = userid;
		this.amount = amount;
		this.datetime = datetime;
		this.type = type;
		this.status = status;
	}


	public Statement() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public Date getDatetime() {
		return datetime;
	}


	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	   

}
