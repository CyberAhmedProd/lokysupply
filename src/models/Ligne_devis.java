package models;

import java.util.ArrayList;

public class Ligne_devis {
	private int id ;
	private int quantity;
	private Double totalHt;
	private Double totalTva;
	private Product products;
	public Ligne_devis() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ligne_devis(int quantity, Double totalHt, Double totalTva, Product products) {
		super();
		this.quantity = quantity;
		this.totalHt = totalHt;
		this.totalTva = totalTva;
		this.products = products;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Double getTotalHt() {
		return totalHt;
	}
	public void setTotalHt(Double totalHt) {
		this.totalHt = totalHt;
	}
	public Double getTotalTva() {
		return totalTva;
	}
	public void setTotalTva(Double totalTva) {
		this.totalTva = totalTva;
	}
	public Product getProducts() {
		return products;
	}
	public void setProducts(Product products) {
		this.products = products;
	}

	

}
