package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//JDBC App to Insert record into  Students db table by collecting inputs from enuser 
public class InsertTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		
		try {
			//read input form end user
			sc=new Scanner(System.in);
			String sname=null,sadd=null;
			int sno=0,avgno=0;
			
			if(sc!=null) {
			System.out.println("Enter students number:");
			sno=sc.nextInt();//gives 09
			System.out.println("Enter students name:");
			sname=sc.next();//gives rama
			System.out.println("Enter students address:");
			sadd=sc.next();//gives vizag
			System.out.println("Enter students avrage number:");
			avgno=sc.nextInt();//gives 20
			}//if

			//Convert input values as requird fro sql query
			sname="'"+sname+"'";
			sadd="'"+sadd+"'";
			
			//register JDBC driver by loading JDBC Driver class
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//Establish the Connection
			con=DriverManager.getConnection("jdbc:mysql:///students","root","system");
			//Create Statement obj
			if(con!=null)
				st=con.createStatement();
			
			//Prepare Sql query
			// insert students values(6,'RAMA','VIZAG',20);
			String query="INSERT STUDENTS VALUES("+sno+","+sname+"," +sadd+","+avgno+")";
			System.out.println(query);
			
			//send and excute sql query in db s/w
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			
			//Process the Result
			if(count==0)
				System.out.println("Record not inserted");
			else
				System.out.println("Record inserted::"+count);
          }//try
		catch(SQLException se) {
			if(se.getErrorCode()>=1050 && se.getErrorCode()<=1050)
				System.out.println("invalid table name or column name  sql keyword");
			if(se.getErrorCode()==22001)
				System.out.println("Dont insert more then cloumn size data to design");
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//Close all jdbc obj
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
