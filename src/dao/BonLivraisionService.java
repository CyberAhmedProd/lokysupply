package dao;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import models.Adress;
import models.BonLivraison;
import models.LigneBonLivraision;
import models.Product;

public interface BonLivraisionService {
	public int createBonLivraison(int idClient, BonLivraison bl);
	public ArrayList<LigneBonLivraision> getAllLigneBonLivraison(int idBonLivraison);
	public Boolean valideBonLivraison(BonLivraison bonLivraison);
	public Boolean addProductLignes(DefaultTableModel model);
	public Boolean deleteProductLigne(Product p);
	public Boolean deleteBonLivraison(int idBonLivraison);
	public Boolean printBonLivraison(BonLivraison bonLivraison);
	public BonLivraison getBonLivraison(int id);
	public ArrayList<BonLivraison> getAllBonLivraison();
	public ArrayList<BonLivraison> getAllBonLivraisonSeekByCode(String code);
}
