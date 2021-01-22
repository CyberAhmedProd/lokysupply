package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

import dao.DevisService;
import db.ConnexionDB;
import models.Client;
import models.Devis;
import models.Fournisseur;
import models.Ligne_devis;
import models.Product;
import models.ProduitFamille;
import models.RaisonSocial;
import models.UnitOfMeasure;

public class DevisServiceImpl implements DevisService{
	Statement st = null;
	Connection cn = null;
	@Override
	public Boolean valideDevis(Devis devis) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int createDevis(int IdClient) {
		int idDevis=0;
		cn = ConnexionDB.getConnexion();
		 PreparedStatement ps;
		 String sqlDevis ="INSERT INTO `devis`(`code`, `client`) VALUES (?,?)";
		 try {
				ps=(PreparedStatement) cn.prepareStatement(sqlDevis, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1,null);
				ps.setDouble(2,IdClient);
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				idDevis = rs.getInt(1);
				//System.out.println("ok");
				ps.close();
				
			} catch (SQLException e2) {
				
				System.out.println("adress");
				return 0;
			}
		return idDevis;
	}

	@Override
	public Boolean addProductLignes(DefaultTableModel model) {
		 Boolean valid = false;
		 PreparedStatement ps;
		 cn = ConnexionDB.getConnexion();
		 for (int count = 0; count < model.getRowCount(); count++){
		
		 String sqlLigneDevis ="INSERT INTO `lignes_devis`(`quantity`, `totalHt`, `totalTva`, `produit`, `devis`) VALUES (?,?,?,?,?)";
					
			try {
				ps=(PreparedStatement) cn.prepareStatement(sqlLigneDevis, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1,Integer.parseInt(model.getValueAt(count, 4).toString()));
				ps.setDouble(2,Double.parseDouble(model.getValueAt(count, 2).toString()));
				ps.setDouble(3,Double.parseDouble(model.getValueAt(count, 3).toString()));
				ps.setInt(4,Integer.parseInt(model.getValueAt(count, 6).toString()));
				ps.setInt(5,Integer.parseInt(model.getValueAt(count, 5).toString()));
				ps.executeUpdate();
				ps.close();
				valid = true;
			} catch (SQLException e2) {
				
				System.out.println("ligne");
				
			}
		 }
		return valid;
		
	
	}

	@Override
	public Boolean deleteProductLigne(Product p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean printDevis(Devis devis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Devis getDevis(int id) {
		
		cn = ConnexionDB.getConnexion();
		
		Devis devis = new Devis();
		
		ClientServiceImpl clientServiceImpl = new ClientServiceImpl();
		String sql = "SELECT * FROM `devis` where id="+id;
		
		try {
			
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			/// --------------------------------------------------
			// ------------------info Devis
			//-----------------------------------------------
			devis.setId(rs.getInt("devis.id"));
			devis.setCode(rs.getString("devis.code"));
			devis.setClient(clientServiceImpl.getClient(rs.getInt("devis.client")));
			devis.setDate(rs.getTimestamp("devis.date"));
			devis.setLignesDevis(this.getAllLigneDevis(devis.getId()));
		
			rs.close();
			st.close();
	
			return devis;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<Devis> getAllDevis() {
		
		cn = ConnexionDB.getConnexion();
		ArrayList<Devis> listeDevis = new ArrayList<Devis>();
		
	
		ClientServiceImpl clientServiceImpl = new ClientServiceImpl();
		
		String sql = "SELECT * FROM `devis`";
		
		try {
			
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
			Devis devis = new Devis();
			/// --------------------------------------------------
			// ------------------info Devis
			//-----------------------------------------------
			devis.setId(rs.getInt("devis.id"));
			devis.setCode(rs.getString("devis.code"));
			devis.setClient(clientServiceImpl.getClient(rs.getInt("devis.client")));
			devis.setDate(rs.getTimestamp("devis.date"));
			devis.setLignesDevis(this.getAllLigneDevis(devis.getId()));
			listeDevis.add(devis);
			}
			rs.close();
			st.close();
	
			return listeDevis;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<Ligne_devis> getAllLigneDevis(int idDevis) {
		cn = ConnexionDB.getConnexion();
		
		ArrayList<Ligne_devis> listeLineDevis = new ArrayList<Ligne_devis>();
		String sql = "SELECT * FROM lignes_devis LEFT JOIN produit on produit.id = lignes_devis.produit LEFT JOIN devis on devis.id = lignes_devis.devis WHERE lignes_devis.devis="+idDevis;
		try {
			
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
			
				Ligne_devis ligneDevis = new Ligne_devis();
				Product product = new Product();
			
				/// --------------------------------------------------
				// ------------------info Product
				//-----------------------------------------------
				product.setId(rs.getInt("produit.id"));
				product.setRef(rs.getString("produit.ref"));
				product.setDesignation(rs.getString("produit.designation"));
				product.setDesignation(rs.getString("produit.designation"));
				product.setDesignation(rs.getString("produit.designation"));
				switch(rs.getString("produit.unit")) {
				case "KILOGRAMME" : product.setUnit(UnitOfMeasure.KILOGRAMME);
				case "GRAMME" : product.setUnit(UnitOfMeasure.GRAMME);
				case "LITRE" : product.setUnit(UnitOfMeasure.LITRE);
				case "LOT" : product.setUnit(UnitOfMeasure.LOT);
				case "PIECE" : product.setUnit(UnitOfMeasure.PIECE);
				case "METRE" : product.setUnit(UnitOfMeasure.METRE);
				case "MILLIMETRE" : product.setUnit(UnitOfMeasure.MILLIMETRE);
				case "MILLIGRAMME" : product.setUnit(UnitOfMeasure.MILLIGRAMME);
				}
				
				product.setUnitPriceHt(rs.getDouble("produit.unit_price"));
				product.setUnitPriceTva(rs.getDouble("produit.unit_price_tva"));
				product.setMinStock(rs.getInt("produit.min_stock"));
				product.setStock(rs.getInt("produit.stock"));
				
				
				//ligne devis info
				/// ------------
				ligneDevis.setId(rs.getInt("lignes_devis.id"));
				ligneDevis.setQuantity(rs.getInt("lignes_devis.quantity"));
				ligneDevis.setTotalHt(rs.getDouble("lignes_devis.totalHt"));
				ligneDevis.setTotalTva(rs.getDouble("lignes_devis.totalTva"));
				ligneDevis.setProducts(product);
				
				listeLineDevis.add(ligneDevis);
				
				
			}
			rs.close();
			st.close();
	
			return listeLineDevis;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean deleteDevis(int idDevis) {
		cn = ConnexionDB.getConnexion();
		  PreparedStatement ps;
		String sqlDevisDelete ="DELETE FROM `devis` WHERE id = ?";
				
		try {
			ps=(PreparedStatement) cn.prepareStatement(sqlDevisDelete);
			ps.setInt(1,idDevis);
			int test = ps.executeUpdate();
			ps.close();
			if(test>0) {
				return true;
			}
			else {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
			
		
		}
	}

	
	
	
	
	
}
