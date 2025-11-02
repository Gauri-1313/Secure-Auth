package db;

import java.sql.*;

public class DatabaseConnection {
	private static Connection con = null;
	
	public static Connection getConnection() {
		if(con == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql:///secure_auth","root","Gauri@1331");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return con;
	}
}
