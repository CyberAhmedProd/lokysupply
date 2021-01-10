package models;

public class ProduitFamille {
	private int id;
	private String nom;
	private String type;
	public ProduitFamille(String nom, String type) {
		super();
		this.nom = nom;
		this.type = type;
	}
	
	public ProduitFamille() {
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
