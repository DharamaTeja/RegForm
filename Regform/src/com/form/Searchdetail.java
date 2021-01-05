package com.form;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Searchdetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
  

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out=res.getWriter();
String mobileno=req.getParameter("MobileNo");
String sqlquery="select * from regtable where mobileno='"+mobileno+"'";
System.out.println(sqlquery);
try {
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
Statement stmt=con.createStatement();
ResultSet rs;
rs=stmt.executeQuery(sqlquery);
 
if(rs.next())
{
	out.append("<html><body>");
	out.append("<h2> User Details </h2>");
	out.append("<table border=1\"><tr>");
	out.append("<tr><th>name</th> <th>mobileno</th> <th>email</th> <th>dob</th> <th>gender</th> <th>age</th> <th>languge</th> <th>state</th></tr>"); 
		
	
	 out.append("<td>"+rs.getString(1)+"</td>");
	 out.append("<td>"+rs.getString(2)+"</td>");
	 out.append("<td>"+rs.getString(3)+"</td>");
	 out.append("<td>"+rs.getString(4)+"</td>");
	 out.append("<td>"+rs.getString(5)+"</td>");
	 out.append("<td>"+rs.getString(6)+"</td>");
	 out.append("<td>"+rs.getString(7)+"</td>");
	 out.append("<td>"+rs.getString(8)+"</td>");
	 out.append("</tr>");
	
	out.append("</table><br>");//end of table

	out.append("<a href='regform.html'>GoToMain</a></body></html>");
}
else
{
	out.println("You dont have records for this mobile numer"+mobileno);
	out.println("Thank you");
	out.println("click the link to go to main and get register");
	out.append("<a href='regform.html'>GoToMain</a></body></html>");
}
}catch (ClassNotFoundException e) {
	
	e.printStackTrace();
} catch (SQLException e) {
	e.printStackTrace();
}

}
}
// completes the project
