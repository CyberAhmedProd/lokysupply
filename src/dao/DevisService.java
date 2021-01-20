package dao;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import models.Client;
import models.Devis;
import models.Ligne_devis;
import models.Product;

public interface DevisService {
	public int createDevis(int idClient);
	public ArrayList<Ligne_devis> getAllLigneDevis(int idDevis);
	public Boolean valideDevis(Devis devis);
	public Boolean addProductLignes(DefaultTableModel model);
	public Boolean deleteProductLigne(Product p);
	public Boolean printDevis(Devis devis);
	public Devis getDevis(Devis devis);
	public ArrayList<Devis> getAllDevis();
}
