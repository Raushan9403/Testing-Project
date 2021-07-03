package com.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertTestSurrugatekey {
     private static final String SUDENT_INSERT_QUERY="INSERT INTO STUDENTS(SNAME,SADD,AVGNO) VALUES(?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			int count=0;
			if(sc!=null) {
				System.out.println("Enter students count");
				count=sc.nextInt();
			}//if
			
			//register jdbc driver class loading driver class
			//Class.forName("com.myslq.cj.jdbc.Driver");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///students", "root", "system");

			//Create PreparedStatment Statement obj having pre-compiled sql query
			ps=con.prepareStatement(SUDENT_INSERT_QUERY);
			
			//read inputs values from end user, seat them to query Param values and executed the pre-compiled 
			//SQL query for multiple times
			if(ps!=null && sc!=null) {
				for(int i=1;i<=count;++i) {
					//read each student input values
					System.out.println("enter"+i+"students details");
					System.out.println("enter student name");
					String sname=sc.next();
					System.out.println("enter student address");
					String sadd=sc.next();
					System.out.println("enter student avrage number");
					int avgno=sc.nextInt();
					
					//seat each students deatils as pre-compiled sql  query params
					 ps.setString(1, sname); ps.setString(2, sadd);ps.setInt(3, avgno);
					
					//execute pre-compiled query sql each time
					int result=ps.executeUpdate();
					
					//process execution result of pre-compiled-sql query
					if(result==0)
						System.out.println(i+"Students details not inserted");
					else
						System.out.println(i+"Students details are inserted");
				}//for
			}//if
		}//try
		catch(SQLException se) {
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
		}//Finally
		}//main
}//class
