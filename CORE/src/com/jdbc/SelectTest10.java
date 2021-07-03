package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//JDBC App to get count of records from student db table
public class SelectTest10 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//load the JDBC Driver class
			//Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establish the connection
			//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","system");
			con=DriverManager.getConnection("jdbc:mysql:///students","root","system");
			//Create Statement obj
			if(con!=null)
				st=con.createStatement();
			
			//Prepare SQL query
			//select count(*) from students;
			String query="SELECT COUNT(*) FROM STUDENTS";
			System.out.println(query);
			
			//send and execute sql query in db s/w
			if(st!=null)
				rs=st.executeQuery(query);
			
			//Process the ResultSet (1 record)
			if(rs!=null) {
				rs.next();
				//int count=rs.getInt(1);
				int count=rs.getInt("COUNT(*)");
				System.out.println("Record count student db table::"+count);
				
			}//if
		}//try
		catch(SQLException se) {//to handle known exception
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			//Close JDBC obj
			try {
				if(rs!=null)
					rs.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
		
		}//finally
		

	}//main

}//class
