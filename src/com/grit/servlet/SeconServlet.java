package com.grit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class SeconServlet extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		
		/*Data base configration*/
		
		String jdbcUrl = "jdbc:postgresql://localhost:5432/servlet-tutorial";
	    String dbusername = "postgres";
	    String dbpassword = "grit123";
	    
	    Connection conn = null;
	    PreparedStatement stmt;
	    ResultSet rs = null;

	    try {
	    	
	    	Class.forName("org.postgresql.Driver");
	    	conn = DriverManager.getConnection(jdbcUrl, dbusername, dbpassword);
	    	
	    	
	    	//pstmt  = (PreparedStatement) conn.prepareStatement("insert into users values(?,?,?,?)");

	    	//stmt=conn.prepareStatement("insert into users values(?,?,?,?,?)");  
	    	
	    	String query = " insert into users (name, email, username, password)"
	    	        + " values (?, ?, ?, ?)";
	    	
	    	stmt=conn.prepareStatement(query);  
	    	
	    	res.setContentType("text");
			
			PrintWriter out = res.getWriter();
			
			
			out.print("<html><head></head>");
			
			

			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String username = req.getParameter("username");
			
			String password = req.getParameter("password");
			
			String confirm = req.getParameter("confirm");
			
			stmt.setString(1, name);
			
			stmt.setString(2, email);
			stmt.setString(3, username);
			
			stmt.setString(4, password);
			
			String responseString;
			if (password.equals(confirm)){
				
				int i=stmt.executeUpdate();  
				System.out.println(i+" records inserted");  
				RequestDispatcher rd=req.getRequestDispatcher("list-users");  
		        rd.forward(req,res);  
				conn.close();  
				responseString = "Inserted Sucessfully";
			}else{
				responseString = "please enter same password";
			}
						
			out.print("<body>"+ responseString + "</body>");
			out.print("<html>");
			
		} catch (Exception e) {
			System.out.println("Print error msg" +e.getMessage());
			// TODO: handle exception
		}
		
	}

}
