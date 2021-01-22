package models;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Devis  extends Bon{

	private ArrayList<Ligne_devis> lignesDevis;

	public Devis() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Devis(String code, Client client, Timestamp date, ArrayList<Ligne_devis> lignesDevis) {
		super(code, client, date);
		this.lignesDevis = lignesDevis;
	}


	public ArrayList<Ligne_devis> getLignesDevis() {
		return lignesDevis;
	}


	public void setLignesDevis(ArrayList<Ligne_devis> lignesDevis) {
		this.lignesDevis = lignesDevis;
	}

	
	
	
	
}
