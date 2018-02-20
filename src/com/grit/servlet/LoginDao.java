package com.grit.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
	
	static String jdbcUrl = "jdbc:postgresql://localhost:5432/servlet-tutorial";
	static String dbusername = "postgres";
	static String dbpassword = "grit123";
	
	
	
	
	public static boolean validate(String username, String password) throws ClassNotFoundException, SQLException{
		
		Connection con = null;
		
		PreparedStatement stmt = null;
		
		ResultSet rs = null;
		
		boolean status = false;
		Class.forName("org.postgresql.Driver");
		
		con = DriverManager.getConnection(jdbcUrl, dbusername, dbpassword);
		
		stmt = con.prepareStatement("select * from users where username=? and password=?");
		
		stmt.setString(1, username);
		stmt.setString(2, password);
		
		

		rs=stmt.executeQuery();  
		status=rs.next();  

		return status;
		
		
		
	}

}
