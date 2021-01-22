package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

import dao.BonLivraisionService;
import db.ConnexionDB;
import models.Adress;
import models.BonLivraison;
import models.Devis;
import models.LigneBonLivraision;
import models.Ligne_devis;
import models.Product;
import models.UnitOfMeasure;

public class BonLivraisionImpl implements BonLivraisionService{
	Statement st = null;
	Connection cn = null;
	@Override
	public int createBonLivraison(int idClient,BonLivraison bl) {
		int idBon=0;
		cn = ConnexionDB.getConnexion();
		 PreparedStatement ps;
		 String sqlDevis ="INSERT INTO `bon_livraison`(`code`, `client`, `address`, `information`) VALUES (?,?,?,?)";
		 String sqladdress ="INSERT INTO `adress`(`numRue`, `libelleRue`, `nomVille`, `codepostal`, `gouvernorat`, `pays`) VALUES (?,?,?,?,?,?)";
		 
		 try {
				ps=(PreparedStatement) cn.prepareStatement(sqladdress, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1,bl.getAdressLivraison().getNumRue());
				ps.setString(2,bl.getAdressLivraison().getLibelleRue());
				ps.setString(3,bl.getAdressLivraison().getNomVille());
				ps.setInt(4,bl.getAdressLivraison().getCodePostale());
				ps.setString(5,bl.getAdressLivraison().getGouvernat());
				ps.setString(6,bl.getAdressLivraison().getPays());
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				bl.getAdressLivraison().setId(rs.getInt(1));
				//System.out.println("ok");
				ps.close();
				
			} catch (SQLException e2) {
				
				System.out.println("adress");
				return 0;
			}
		 
		 
		 try {
				ps=(PreparedStatement) cn.prepareStatement(sqlDevis, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1,null);
				ps.setDouble(2,idClient);
				ps.setInt(3,bl.getAdressLivraison().getId());
				ps.setString(4,bl.getInformation());
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				idBon = rs.getInt(1);
				//System.out.println("ok");
				ps.close();
				
			} catch (SQLException e2) {
				
				System.out.println("bon livraision");
				return 0;
			}
		return idBon;
	}

	@Override
	public ArrayList<LigneBonLivraision> getAllLigneBonLivraison(int idBonLivraison) {
cn = ConnexionDB.getConnexion();
		
		ArrayList<LigneBonLivraision> listeLigneBonLivraision = new ArrayList<LigneBonLivraision>();
		String sql = "SELECT * FROM lignes_bon_livraison LEFT JOIN produit on produit.id = lignes_bon_livraison.produit LEFT JOIN bon_livraison on bon_livraison.id = lignes_bon_livraison.bon_livraison WHERE lignes_bon_livraison.bon_livraison="+idBonLivraison;
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
	public Boolean valideBonLivraison(BonLivraison bonLivraison) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean addProductLignes(DefaultTableModel model) {
		 Boolean valid = false;
		 PreparedStatement ps;
		 cn = ConnexionDB.getConnexion();
		 for (int count = 0; count < model.getRowCount(); count++){
		
		 String sqlLigneDevis ="INSERT INTO `lignes_bon_livraison`(`quantity`, `totalHt`, `totalTva`, `produit`, `bon_livraison`) VALUES (?,?,?,?,?)";
					
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
	public Boolean deleteBonLivraison(int idBonLivraison) {
		cn = ConnexionDB.getConnexion();
		  PreparedStatement ps;
		String sqlDevisDelete ="DELETE FROM `bon_livraison` WHERE id = ?";
				
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
	public Boolean printBonLivraison(BonLivraison bonLivraison) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BonLivraison getBonLivraison(int id) {
		cn = ConnexionDB.getConnexion();
		
		BonLivraison bonLivraison = new BonLivraison();
		
		ClientServiceImpl clientServiceImpl = new ClientServiceImpl();
		String sql = "SELECT * FROM `bon_livraison` LEFT JOIN adress on adress.id = bon_livraison.address where bon_livraison.id="+id;
		
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
			bonLivraison.setClient(clientServiceImpl.getClient(rs.getInt("bon_livraison.client")));
			bonLivraison.setDate(rs.getTimestamp("bon_livraison.date"));
			bonLivraison.setAdressLivraison(address);
			bonLivraison.setInformation(rs.getString("bon_livraison.information"));
			bonLivraison.setLignesDevis(this.getAllLigneBonLivraison(bonLivraison.getId()));
		
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
	public ArrayList<BonLivraison> getAllBonLivraison() {
		cn = ConnexionDB.getConnexion();
		ArrayList<BonLivraison> listeBonLivraison = new ArrayList<BonLivraison>();
		
	
		ClientServiceImpl clientServiceImpl = new ClientServiceImpl();
		
		String sql = "SELECT * FROM `bon_livraison` LEFT JOIN adress on adress.id = bon_livraison.address";
		
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
				BonLivraison bonLivraison = new BonLivraison();
				/// --------------------------------------------------
				// ------------------info bon livraison
				//-----------------------------------------------
				bonLivraison.setId(rs.getInt("devis.id"));
				bonLivraison.setCode(rs.getString("devis.code"));
				bonLivraison.setClient(clientServiceImpl.getClient(rs.getInt("devis.client")));
				bonLivraison.setDate(rs.getTimestamp("devis.date"));
				bonLivraison.setAdressLivraison(address);
				bonLivraison.setInformation(rs.getString("bon_livraison.information"));
				bonLivraison.setLignesDevis(this.getAllLigneBonLivraison(bonLivraison.getId()));
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
