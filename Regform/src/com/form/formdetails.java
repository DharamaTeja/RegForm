package com.form;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class formdetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public formdetails() {
      
    } 
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
	    	res.setContentType("text/html");
		    PrintWriter pw=res.getWriter();
	
			String name=req.getParameter("username");
			long mobileno=Long.parseLong(req.getParameter("MobileNo"));
			String email=req.getParameter("Email");
			String mydate= req.getParameter("dob");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			
				java.util.Date dateOfJoin = null;
				try {
					dateOfJoin = formatter.parse(mydate);
				} catch (ParseException e1) {
				
					e1.printStackTrace();
				} 
			
			String gender=req.getParameter("gender");
		    int Age=Integer.parseInt(req.getParameter("age"));
		    String languages[] = req.getParameterValues("language");
		   
			String state=req.getParameter("State");
			
			try {
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
			
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
				
				PreparedStatement stmt=con.prepareStatement("insert into regtable(name,mobileno,email,dob,gender,Age,language,state) values(?,?,?,?,?,?,?,?)");
		          
			        stmt.setString(1,name);
	                stmt.setLong(2,mobileno);
	                stmt.setString(3,email);
	                java.sql.Date dateJoin= new java.sql.Date( dateOfJoin.getTime() ); 
	     			stmt.setDate(4,dateJoin);
	     		    stmt.setString(5,gender);
	                stmt.setInt(6,Age);
	                for (String lang : languages) { 
	                stmt.setString(7,lang);			        
	                }
	                stmt.setString(8,state);
	               
				   int i=stmt.executeUpdate(); 
	              
				   if((i>0) && (Age>18))
					{
					   res.sendRedirect("details.html");
					
						  String to="voletidharma993@gmail.com";
			    	      String subject="Register Details";
			    	      String message="Hi, you are registered to us:";
			    	      String user="voletidharma993@gmail.com";
			    	      String pass="dharma1993";
			    		  sendMail.send(to,subject, message, user, pass);
						
						}
					else {
						pw.println("Invalid input age should be greater than 18");
						pw.print("<a href='regform.html'>GoToMain</a></body></html>");
					}
						
				    	
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		
	}

	}
