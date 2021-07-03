package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//JDBC App to delete student record based given city(sadd)
public class DeleteTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		
		try {
			//read inputs from end user
			sc=new Scanner(System.in);
			String sadd=null;
			if(sc!=null) {
				System.out.println("Enter Student address sadd::");
				sadd=sc.next();//gives hzp
			  }
			
			//Convert jdbc code as required for sql query
			sadd="'"+sadd+"'";
			
			//Register JDBC Driver by loading  JDBC Driver Class
			//Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establish the Connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","system");
			
			//Create Statement Object
			if(con!=null)
				st=con.createStatement();
			
			//Prepare SQL query
			//delete from students where sadd='HZP';
			String query="DELETE FROM STUDENTS WHERE SADD="+sadd;
			System.out.println(query);
			
			//Send and Execute Sql query in db s/w
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			
			//Process the Result
			if(count==0)
				System.out.println("No record to found to delete");
			else
				System.out.println("no of records that are affected::"+count);
			}//try
		catch (SQLException se) {// handle known exception
			if(se.getErrorCode()>=1050 && se.getErrorCode()<=1050)
				System.out.println("Invalid table name or column name sql keyword");
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//Close JDBC Obj
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
