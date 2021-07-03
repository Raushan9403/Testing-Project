package com.jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertTestSurrugatePKPostgreSQL {
     private static final String PRODUT_INSERT_QUERY="INSERT INTO PRODUT_INFO VALUES(NEXTVAL('PID_SEQ'),?,?,?,?)";
	public static void main(String[] args) {
		//read inputs
		try(Scanner sc=new Scanner(System.in);) {//try1
				int count=0;
				if(sc!=null) {
					System.out.println("Enter students count");
					count=sc.nextInt();
				}//if
				//establish the connection
			try(Connection con=DriverManager.getConnection("jdbc:postgresql:NTAJ", "postgres", "tiger");
			//Create PreparedStatment Statement obj having pre-compiled sql query
		 PreparedStatement	ps=con.prepareStatement(PRODUT_INSERT_QUERY);){//try2
			//read inputs values from end user, seat them to query Param values and executed the pre-compiled 
			//SQL query for multiple times
			if(ps!=null && sc!=null) {
				for(int i=1;i<=count;++i) {
					//read each student input values
					System.out.println("enter"+i+"produt details");
					System.out.println("enter produt name::");
					String pname=sc.next();
					System.out.println("enter produt price::");
					float pp=sc.nextFloat();
					System.out.println("enter produt quantity::");
					int pq=sc.nextInt();
					System.out.println("enter produt status::");
					String pss=sc.next();
					//seat each students deatils as pre-compiled sql  query params
					 ps.setString(1, pname); ps.setFloat(2, pp);ps.setInt(3, pq);ps.setString(4, pss);
					//execute pre-compiled query sql each time
					int result=ps.executeUpdate();
					//process execution result of pre-compiled-sql query
					if(result==0)
						System.out.println(i+"Produt details not inserted");
					else
						System.out.println(i+"Produt details are inserted");
				}//for
			}//if
		}//try2
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("problem in record insertion");
		}
		catch (Exception e) {
		       e.printStackTrace();
		   	System.out.println("problem in app");
		}
	}//main
}//class