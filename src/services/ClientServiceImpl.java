package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import com.mysql.jdbc.PreparedStatement;

import dao.ClientService;
import db.ConnexionDB;
import models.Adress;
import models.Client;
import models.CompteBancaire;
import models.RaisonSocial;
import models.TypeEntreprise;

public class ClientServiceImpl implements ClientService{
	Statement st = null;
	Connection cn = null;
	@Override
	public Client get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Client> getAll() {
		cn = ConnexionDB.getConnexion();
		
		ArrayList<Client> listClient = new ArrayList();
		String sql = "SELECT * FROM client LEFT JOIN raison_social on raison_social.id = client.raision_social LEFT JOIN compte_bancaire on compte_bancaire.id = client.compte_bancaire LEFT JOIN adress on adress.id = client.address";
		try {
			
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
			
				
				Client client = new Client();
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
				client.setMatricule(rs.getString("matricule"));
				if(rs.getString("matricule")=="PHYSIQUE") {
					client.setType(TypeEntreprise.PHYSIQUE);
				}
				else {
					client.setType(TypeEntreprise.MORALE);
				}
				client.setId(rs.getInt("id"));
				client.setDescription(rs.getString("description"));
				client.setTelFix(Integer.parseInt(rs.getString("telfix")));
				client.setTelMobile(Integer.parseInt(rs.getString("telmobile")));
				client.setEmail(rs.getString("email"));
				client.setWebSite(rs.getString("website"));
				client.setAdresse(address);
				client.setCompteBancaires(listCompte);
				client.setRaisonSocial(social);
				client.setTva_assuj(rs.getBoolean("tva_ajussti"));
				listClient.add(client);
				/*
				ArrayList<CompteBancaire> listCompte = new ArrayList();
				CompteBancaire compte = new CompteBancaire();
				compte.setNameBanque(rs.getString("nom_banque"));
				compte.setAgence(rs.getString("nom_agance"));
				compte.setNumRib(rs.getInt("rib_num"));
				listCompte.add(compte);
				
				
				TypeEntreprise type = rs.getString("matricule").equals("PHYSIQUE")?TypeEntreprise.PYHSIUE:TypeEntreprise.MORALE;
				listClient.add(i,
						new Client(rs.getString("matricule"),
								rs.getString("description"),
								Integer.parseInt(rs.getString("telfix")),
								Integer.parseInt(rs.getString("telmobile")),
								rs.getString("email"), 
								rs.getString("website"),
								new Adress(rs.getInt("numRue"),
										rs.getString("libelleRue"),
										rs.getString("nomVille"),
										rs.getInt("codepostal"),
										rs.getString("gouvernorat"),
										rs.getString("pays")),
							
								new RaisonSocial(
										rs.getString("nom_banque"),
										rs.getString("prenom"),
										"homme"),
								type ,
								listCompte
										rs.getString("nom_banque"),
										 
										rs.getString("nom_agance"),
										rs.getInt("rib_num")))
										
						));
						*/
				
			}
			rs.close();
			st.close();
	
			return listClient;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}

	

	@Override
	public Boolean save(Client p) {
		 cn = ConnexionDB.getConnexion();
		 PreparedStatement ps;
		String sqladress ="INSERT INTO `adress`(`numRue`, `libelleRue`, `nomVille`, `codepostal`, `gouvernorat`, `pays`) VALUES (?,?,?,?,?,?)";
				
		/*String sqladress ="INSERT INTO `adress`(`numRue`, `libelleRue`, `nomVille`, `codepostal`, `gouvernorat`, `pays`) VALUES "
				+ "("+p.getAdresse().getNumRue()+",'"+p.getAdresse().getLibelleRue()+"','"+p.getAdresse().getNomVille()+"',"+p.getAdresse().getCodePostale()+",'"+p.getAdresse().getGouvernat()+"','"+p.getAdresse().getPays()+"')";
		*/
		String sqlsocial ="INSERT INTO `raison_social`(`nom`, `prenom`, `sexe`) VALUES (?,?,?)";
		
		/*String sqlsocial ="INSERT INTO `raison_social`(`nom`, `prenom`, `sexe`) VALUES "
				+ "('"+p.getRaisonSocial().getNom()+"','"+p.getRaisonSocial().getPrenom()+"','"+p.getRaisonSocial().getSexe()+"')";*/
		
		String sqlbanque ="INSERT INTO `compte_bancaire`(`nom_banque`, `nom_agance`, `rib_num`) VALUES (?,?,?)";
		
		/*String sqlbanque ="INSERT INTO `compte_bancaire`(`nom_banque`, `nom_agance`, `rib_num`) VALUES "
				+ "('"+p.getCompteBancaires().get(0).getNameBanque()+"','"+p.getCompteBancaires().get(0).getNameBanque()+"',"+p.getCompteBancaires().get(0).getNumRib()+")";*/
		
			
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
			String sql ="INSERT INTO `client`(`matricule`, `type`, `description`, `telfix`, `telmobile`, `email`, `website`, `raision_social`, `compte_bancaire`, `address`, `tva_ajussti`) VALUES "
					+ "('"+p.getMatricule()+"','"+p.getType()+"','"+p.getDescription()+"',"+p.getTelFix()+",'"+p.getTelMobile()+"','"+p.getEmail()+"','"+p.getWebSite()+"',"+p.getRaisonSocial().getId()+","+p.getCompteBancaires().get(0).getId()+","+p.getAdresse().getId()+","+p.isTva_assuj()+")";
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
	public Boolean update(Client c) {
		 cn = ConnexionDB.getConnexion();
		  PreparedStatement ps;
		String sqladressUpdate ="UPDATE `adress` SET `numRue`= ?,`libelleRue`= ?,`nomVille`= ?,`codepostal`= ?,`gouvernorat`= ?,`pays`= ? WHERE id = ?";
				
		/*String sqladress ="INSERT INTO `adress`(`numRue`, `libelleRue`, `nomVille`, `codepostal`, `gouvernorat`, `pays`) VALUES "
				+ "("+p.getAdresse().getNumRue()+",'"+p.getAdresse().getLibelleRue()+"','"+p.getAdresse().getNomVille()+"',"+p.getAdresse().getCodePostale()+",'"+p.getAdresse().getGouvernat()+"','"+p.getAdresse().getPays()+"')";
		*/
		String sqlsocialUpdate ="UPDATE `raison_social` SET `nom`= ?,`prenom`= ?,`sexe`= ? WHERE id = ?";
		
		/*String sqlsocial ="INSERT INTO `raison_social`(`nom`, `prenom`, `sexe`) VALUES "
				+ "('"+p.getRaisonSocial().getNom()+"','"+p.getRaisonSocial().getPrenom()+"','"+p.getRaisonSocial().getSexe()+"')";*/
		
		String sqlbanqueUpdate ="UPDATE `compte_bancaire` SET `nom_banque`= ?,`nom_agance`= ?,`rib_num`= ? WHERE id = ?";
		
		/*String sqlbanque ="INSERT INTO `compte_bancaire`(`nom_banque`, `nom_agance`, `rib_num`) VALUES "
				+ "('"+p.getCompteBancaires().get(0).getNameBanque()+"','"+p.getCompteBancaires().get(0).getNameBanque()+"',"+p.getCompteBancaires().get(0).getNumRib()+")";*/
		
			
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
			
			String sqlUpdate ="UPDATE `client` SET `matricule`= ?,`type`= ?,`description`= ?,`telfix`= ?,`telmobile`= ?,`email`= ?,`website`= ?,`tva_ajussti`= ? WHERE id=?";
			try {
				ps=(PreparedStatement) cn.prepareStatement(sqlUpdate);
				ps.setString(1,c.getMatricule());
				ps.setString(2,c.getType().toString());
				ps.setString(3,c.getDescription());
				ps.setInt(4,c.getTelFix());
				ps.setInt(5,c.getTelMobile());
				ps.setString(6,c.getEmail());
				ps.setString(7,c.getWebSite());
				ps.setBoolean(8,c.isTva_assuj());
				ps.setInt(9,c.getId());
				
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
	public Boolean delete(Client c) {
		cn = ConnexionDB.getConnexion();
		  PreparedStatement ps;
		String sqladressDelete ="DELETE FROM `adress` WHERE id = ?";
				
	
		String sqlsocialDelete ="DELETE FROM `raison_social` WHERE id = ?";
		
	
		
		String sqlbanqueDelete ="DELETE FROM `compte_bancaire` WHERE id = ?";
		
		String sqlDeleteClient ="DELETE FROM `client` WHERE id= ?";
		try {
			ps=(PreparedStatement) cn.prepareStatement(sqlDeleteClient);
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
