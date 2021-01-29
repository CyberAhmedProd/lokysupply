package dao;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;


import models.FactureClient;
import models.LigneBonLivraision;
import models.Product;

public interface FactureClientService {
	public int createFacture(int idClient, FactureClient bl);
	public ArrayList<LigneBonLivraision> getAllLigneBonLivraison(int idBonLivraison);
	public Boolean valideFacture(FactureClient bonLivraison);
	public Boolean addProductLignes(DefaultTableModel model);
	public Boolean deleteProductLigne(Product p);
	public Boolean deleteFacture(int idBonLivraison);
	public Boolean printFacture(FactureClient bonLivraison);
	public FactureClient getFacture(int id);
	public ArrayList<FactureClient> getAllBonLivraison();
	public ArrayList<FactureClient> getAllBonLivraisonSeekByCode(String code);
}
