package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import dao.EntrepriseService;
import db.ConnexionDB;
import models.Adress;
import models.CompteBancaire;
import models.Entreprise;
import models.Fournisseur;
import models.RaisonSocial;
import models.TypeEntreprise;

public class EntrepriseServiceImpl implements EntrepriseService{
	Statement st = null;
	Connection cn = null;

	@Override
	public Entreprise getEntreprise() {
		cn = ConnexionDB.getConnexion();
		Entreprise entreprise = new Entreprise();
		String sql = "SELECT * FROM entreprise LEFT JOIN raison_social on raison_social.id = entreprise.raision_social LEFT JOIN compte_bancaire on compte_bancaire.id = entreprise.compte_bancaire LEFT JOIN adress on adress.id = entreprise.address";
		try {
			
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
			
				
				
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
				entreprise.setMatricule(rs.getString("matricule"));
				if(rs.getString("matricule")=="PHYSIQUE") {
					entreprise.setType(TypeEntreprise.PHYSIQUE);
				}
				else {
					entreprise.setType(TypeEntreprise.MORALE);
				}
				entreprise.setId(rs.getInt("id"));
				entreprise.setDescription(rs.getString("description"));
				entreprise.setTelFix(Integer.parseInt(rs.getString("telfix")));
				entreprise.setTelMobile(Integer.parseInt(rs.getString("telmobile")));
				entreprise.setEmail(rs.getString("email"));
				entreprise.setWebSite(rs.getString("website"));
				entreprise.setAdresse(address);
				entreprise.setCompteBancaires(listCompte);
				entreprise.setRaisonSocial(social);
				entreprise.setTva_assuj(rs.getBoolean("tva_ajussti"));
				
				rs.close();
				st.close();
				return entreprise;
			}
			else 
				return null;
			
	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public ArrayList<Entreprise> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean save(Entreprise e) {
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
				ps.setInt(1,e.getAdresse().getNumRue());
				ps.setString(2,e.getAdresse().getLibelleRue());
				ps.setString(3,e.getAdresse().getNomVille());
				ps.setInt(4,e.getAdresse().getCodePostale());
				ps.setString(5,e.getAdresse().getGouvernat());
				ps.setString(6,e.getAdresse().getPays());
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				e.getAdresse().setId(rs.getInt(1));
				//System.out.println("ok");
				ps.close();
				
			} catch (SQLException e2) {
				
				System.out.println("adress");
			}
			
			try {
				ps=(PreparedStatement) cn.prepareStatement(sqlsocial, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1,e.getRaisonSocial().getNom());
				ps.setString(2,e.getRaisonSocial().getPrenom());
				ps.setString(3,e.getRaisonSocial().getSexe());
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				e.getRaisonSocial().setId(rs.getInt(1));
				ps.close();
				//System.out.println("ok");
			} catch (SQLException e2) {
				
				System.out.println("social");
			}
			
			try {
				ps=(PreparedStatement) cn.prepareStatement(sqlbanque, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1,e.getCompteBancaires().get(0).getNameBanque());
				ps.setString(2,e.getCompteBancaires().get(0).getNameBanque());
				ps.setInt(3,e.getCompteBancaires().get(0).getNumRib());
		
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				e.getCompteBancaires().get(0).setId(rs.getInt(1));
				ps.close();
			} catch (SQLException e2) {
				
				System.out.println("anque");
			}
			String sql ="INSERT INTO `entreprise`(`matricule`, `type`, `description`, `telfix`, `telmobile`, `email`, `website`, `raision_social`, `compte_bancaire`, `address`, `tva_ajussti`) VALUES "
					+ "('"+e.getMatricule()+"','"+e.getType()+"','"+e.getDescription()+"',"+e.getTelFix()+",'"+e.getTelMobile()+"','"+e.getEmail()+"','"+e.getWebSite()+"',"+e.getRaisonSocial().getId()+","+e.getCompteBancaires().get(0).getId()+","+e.getAdresse().getId()+","+e.isTva_assuj()+")";
			try {
				st = cn.createStatement();
				st.executeUpdate(sql);
				
				return true;
			} catch (SQLException k) {
				// TODO Auto-generated catch block
				k.printStackTrace();
				
			
			}
			
			return false;
	}

	@Override
	public Boolean update(Entreprise e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Entreprise e) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
