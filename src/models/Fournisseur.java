package models;

import java.util.ArrayList;

public class Fournisseur extends Entreprise{

	public Fournisseur(String matricule, String description, int telFix, int telMobile, String email, String webSite,
			Adress adresse, RaisonSocial raisonSocial, TypeEntreprise type, ArrayList<CompteBancaire> compteBancaires) {
		super(matricule, description, telFix, telMobile, email, webSite, adresse, raisonSocial, type, compteBancaires);
		// TODO Auto-generated constructor stub
	}

	public Fournisseur() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	
}
	
