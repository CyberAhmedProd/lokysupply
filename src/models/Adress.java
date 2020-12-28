package models;

public class Adress {
	private int id;
	private int numRue;
	private String libelleRue;
	private String nomVille;
	private int codePostale;
	private String gouvernat;
	private String pays;
	public Adress(int numRue, String libelleRue, String nomVille, int codePostale, String gouvernat, String pays) {
		super();
		this.numRue = numRue;
		this.libelleRue = libelleRue;
		this.nomVille = nomVille;
		this.codePostale = codePostale;
		this.gouvernat = gouvernat;
		this.pays = pays;
	}
	
	public Adress() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumRue() {
		return numRue;
	}
	public void setNumRue(int numRue) {
		this.numRue = numRue;
	}
	public String getLibelleRue() {
		return libelleRue;
	}
	public void setLibelleRue(String libelleRue) {
		this.libelleRue = libelleRue;
	}
	public String getNomVille() {
		return nomVille;
	}
	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}
	public int getCodePostale() {
		return codePostale;
	}
	public void setCodePostale(int codePostale) {
		this.codePostale = codePostale;
	}
	public String getGouvernat() {
		return gouvernat;
	}
	public void setGouvernat(String gouvernat) {
		this.gouvernat = gouvernat;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	
}
