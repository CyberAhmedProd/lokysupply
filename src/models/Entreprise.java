package models;

import java.util.ArrayList;

public class Entreprise {
	private int id;
	private String matricule;
	private String description;
	private int telFix;
	private int telMobile;
	private String email;
	private String webSite;
	private Adress adresse;
	private RaisonSocial raisonSocial;
	private TypeEntreprise type;
	private boolean tva_assuj;
	
	public Adress getAdresse() {
		return adresse;
	}
	public void setAdresse(Adress adresse) {
		this.adresse = adresse;
	}
	public ArrayList<CompteBancaire> getCompteBancaires() {
		return compteBancaires;
	}
	public void setCompteBancaires(ArrayList<CompteBancaire> compteBancaires) {
		this.compteBancaires = compteBancaires;
	}
	private ArrayList<CompteBancaire> compteBancaires;
	
	public Entreprise(String matricule, String description, int telFix, int telMobile, String email, String webSite,
			Adress adresse, ArrayList<CompteBancaire> compteBancaires) {
		super();
		this.matricule = matricule;
		this.description = description;
		this.telFix = telFix;
		this.telMobile = telMobile;
		this.email = email;
		this.webSite = webSite;
		this.adresse = adresse;
		this.compteBancaires = compteBancaires;
	}
	
	
	public Entreprise(String matricule, String description, int telFix, int telMobile, String email, String webSite,
			Adress adresse, RaisonSocial raisonSocial, TypeEntreprise type, ArrayList<CompteBancaire> compteBancaires) {
		super();
		this.matricule = matricule;
		this.description = description;
		this.telFix = telFix;
		this.telMobile = telMobile;
		this.email = email;
		this.webSite = webSite;
		this.adresse = adresse;
		this.raisonSocial = raisonSocial;
		this.type = type;
		this.compteBancaires = compteBancaires;
	}
	
	
	public Entreprise(String matricule, String description, int telFix, int telMobile, String email, String webSite,
			Adress adresse, RaisonSocial raisonSocial, TypeEntreprise type, boolean tva_assuj,
			ArrayList<CompteBancaire> compteBancaires) {
		super();
		this.matricule = matricule;
		this.description = description;
		this.telFix = telFix;
		this.telMobile = telMobile;
		this.email = email;
		this.webSite = webSite;
		this.adresse = adresse;
		this.raisonSocial = raisonSocial;
		this.type = type;
		this.tva_assuj = tva_assuj;
		this.compteBancaires = compteBancaires;
	}
	public Entreprise() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTelFix() {
		return telFix;
	}
	public void setTelFix(int telFix) {
		this.telFix = telFix;
	}
	public int getTelMobile() {
		return telMobile;
	}
	public void setTelMobile(int telMobile) {
		this.telMobile = telMobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public RaisonSocial getRaisonSocial() {
		return raisonSocial;
	}
	public void setRaisonSocial(RaisonSocial raisonSocial) {
		this.raisonSocial = raisonSocial;
	}
	public TypeEntreprise getType() {
		return type;
	}
	public void setType(TypeEntreprise type) {
		this.type = type;
	}
	public boolean isTva_assuj() {
		return tva_assuj;
	}
	public void setTva_assuj(boolean tva_assuj) {
		this.tva_assuj = tva_assuj;
	}
	
	
}
