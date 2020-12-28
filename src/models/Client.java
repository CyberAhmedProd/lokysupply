package models;

import java.util.ArrayList;

public class Client extends Entreprise{

	public Client(String matricule, String description, int telFix, int telMobile, String email, String webSite,
			Adress adresse, RaisonSocial raisonSocial, TypeEntreprise type, ArrayList<CompteBancaire> compteBancaires) {
		super(matricule, description, telFix, telMobile, email, webSite, adresse, raisonSocial, type, compteBancaires);
		// TODO Auto-generated constructor stub
	}

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	

}
