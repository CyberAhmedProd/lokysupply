package models;

public class Product {
	private int id;
	private String ref;
	private String designation;
	private UnitOfMeasure unit;
	private ProduitFamille famille;
	private int stock;
	private int minStock;
	private double unitPriceHt;
	private double unitPriceTva;
	private Fournisseur fournisseur;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(String ref, String designation, UnitOfMeasure unit, ProduitFamille famille, int stock, int minStock,
			double unitPriceHt, double unitPriceTva, Fournisseur fournisseur) {
		super();
		this.ref = ref;
		this.designation = designation;
		this.unit = unit;
		this.famille = famille;
		this.stock = stock;
		this.minStock = minStock;
		this.unitPriceHt = unitPriceHt;
		this.unitPriceTva = unitPriceTva;
		this.fournisseur = fournisseur;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public UnitOfMeasure getUnit() {
		return unit;
	}
	public void setUnit(UnitOfMeasure unit) {
		this.unit = unit;
	}
	public ProduitFamille getFamille() {
		return famille;
	}
	public void setFamille(ProduitFamille famille) {
		this.famille = famille;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getMinStock() {
		return minStock;
	}
	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}
	public double getUnitPriceHt() {
		return unitPriceHt;
	}
	public void setUnitPriceHt(double unitPriceHt) {
		this.unitPriceHt = unitPriceHt;
	}
	public double getUnitPriceTva() {
		return unitPriceTva;
	}
	public void setUnitPriceTva(double unitPriceTva) {
		this.unitPriceTva = unitPriceTva;
	}
	public Fournisseur getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}
	
}
