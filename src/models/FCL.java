package models;

import java.util.ArrayList;

public class FCL extends FactureClient{
	private ArrayList<LigneBonLivraision> listBonlivraison;
	private Adress adressLivraison;
	private Client client;
	public FCL() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FCL(String code, ModePayement modePayement, ArrayList<LigneBonLivraision> listBonlivraison,
			Adress adressLivraison, Client client) {
		super(code, modePayement);
		this.listBonlivraison = listBonlivraison;
		this.adressLivraison = adressLivraison;
		this.client = client;
	}
	public ArrayList<LigneBonLivraision> getListBonlivraison() {
		return listBonlivraison;
	}
	public void setListBonlivraison(ArrayList<LigneBonLivraision> listBonlivraison) {
		this.listBonlivraison = listBonlivraison;
	}
	public Adress getAdressLivraison() {
		return adressLivraison;
	}
	public void setAdressLivraison(Adress adressLivraison) {
		this.adressLivraison = adressLivraison;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
}
