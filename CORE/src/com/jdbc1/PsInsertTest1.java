package com.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertTest1 {
private static final String COUSTOMER_INSERT_QUERY="INSERT INTO CUSTOMER VALUES(?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			int count=0;
			if(sc!=null) {
				System.out.println("Enter Coustomer Count");
				count=sc.nextInt();
				}//if
			
			//register jdbc Driver for loading jdbc driver class
			//Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establish the Connection 
			con=DriverManager.getConnection("jdbc:mysql:///db_ed","root","system");
			
			//Create PreaparedStatement obj having pre-compiled Sql query
			ps=con.prepareStatement(COUSTOMER_INSERT_QUERY);
			
			//read inputs values end user , seat the to query param values and executed the pre-compiled
			//Sql query fro multiple times
			if(ps!=null && sc!=null) {
				for(int i=1;i<=count;i++) {
					//read each customer values
					System.out.println("Enter"+i+"Coustomer details");
					System.out.println("Enter Coustomer number::");
					int cno=sc.nextInt();
					System.out.println("Enter Coustomer name::");
					String cname=sc.next();
					System.out.println("Enter Coustomer address::");
					String cadd=sc.next();
					System.out.println("Enter Coustomer mobile number::");
					double cmo_no=sc.nextDouble();
					
					//seat each customer details as a pre-compiled sql query param
					ps.setInt(1, cno); ps.setString(2, cname); ps.setString(3, cadd); ps.setDouble(4, cmo_no);
					
					//execute pre-compiled sql query each time
					int result=ps.executeUpdate();
					
					//Process execution result of pre-compiled sql query
					if(result==0)
						System.out.println(i+"Coustomer details  is not inserted");
					else
						System.out.println(i+"Coustomer details  are inserted");
				}//for
			}//if
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
				if(ps!=null)
					ps.close();
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
