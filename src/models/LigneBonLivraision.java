package models;

public class LigneBonLivraision extends Ligne_devis{

	public LigneBonLivraision() {
		super();
	
	}

	public LigneBonLivraision(int quantity, Double totalHt, Double totalTva, Product products) {
		super(quantity, totalHt, totalTva, products);
		
	}
	
}
