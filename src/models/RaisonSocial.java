package models;

public class RaisonSocial {
	private int id;
	private String nom;
	private String prenom;
	private String sexe;
	public RaisonSocial(String nom, String prenom, String sexe) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
	}
	
	public RaisonSocial() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	
}
