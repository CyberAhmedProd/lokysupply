package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import dao.UserService;
import db.ConnexionDB;
import models.User;

public class UserServiceImpl implements UserService{
	
	Statement st = null;
	Connection  cn;
	public UserServiceImpl() {
		super();
	}

	@Override
	public User login(String login, String password) {
		cn = ConnexionDB.getConnexion();
		User person = null;
		String sql = "SELECT * FROM `user` WHERE login='"+login+"' AND password='"+password+"'";
		try {
				st = cn.createStatement();
				ResultSet rs = st.executeQuery(sql);
				rs.next();
				person= new User();
				person.setId(rs.getInt("id"));
				person.setNom(rs.getString("nom"));
				person.setPrenom(rs.getString("prenom"));
				person.setLogin(rs.getString("login"));
				person.setPassword(rs.getString("password"));
				person.setCreated_at(rs.getTimestamp("account_created"));
				person.setEtat(rs.getBoolean("etat"));
				return person;
				
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				return person;
			}
		
	}

	@Override
	public boolean register(User p) {
		Connection  cn = ConnexionDB.getConnexion();
		String sql ="INSERT INTO `user`(`nom`, `prenom`, `login`, `password`) VALUES "
				+ "('"+p.getNom()+"','"+p.getPrenom()+"','"+p.getLogin()+"','"+p.getPassword()+"')";
		try {
			st = cn.createStatement();
			st.executeUpdate(sql);
		
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		}
	}
	public void closeConnection() throws SQLException {
		this.cn.close();
	}

}
