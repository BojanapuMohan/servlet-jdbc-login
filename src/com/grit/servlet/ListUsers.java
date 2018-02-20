package com.grit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListUsers
 */
@WebServlet("/ListUsers")
public class ListUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println("Servlet implents");
		String jdbcUrl = "jdbc:postgresql://localhost:5432/servlet-tutorial";
	    String dbusername = "postgres";
	    String dbpassword = "grit123";

	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	
	    	 conn = DriverManager.getConnection(jdbcUrl, dbusername, dbpassword);
	    	 
	    	 String sql = "select * from users";
	    	 stmt = conn.prepareStatement("select * from users");
	    	 
	         rs = stmt.executeQuery();
	         
	         
	         
	         PrintWriter out = response.getWriter();
	         
	         out.print("<html><head></head>");
	         out.print("<body><table class='table'><thead><tr><th scope='col'>#</th><th scope='col'>First</th></tr></thead><tbody>");
	         
	         while(rs.next()) {
	             out.print("<tr><th scope='row'>"+ rs.getString(1)+"</th><td>"+ rs.getString(2) +"</td></tr>");
	         }
	         
	         out.print("</tbody></table></body>");
	         out.print("</html>");

		} catch (Exception e) {
			System.out.println("Message" + e.getMessage());
		}
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
