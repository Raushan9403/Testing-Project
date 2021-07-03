package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//JDBC App to given percentmarks  Students avgno by given percentage for given 3 address
public class UpdateTest2 {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		
		try {
			//read input form end user
			sc=new Scanner(System.in);
			String sadd1=null,sadd2=null,sadd3=null;
			float marks_percentage=0.0f;
			
			if(sc!=null) {
				System.out.println("Enter Student sadd1");
				sadd1=sc.next(); //gives HZP
				System.out.println("Enter Student sadd2");
				sadd2=sc.next(); //gives HYD
				System.out.println("Enter Students sadd3");
				sadd3=sc.next(); //gives MUMBI
				System.out.println("Enter Students percentmarks");
				marks_percentage=sc.nextFloat(); //gives 10
			}//if

			//Convert input values as requird fro sql query
			sadd1="'"+sadd1+"'";
			sadd2="'"+sadd2+"'";
			sadd3="'"+sadd3+"'";
			
			//register JDBC driver by loading JDBC Driver class
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//Establish the Connection
			con=DriverManager.getConnection("jdbc:mysql:///students","root","system");
			//Create Statement obj
			if(con!=null)
				st=con.createStatement();
			
			//Prepare Sql query
			// update students set avgno=avgno+(avgno*5/100) where sadd in('MUMBI','HYD','HZP');
			String query="UPDATE STUDENTS SET AVGNO=AVGNO+(AVGNO*"+marks_percentage/100+" ) WHERE SADD IN("+sadd1+","+sadd2+","+sadd3+")";
			System.out.println(query);
			
			//send and excute sql query in db s/w
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			
			//Process the Result
			if(count==0)
				System.out.println("No record found for updation");
			else
				System.out.println("no of records that are affected::"+count);
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
