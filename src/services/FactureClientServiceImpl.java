package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

import dao.FactureClientService;
import db.ConnexionDB;
import models.Adress;
import models.BonLivraison;
import models.FactureClient;
import models.LigneBonLivraision;
import models.Product;
import models.UnitOfMeasure;

public class FactureClientServiceImpl implements FactureClientService{
	Statement st = null;
	Connection cn = null;
	@Override
	public int createFacture(int idClient,FactureClient bl) {
		int idBon=0;
		cn = ConnexionDB.getConnexion();
		 PreparedStatement ps;
		 String sqlDevis ="INSERT INTO `facture_client`(`code`, `mode_payment`, `client`) VALUES (?,?,?,?)";
	
		 
		 try {
				ps=(PreparedStatement) cn.prepareStatement(sqlDevis, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1,null);
				ps.setString(2,bl.getModePayement().toString());
				ps.setDouble(3,idClient);
				
			
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				idBon = rs.getInt(1);
				//System.out.println("ok");
				ps.close();
				
			} catch (SQLException e2) {
				
				System.out.println("create facture");
				return 0;
			}
		return idBon;
	}

	@Override
	public ArrayList<LigneBonLivraision> getAllLigneBonLivraison(int idBonLivraison) {
		cn = ConnexionDB.getConnexion();
		
		ArrayList<LigneBonLivraision> listeLigneBonLivraision = new ArrayList<LigneBonLivraision>();
		String sql = "SELECT * FROM lignes_facture LEFT JOIN produit on produit.id = lignes_facture.produit LEFT JOIN facture_client on facture_client.id = lignes_facture.facture_client WHERE lignes_facture.facture_client="+idBonLivraison;
		try {
			
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
			
				LigneBonLivraision ligneBonLivraision = new LigneBonLivraision();
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
				
				
				// ligneBonLivraision info
				/// ------------
				ligneBonLivraision.setId(rs.getInt("lignes_bon_livraison.id"));
				ligneBonLivraision.setQuantity(rs.getInt("lignes_bon_livraison.quantity"));
				ligneBonLivraision.setTotalHt(rs.getDouble("lignes_bon_livraison.totalHt"));
				ligneBonLivraision.setTotalTva(rs.getDouble("lignes_bon_livraison.totalTva"));
				ligneBonLivraision.setProducts(product);
				
				listeLigneBonLivraision.add(ligneBonLivraision);
				
				
			}
			rs.close();
			st.close();
	
			return listeLigneBonLivraision;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean valideFacture(FactureClient bonLivraison) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean addProductLignes(DefaultTableModel model) {
		 Boolean valid = false;
		 PreparedStatement ps;
		 cn = ConnexionDB.getConnexion();
		 for (int count = 0; count < model.getRowCount(); count++){
		
		 String sqlLigneDevis ="INSERT INTO `lignes_facture`(`quantity`, `totalHt`, `totalTva`, `produit`, `bon_livraison`) VALUES (?,?,?,?,?)";
					
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
				
				System.out.println("error  addProduct ligne Bon livraison");
				
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
	public Boolean deleteFacture(int idBonLivraison) {
		cn = ConnexionDB.getConnexion();
		  PreparedStatement ps;
		String sqlDevisDelete ="DELETE FROM `facture_client` WHERE id = ?";
				
		try {
			ps=(PreparedStatement) cn.prepareStatement(sqlDevisDelete);
			ps.setInt(1,idBonLivraison);
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

	@Override
	public Boolean printFacture(FactureClient bonLivraison) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FactureClient getFacture(int id) {
		cn = ConnexionDB.getConnexion();
		
		FactureClient bonLivraison = new FactureClient();
		
		ClientServiceImpl clientServiceImpl = new ClientServiceImpl();
		String sql = "SELECT * FROM `facture_client` LEFT JOIN adress on adress.id = bon_livraison.address where bon_livraison.id="+id;
		
		try {
			
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			// info address
			Adress address = new Adress();
			address.setId(rs.getInt("adress.id"));
			address.setNumRue(rs.getInt("numRue"));
			address.setLibelleRue(rs.getString("libelleRue"));
			address.setNomVille(rs.getString("nomVille"));
			address.setCodePostale(rs.getInt("codepostal"));
			address.setGouvernat(rs.getString("gouvernorat"));
			address.setPays(rs.getString("pays"));
			
			/// --------------------------------------------------
			// ------------------info Bon livraison
			//-----------------------------------------------
			bonLivraison.setId(rs.getInt("bon_livraison.id"));
			bonLivraison.setCode(rs.getString("bon_livraison.code"));
		
		
			rs.close();
			st.close();
	
			return bonLivraison;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<FactureClient> getAllBonLivraison() {
		cn = ConnexionDB.getConnexion();
		ArrayList<FactureClient> listeBonLivraison = new ArrayList<FactureClient>();
		
	
		ClientServiceImpl clientServiceImpl = new ClientServiceImpl();
		
		String sql = "SELECT * FROM `bon_livraison` LEFT JOIN adress on adress.id = bon_livraison.address";
		
		try {
			
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
			    // info address
				
				FactureClient bonLivraison = new FactureClient();
				/// --------------------------------------------------
				// ------------------info bon livraison
				//-----------------------------------------------
				bonLivraison.setId(rs.getInt("bon_livraison.id"));
				bonLivraison.setCode(rs.getString("bon_livraison.code"));
		
				listeBonLivraison.add(bonLivraison);
			}
			rs.close();
			st.close();
	
			return listeBonLivraison;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<FactureClient> getAllBonLivraisonSeekByCode(String code) {
		cn = ConnexionDB.getConnexion();
		ArrayList<FactureClient> listeBonLivraison = new ArrayList<FactureClient>();
		
	
		ClientServiceImpl clientServiceImpl = new ClientServiceImpl();
		
		String sql = "SELECT * FROM `bon_livraison` LEFT JOIN adress on adress.id = bon_livraison.address where bon_livraison.code like '%"+code+"%'";
		
		try {
			
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
			    // info address
				Adress address = new Adress();
				address.setId(rs.getInt("adress.id"));
				address.setNumRue(rs.getInt("numRue"));
				address.setLibelleRue(rs.getString("libelleRue"));
				address.setNomVille(rs.getString("nomVille"));
				address.setCodePostale(rs.getInt("codepostal"));
				address.setGouvernat(rs.getString("gouvernorat"));
				address.setPays(rs.getString("pays"));
				FactureClient bonLivraison = new FactureClient();
				/// --------------------------------------------------
				// ------------------info bon livraison
				//-----------------------------------------------
				bonLivraison.setId(rs.getInt("bon_livraison.id"));
				bonLivraison.setCode(rs.getString("bon_livraison.code"));
			
				listeBonLivraison.add(bonLivraison);
			}
			rs.close();
			st.close();
	
			return listeBonLivraison;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
