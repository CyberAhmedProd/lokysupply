package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import dao.ProductService;
import db.ConnexionDB;
import models.Adress;
import models.CompteBancaire;
import models.Fournisseur;
import models.Product;
import models.ProduitFamille;
import models.RaisonSocial;
import models.TypeEntreprise;
import models.UnitOfMeasure;

public class ProductServiceImpl implements ProductService{
	Statement st = null;
	Connection cn = null;
	@Override
	public Product get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Product> getAll() {
cn = ConnexionDB.getConnexion();
		
		ArrayList<Product> listProduct = new ArrayList();
		String sql = "SELECT * FROM produit LEFT JOIN fournisseur on fournisseur.id = produit.fournisseur LEFT JOIN product_family on product_family.id = produit.famille LEFT JOIN raison_social on raison_social.id = fournisseur.raision_social";
		try {
			
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
			
				
				Product product = new Product();
				Fournisseur fournisseur = new Fournisseur();
				ProduitFamille famille = new ProduitFamille();
				RaisonSocial raisonSocial = new RaisonSocial();
				
				
				// -----------fournisseur info
				fournisseur.setId(rs.getInt("fournisseur.id"));
				raisonSocial.setNom(rs.getString("product_family.nom"));
				raisonSocial.setId(rs.getInt("product_family.id"));
				fournisseur.setRaisonSocial(raisonSocial);
			
				
				// -----------------------------------------
				// --------------------------type product  info 
				famille.setId(rs.getInt("product_family.id"));
				famille.setNom(rs.getString("product_family.nom"));
				famille.setType(rs.getString("product_family.type"));
				
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
				product.setFournisseur(fournisseur);
				product.setFamille(famille);
				
				listProduct.add(product);
				
				
			}
			rs.close();
			st.close();
	
			return listProduct;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean save(Product p) {
		 cn = ConnexionDB.getConnexion();
		 PreparedStatement ps;
		String sqlafamille ="INSERT INTO `product_family`(`nom`, `type`) VALUES (?,?)";
				
			
			try {
				ps=(PreparedStatement) cn.prepareStatement(sqlafamille, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1,p.getFamille().getNom());
				ps.setString(2,p.getFamille().getType());
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				p.getFamille().setId(rs.getInt(1));
				//System.out.println("ok");
				ps.close();
				
			} catch (SQLException e2) {
				
				System.out.println("famille");
			}
			
			
			String sqlproduct ="INSERT INTO `produit`(`ref`, `designation`, `unit`, `unit_price`, `unit_price_tva`, `min_stock`, `stock`, `fournisseur`, `famille`) VALUES "
					+ "('"+p.getRef()+"','"+p.getDesignation()+"','"+p.getUnit()+"',"+p.getUnitPriceHt()+",'"+p.getUnitPriceTva()+"','"+p.getMinStock()+"','"+p.getStock()+"',"+p.getFournisseur().getId()+","+p.getFamille().getId()+")";
			try {
				st = cn.createStatement();
				st.executeUpdate(sqlproduct);
				
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			
			}
			
			return false;
		
	}

	@Override
	public Boolean update(Product p) {
		cn = ConnexionDB.getConnexion();
		  PreparedStatement ps;
		String sqlProductUpdate ="UPDATE `produit` SET `ref`=?,"
				+ "`designation`= ?,`unit`= ?,`unit_price`= ?,`unit_price_tva`= ?,`min_stock`= ?,`stock`= ?,`fournisseur`= ?,`famille`= ? WHERE id = ?";
				
			
			try {
				
				ps=(PreparedStatement) cn.prepareStatement(sqlProductUpdate);
				ps.setString(1,p.getRef());
				ps.setString(2,p.getDesignation());
				ps.setString(3,p.getUnit().toString());
				ps.setDouble(4,p.getUnitPriceHt());
				ps.setDouble(5,p.getUnitPriceTva());
				ps.setDouble(6,p.getMinStock());
				ps.setDouble(7,p.getStock());
				ps.setInt(8,p.getFournisseur().getId());
				ps.setDouble(9,p.getFamille().getId());
				ps.setDouble(10,p.getId());
				ps.executeUpdate();
				//System.out.println("ok");
				ps.close();
				return true;
				
			} catch (SQLException e2) {
				
				System.out.println("product");
				return false;
			}
			
		
			
		
			
			
			
		
	}

	@Override
	public Boolean delete(Product t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean checkStock() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String alerteStock() {
		// TODO Auto-generated method stub
		return null;
	}

}
