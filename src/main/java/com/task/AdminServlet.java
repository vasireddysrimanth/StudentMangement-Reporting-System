package com.task;

import java.io.IOException; 
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class AdminServlet extends HttpServlet {
		public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException
		{
			res.setContentType("text/html");
			PrintWriter pw=res.getWriter();
			String name = req.getParameter("admin_username");
			String pwd = req.getParameter("admin_Password");
			if(name.equals(null)  && pwd  == " ") {
				pw.println("enter your Admin details");
			}
			else if (name.equals("admin") && pwd.equals("Servlet"))
			{
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=
					DriverManager.getConnection("jdbc:mysql://@localhost:3306/srms","root","ajith");
					PreparedStatement pst=con.prepareStatement("select *from StudentData");
					pw.print("<table width = 75% border = 2 border-color=black >");
					ResultSet rs=pst.executeQuery();
					java.sql.ResultSetMetaData rsmd = rs.getMetaData();
					int total = rsmd.getColumnCount();
					pw.print("<tr>");
					for(int i = 1; i<= total-1; i++)
					{
						pw.print("<th>" + rsmd.getColumnName(i) + "</th>");
					}
					pw.print("</tr>");
					while(rs.next())
					{
						pw.println("<tr><td>"+ " " + rs.getInt(1)+"</td><td>"+ " "+ rs.getString(2)+ "</td><td>"+ ""+rs.getString(3)+ "</td><td>"+ " "+rs.getDate(4)+ "</td><td>"+" "+rs.getString(5));
					}
					pw.print("</table>");

						}

				catch(Exception e) {
					e.printStackTrace();
					
				}
			}
			else
			{
				pw.println("Check your Admin Credentials");
			}
		
	}
		}


