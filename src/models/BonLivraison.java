package models;

import java.sql.Timestamp;
import java.util.ArrayList;

public class BonLivraison extends Bon{

	private Adress adressLivraison;
	private String information;
	private ArrayList<LigneBonLivraision> lignesDevis;
	public BonLivraison() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BonLivraison(String code, Client client, Timestamp date, Adress adressLivraison, String information,
			ArrayList<LigneBonLivraision> lignesDevis) {
		super(code, client, date);
		this.adressLivraison = adressLivraison;
		this.information = information;
		this.lignesDevis = lignesDevis;
	}
	public Adress getAdressLivraison() {
		return adressLivraison;
	}
	public void setAdressLivraison(Adress adressLivraison) {
		this.adressLivraison = adressLivraison;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public ArrayList<LigneBonLivraision> getLignesDevis() {
		return lignesDevis;
	}
	public void setLignesDevis(ArrayList<LigneBonLivraision> lignesDevis) {
		this.lignesDevis = lignesDevis;
	}
	
	
	
}
