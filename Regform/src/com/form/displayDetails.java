package com.form;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class displayDetails extends HttpServlet 
{

	private static final long serialVersionUID = -1352121822909852502L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String sqlquery;
		String mobileno;
		 PrintWriter out=response.getWriter();
		 response.setContentType("text/html");
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			
			 PreparedStatement stmt=con.prepareStatement("select * from regtable");
			 ResultSet rs;
			 rs=stmt.executeQuery();
			 
			 out.append("<html><body>");
			 out.append("<h2> User Details </h2>");
			 out.append("<table border=1\"><tr>");
			 out.append("<tr><th>name</th> <th>mobileno</th> <th>email</th> <th>dob</th> <th>gender</th> <th>age</th> <th>languge</th> <th>state</th></tr>"); 
			 while(rs.next())
			 {
			
				 out.append("<td>"+rs.getString(1)+"</td>");
				 out.append("<td>"+rs.getString(2)+"</td>");
				 out.append("<td>"+rs.getString(3)+"</td>");
				 out.append("<td>"+rs.getString(4)+"</td>");
				 out.append("<td>"+rs.getString(5)+"</td>");
				 out.append("<td>"+rs.getString(6)+"</td>");
				 out.append("<td>"+rs.getString(7)+"</td>");
				 out.append("<td>"+rs.getString(8)+"</td>");
				 out.append("</tr>");
			 }
			 out.append("</table><br>");
			 out.append("<a href='regform.html'>GoToMain</a></body></html>");
			
		}
	
		catch(Exception e)
		{
			
		}
			
	}
		}
// just changed the comment
