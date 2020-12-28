package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import dao.FournisseurService;
import db.ConnexionDB;
import models.Adress;

import models.CompteBancaire;
import models.Fournisseur;
import models.RaisonSocial;
import models.TypeEntreprise;

public class FournisseurServiceImpl implements FournisseurService{

	Statement st = null;
	Connection cn = null;
	@Override
	public Fournisseur get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Fournisseur> getAll() {
		cn = ConnexionDB.getConnexion();
		
		ArrayList<Fournisseur> listFournisseur = new ArrayList();
		String sql = "SELECT * FROM fournisseur LEFT JOIN raison_social on raison_social.id = fournisseur.raision_social LEFT JOIN compte_bancaire on compte_bancaire.id = fournisseur.compte_bancaire LEFT JOIN adress on adress.id = fournisseur.address";
		try {
			
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
			
				
				Fournisseur fournisseur = new Fournisseur();
				Adress address = new Adress();
				CompteBancaire compte = new CompteBancaire();
				RaisonSocial social = new RaisonSocial();
				ArrayList<CompteBancaire> listCompte = new ArrayList();
				listCompte.add(compte);
				
				// -----------Address info
				address.setId(rs.getInt("adress.id"));
				address.setNumRue(rs.getInt("numRue"));
				address.setLibelleRue(rs.getString("libelleRue"));
				address.setNomVille(rs.getString("nomVille"));
				address.setCodePostale(rs.getInt("codepostal"));
				address.setGouvernat(rs.getString("gouvernorat"));
				address.setPays(rs.getString("pays"));
				// -----------------------------------------
				// --------------------------CompteBancaire info 
				compte.setId(rs.getInt("compte_bancaire.id"));
				compte.setNameBanque(rs.getString("nom_banque"));
				compte.setAgence(rs.getString("nom_agance"));
				compte.setNumRib(rs.getInt("rib_num"));
				
				/// --------------------------------------------------
				// ------------------info Social
				//-----------------------------------------------
				social.setId(rs.getInt("raison_social.id"));
				social.setNom(rs.getString("nom"));
				social.setPrenom(rs.getString("prenom"));
				social.setSexe("homme");
				
				//
				fournisseur.setMatricule(rs.getString("matricule"));
				if(rs.getString("matricule")=="PHYSIQUE") {
					fournisseur.setType(TypeEntreprise.PYHSIUE);
				}
				else {
					fournisseur.setType(TypeEntreprise.MORALE);
				}
				fournisseur.setId(rs.getInt("id"));
				fournisseur.setDescription(rs.getString("description"));
				fournisseur.setTelFix(Integer.parseInt(rs.getString("telfix")));
				fournisseur.setTelMobile(Integer.parseInt(rs.getString("telmobile")));
				fournisseur.setEmail(rs.getString("email"));
				fournisseur.setWebSite(rs.getString("website"));
				fournisseur.setAdresse(address);
				fournisseur.setCompteBancaires(listCompte);
				fournisseur.setRaisonSocial(social);
				listFournisseur.add(fournisseur);
				
				
			}
			rs.close();
			st.close();
	
			return listFournisseur;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}

	

	@Override
	public Boolean save(Fournisseur p) {
		 cn = ConnexionDB.getConnexion();
		 PreparedStatement ps;
		String sqladress ="INSERT INTO `adress`(`numRue`, `libelleRue`, `nomVille`, `codepostal`, `gouvernorat`, `pays`) VALUES (?,?,?,?,?,?)";
				
		
		String sqlsocial ="INSERT INTO `raison_social`(`nom`, `prenom`, `sexe`) VALUES (?,?,?)";
		
	
		
		String sqlbanque ="INSERT INTO `compte_bancaire`(`nom_banque`, `nom_agance`, `rib_num`) VALUES (?,?,?)";
		
		
			
			try {
				ps=(PreparedStatement) cn.prepareStatement(sqladress, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1,p.getAdresse().getNumRue());
				ps.setString(2,p.getAdresse().getLibelleRue());
				ps.setString(3,p.getAdresse().getNomVille());
				ps.setInt(4,p.getAdresse().getCodePostale());
				ps.setString(5,p.getAdresse().getGouvernat());
				ps.setString(6,p.getAdresse().getPays());
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				p.getAdresse().setId(rs.getInt(1));
				//System.out.println("ok");
				ps.close();
				
			} catch (SQLException e2) {
				
				System.out.println("adress");
			}
			
			try {
				ps=(PreparedStatement) cn.prepareStatement(sqlsocial, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1,p.getRaisonSocial().getNom());
				ps.setString(2,p.getRaisonSocial().getPrenom());
				ps.setString(3,p.getRaisonSocial().getSexe());
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				p.getRaisonSocial().setId(rs.getInt(1));
				ps.close();
				//System.out.println("ok");
			} catch (SQLException e2) {
				
				System.out.println("social");
			}
			
			try {
				ps=(PreparedStatement) cn.prepareStatement(sqlbanque, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1,p.getCompteBancaires().get(0).getNameBanque());
				ps.setString(2,p.getCompteBancaires().get(0).getNameBanque());
				ps.setInt(3,p.getCompteBancaires().get(0).getNumRib());
		
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				p.getCompteBancaires().get(0).setId(rs.getInt(1));
				ps.close();
			} catch (SQLException e2) {
				
				System.out.println("anque");
			}
			String sql ="INSERT INTO `fournisseur`(`matricule`, `type`, `description`, `telfix`, `telmobile`, `email`, `website`, `raision_social`, `compte_bancaire`, `address`) VALUES "
					+ "('"+p.getMatricule()+"','"+p.getType()+"','"+p.getDescription()+"',"+p.getTelFix()+",'"+p.getTelMobile()+"','"+p.getEmail()+"','"+p.getWebSite()+"',"+p.getRaisonSocial().getId()+","+p.getCompteBancaires().get(0).getId()+","+p.getAdresse().getId()+")";
			try {
				st = cn.createStatement();
				st.executeUpdate(sql);
				
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			
			}
			
			return false;
		
	}

	@Override
	public Boolean update(Fournisseur c) {
		 cn = ConnexionDB.getConnexion();
		  PreparedStatement ps;
		String sqladressUpdate ="UPDATE `adress` SET `numRue`= ?,`libelleRue`= ?,`nomVille`= ?,`codepostal`= ?,`gouvernorat`= ?,`pays`= ? WHERE id = ?";
				
	
		String sqlsocialUpdate ="UPDATE `raison_social` SET `nom`= ?,`prenom`= ?,`sexe`= ? WHERE id = ?";
		
	
		
		String sqlbanqueUpdate ="UPDATE `compte_bancaire` SET `nom_banque`= ?,`nom_agance`= ?,`rib_num`= ? WHERE id = ?";
		
	
		
			
			try {
				
				ps=(PreparedStatement) cn.prepareStatement(sqladressUpdate);
				ps.setInt(1,c.getAdresse().getNumRue());
				ps.setString(2,c.getAdresse().getLibelleRue());
				ps.setString(3,c.getAdresse().getNomVille());
				ps.setInt(4,c.getAdresse().getCodePostale());
				ps.setString(5,c.getAdresse().getGouvernat());
				ps.setString(6,c.getAdresse().getPays());
				ps.setInt(7,c.getAdresse().getId());
				ps.executeUpdate();
				//System.out.println("ok");
				ps.close();
				
			} catch (SQLException e2) {
				
				System.out.println("adress");
			}
			
			try {
				ps=(PreparedStatement) cn.prepareStatement(sqlsocialUpdate);
				ps.setString(1,c.getRaisonSocial().getNom());
				ps.setString(2,c.getRaisonSocial().getPrenom());
				ps.setString(3,c.getRaisonSocial().getSexe());
				ps.setInt(4,c.getRaisonSocial().getId());
				ps.executeUpdate();
				ps.close();
				//System.out.println("ok");
			} catch (SQLException e2) {
				
				System.out.println("social");
			}
			
			try {
				ps=(PreparedStatement) cn.prepareStatement(sqlbanqueUpdate);
				ps.setString(1,c.getCompteBancaires().get(0).getNameBanque());
				ps.setString(2,c.getCompteBancaires().get(0).getNameBanque());
				ps.setInt(3,c.getCompteBancaires().get(0).getNumRib());
				ps.setInt(4,c.getCompteBancaires().get(0).getId());
		
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e2) {
				
				System.out.println("anque");
			}
			
			String sqlUpdate ="UPDATE `fournisseur` SET `matricule`= ?,`type`= ?,`description`= ?,`telfix`= ?,`telmobile`= ?,`email`= ?,`website`= ? WHERE id=?";
			try {
				ps=(PreparedStatement) cn.prepareStatement(sqlUpdate);
				ps.setString(1,c.getMatricule());
				ps.setString(2,c.getType().toString());
				ps.setString(3,c.getDescription());
				ps.setInt(4,c.getTelFix());
				ps.setInt(5,c.getTelMobile());
				ps.setString(6,c.getEmail());
				ps.setString(7,c.getWebSite());
				ps.setInt(8,c.getId());
				
				ps.executeUpdate();
				ps.close();
				
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			
			}
			
			
			
			return true;
		
	}

	@Override
	public Boolean delete(Fournisseur c) {
		cn = ConnexionDB.getConnexion();
		  PreparedStatement ps;
		String sqladressDelete ="DELETE FROM `adress` WHERE id = ?";
				
	
		String sqlsocialDelete ="DELETE FROM `raison_social` WHERE id = ?";
		
	
		String sqlbanqueDelete ="DELETE FROM `compte_bancaire` WHERE id = ?";
		
		String sqlDeleteFournisseur ="DELETE FROM `fournisseur` WHERE id= ?";
		try {
			ps=(PreparedStatement) cn.prepareStatement(sqlDeleteFournisseur);
			ps.setInt(1,c.getId());
			ps.executeUpdate();
			ps.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
			
		
		}
		
		try {
			
			ps=(PreparedStatement) cn.prepareStatement(sqladressDelete);
			ps.setInt(1,c.getAdresse().getId());
			ps.executeUpdate();
			//System.out.println("ok");
			ps.close();
			
		} catch (SQLException e2) {
			
			System.out.println("adress");
			return false;
		}
		
		try {
			ps=(PreparedStatement) cn.prepareStatement(sqlbanqueDelete);
			ps.setInt(1,c.getCompteBancaires().get(0).getId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e2) {
			
			System.out.println("anque");
			return false;
		}
		
		try {
			ps=(PreparedStatement) cn.prepareStatement(sqlsocialDelete);
			ps.setInt(1,c.getRaisonSocial().getId());
			ps.executeUpdate();
			ps.close();
			return true;
			//System.out.println("ok");
		} catch (SQLException e2) {
			
			System.out.println("social");
			return false;
		}
		
	}

}
