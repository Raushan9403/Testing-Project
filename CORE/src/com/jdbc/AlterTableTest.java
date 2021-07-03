package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AlterTableTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		try {
			//Rgister JDBC Driver loading jdbc driver class
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//establish the Connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ed","root","system");
			//Create Statement object
			if(con!=null)
				st=con.createStatement();
			//Prapare Sql query
			String sql="ALTER TABLE REGISTRATION  ADD  FATHER  VARCHAR(250)";
			System.out.println(sql);
	          //send and execute sql query in db s/w
			int count=0;
			if(st!=null) 
				count=st.executeUpdate(sql);
			System.out.println("count::"+count);
			if(count==0)
				System.out.println("  add column in Resistration table");
			else
				System.out.println("not  add column in Resistration table");
	}//try
		catch (SQLException se) {
                 se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc obj
			try {
				if(st!=null)
					st.close();
			}//try
			catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}//try
			catch (SQLException se) {
				se.printStackTrace();
			}
		}//finally
		
	}//main

}//class
