package models;

public class CompteBancaire {
	private int id;
	private String nameBanque;
	private String agence;
	private int numRib;
	public CompteBancaire(String nameBanque, String agence, int numRib) {
		super();
		this.nameBanque = nameBanque;
		this.agence = agence;
		this.numRib = numRib;
	}
	
	public CompteBancaire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameBanque() {
		return nameBanque;
	}
	public void setNameBanque(String nameBanque) {
		this.nameBanque = nameBanque;
	}
	public String getAgence() {
		return agence;
	}
	public void setAgence(String agence) {
		this.agence = agence;
	}
	public int getNumRib() {
		return numRib;
	}
	public void setNumRib(int numRib) {
		this.numRib = numRib;
	};
	
	public boolean checkRib() {
		return false;
	}
	
}
