package models;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Devis {
	private int id;
	private String code;
	private Client client;
	private Timestamp date;
	private ArrayList<Ligne_devis> lignesDevis;
	
	
	public Devis(String code, Client client, Timestamp date, ArrayList<Ligne_devis> lignesDevis) {
		super();
		this.code = code;
		this.client = client;
		this.date = date;
		this.lignesDevis = lignesDevis;
	}
	public Devis() {
		super();
		// TODO Auto-generated constructor stub
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
	public ArrayList<Ligne_devis> getLignesDevis() {
		return lignesDevis;
	}
	public void setLignesDevis(ArrayList<Ligne_devis> lignesDevis) {
		this.lignesDevis = lignesDevis;
	}
	
}
