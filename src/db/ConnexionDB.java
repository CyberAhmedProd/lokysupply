package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexionDB {
	String  url="jdbc:mysql://localhost/lokyerpbase";
	String login="root";
	String password="";
	
	static Connection cn;
	private ConnexionDB() {
		super();
		try {
			Class.forName("com.mysql.jdbc.Driver"); //chargement du pilote
			cn = DriverManager.getConnection(url,login,password); // instantiation
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static Connection getConnexion() {
		if(cn==null)
			new ConnexionDB();
		return cn;
	}
}
