package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//JDBC App to hike employee salary by given percentage for given 3 design
public class UpdateTest1 {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		
		try {
			//read input form end user
			sc=new Scanner(System.in);
			String desi1=null,desi2=null,desi3=null;
			float hike_percentage=0.0f;
			
			if(sc!=null) {
				System.out.println("Enter employee desi1");
				desi1=sc.next(); //gives PIRAMID
				System.out.println("Enter employee desi2");
				desi2=sc.next(); //gives HEXAGONAL
				System.out.println("Enter employee desi3");
				desi3=sc.next(); //gives TRIGONAL
				System.out.println("Enter employee salary hike");
				hike_percentage=sc.nextFloat(); //gives 10
			}//if

			//Convert input values as requird fro sql query
			desi1="'"+desi1+"'";
			desi2="'"+desi2+"'";
			desi3="'"+desi3+"'";
			
			//register JDBC driver by loading JDBC Driver class
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//Establish the Connection
			con=DriverManager.getConnection("jdbc:mysql:///db_ed","root","system");
			//Create Statement obj
			if(con!=null)
				st=con.createStatement();
			
			//Prepare Sql query
			//update employee set salary=salary+(salary*10/100) where design in('PIRAMID','HEXAGONAL','TRIGONAL');
			String query="UPDATE EMPLOYEE SET SALARY=SALARY+(SALARY*"+hike_percentage/100+" ) WHERE DESIGN IN("+desi1+","+desi2+","+desi3+")";
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
