package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp {
	
		public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//read inputs from end user
			sc=new Scanner(System.in);
			String user=null,pass=null;
			if(sc!=null) {
				System.out.println("Enter login username::");
				user=sc.nextLine();//gives Deepak paswan
				System.out.println("Enter login pasword::");
				pass=sc.nextLine();//gives paswan D
				}//if
			
			//convet input values as required for sql query
			user="'"+user+"'";//gives 'Deepak paswan'
			pass="'"+pass+"'";//gives 'paswan D'
			
			//Load Jdbc driver class
			//Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///db_ed","root","system");
			
			//Create JDBC Statement Obj
			if(con!=null)
				st=con.createStatement();
			
			//Prepare Sql query
			//select count(*) from irtc_tab where uname='king' and pwd='kingdom'
			String query="SELECT COUNT(*) FROM IRTC_TAB WHERE UNAME="+user+" AND PWD="+pass;
			System.out.println(query);
			
			//Send and execute the sql query in db s/w
			if(st!=null)
				rs=st.executeQuery(query);
			
			//Process the ResultSet obj
			if(rs!=null) {
				rs.next();
				int count=rs.getInt(1);
				
				//process the result
				if(count==0)
					System.out.println("INVALID CREADITALS");
				else
					System.out.println("VALID CREADITALS");
			}//if
			}//try
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close all jdbc obj
			try {
				if(rs!=null)
					rs.close();
			}//try
				catch (SQLException se) {
					se.printStackTrace();
				}
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

