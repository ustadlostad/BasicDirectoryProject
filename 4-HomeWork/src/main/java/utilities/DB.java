package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DB {
	
	final private String url = "jdbc:mysql://localhost/";
	final private String dbName = "fyp";
	final private String dbUser = "root";
	final private String dbPass = "";
	
	final private String driver = "com.mysql.jdbc.Driver";
	
	
	private Connection con = null;
	private PreparedStatement st; //prepared statement güvenlik ve stabilizasyon için iyidir statement da injectin yersin
	
	public DB() {
		
		try {
			
			Class.forName(driver);//jdbc kütüphanesini tetiklemek için bu yazýlýr
			con = DriverManager.getConnection(url+dbName, dbUser, dbPass);
			System.out.println("Baðlantý baþarýlý!");
			
		} catch (Exception e) {
			System.err.println("Db baðlantý hatasý :"+e);
		}

	}
	
	
	
	
	public PreparedStatement connect (String query) {
		
		try {
			
			st = con.prepareStatement(query);
			
			return st;
			
		} catch (Exception e) {
			System.err.println("Pre Statement hatasý : "+e);
		}
		
		return st;
	}
	
}
