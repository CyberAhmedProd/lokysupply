package models;

import java.util.ArrayList;

public class FBL extends FactureClient{
	private ArrayList<BonLivraison> listBonlivraison;

	public FBL() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FBL(String code, ModePayement modePayement, ArrayList<BonLivraison> listBonlivraison) {
		super(code, modePayement);
		this.listBonlivraison = listBonlivraison;
	}

	public ArrayList<BonLivraison> getListBonlivraison() {
		return listBonlivraison;
	}

	public void setListBonlivraison(ArrayList<BonLivraison> listBonlivraison) {
		this.listBonlivraison = listBonlivraison;
	}
	
}
