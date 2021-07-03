package com.jdbc;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CreateTableTest1 {
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		Scanner sc=null;
		try {
			//read inputs from end user
			sc=new Scanner(System.in);
			int count=0;
			String query=null;
			if(sc!=null) {
				System.out.println("Enetr Dml query::");
				query=sc.nextLine();
		      }//if
			
			//Rgister JDBC Driver loading jdbc driver class
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//establish the Connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ed","root","system");
			//Create Statement object
			if(con!=null)
				st=con.createStatement();
			
			//send and execute sql query in db s/w
			if(st!=null)
			count=st.executeUpdate(query);
			//Process the Result
			if(count==0)
				System.out.println("table is modified::");
			else
				System.out.println("table is not modified::"+count); 
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
			try {
				if(sc!=null)
					sc.close();
			}//try
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}//finally
		
	}//main
	
	}//class

