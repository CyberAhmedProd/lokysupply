package models;

import java.sql.Timestamp;

public class Bon {
	protected int id;
	protected String code;
	protected Client client;
	protected Timestamp date;
	public Bon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bon(String code, Client client, Timestamp date) {
		super();
		this.code = code;
		this.client = client;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
}
