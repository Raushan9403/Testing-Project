package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//JDBC App to Update Student record based given student number(sno)
public class UpdateTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		
		try {
			//read inputs from end user
			sc=new Scanner(System.in);
			String sname=null,sadd=null;
			int avgno=0,sno=0;
			if(sc!=null) {
			
				
				System.out.println("Enter Student  new name sname::");
				sname=sc.nextLine();//gives Ram RP
				
				System.out.println("Enter Student  new address sadd::");
				sadd=sc.nextLine();//gives navi mumbi
				
				System.out.println("Enter Student average number avgno::");
				avgno=sc.nextInt();//gives 20
				
				System.out.println("Enter Student number sno::");
				sno=sc.nextInt();//gives 3
			  }
			
			//Convert jdbc code belong sql query
                sname="'"+sname+"'";
               sadd="'"+sadd+"'";
			//Register JDBC Driver by loading  JDBC Driver Class
			//Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establish the Connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","system");
			
			//Create Statement Object
			if(con!=null)
				st=con.createStatement();
			
			//Prepare SQL query
			//update students set sname='DIMPLE' ,sadd='MUMBI' ,avgno=20 where sno=;
			String query="UPDATE STUDENTS SET SNAME="+sname+",SADD="+sadd+",AVGNO="+avgno+" WHERE SNO="+sno;
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
			else if(se.getErrorCode()==22001)
				System.out.println("Dont insert more then column size data to sname or sadd columns");
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
