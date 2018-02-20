package com.grit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class FirstServlet implements Servlet{
	
	ServletConfig servletConfig = null;

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {

		/*Data base configration*/
		System.out.println("Servlet implents");
		String jdbcUrl = "jdbc:postgresql://localhost:5432/servlet-tutorial";
	    String dbusername = "postgres";
	    String dbpassword = "grit123";
	    
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	    	
	    	conn = DriverManager.getConnection(jdbcUrl, dbusername, dbpassword);
	    	
	    	
	    	pstmt  = (PreparedStatement) conn.prepareStatement("insert into users values(?,?,?,?)");

	    	
	    	
	    	res.setContentType("text");
			
			PrintWriter out = res.getWriter();
			
			
			out.print("<html><head></head>");
			
			

			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String username = req.getParameter("username");
			
			String password = req.getParameter("password");
			
			String confirm = req.getParameter("confirm");
			
			pstmt.setString(1, name);
			
			pstmt.setString(2, email);
			pstmt.setString(3, username);
			
			pstmt.setString(4, password);
			
			String responseString;
			if (password.equals(confirm)){
				
				int i=pstmt.executeUpdate();  
				System.out.println(i+" records inserted");  
				  
				conn.close();  
				responseString = "Inserted Sucessfully";
			}else{
				responseString = "please enter same password";
			}
			
			
			out.print("<body>"+ responseString + "</body>");
			out.print("<html>");
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	
	public void destroy(){
		System.out.println("servlet is destroyed");
	}
	public ServletConfig getServletConfig(){
		return servletConfig;
	}
	public String getServletInfo(){
		return "copyright 2007-1010";
		}


	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		
		this.servletConfig = servletConfig;
	}

}
