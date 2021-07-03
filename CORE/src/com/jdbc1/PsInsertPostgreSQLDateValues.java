package com.jdbc1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsInsertPostgreSQLDateValues {
private static final String INSERT_EMP_QUERY="INSERT INTO EMPOLYEE_INFO  VALUES(NEXTVAL('EMP_ID_SEQ'),?,?,?)";
	public static void main(String[] args) {
		
        Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		try {
		sc=new Scanner(System.in);
				   String Ename=null,DOB=null,Job= null;
				   if(sc!=null) {
					   System.out.println("Enter Employee name::");
					   Ename=sc.next();
					   System.out.println("Enter Employee DOB in dd-MM-yyyy::");
					   DOB=sc.next();
					   System.out.println("Enter Employee job ::");
					   Job=sc.next();
				   }//if) {
			//read inputs
		   
		   //convert string date values to java.sql.date class obj
		   // For PDOB(dd-MM-yyyy) 
		   //Convert String Date vlues to java.util.Date class obj
		   SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
		   java.util.Date udob=sdf1.parse(DOB);
		   //Convert java.util.Date Class obj to java.sql.Date class obj
		   long ms=udob.getTime();
		   java.sql.Date sqdob=new java.sql.Date(ms);
		   //Establish the Connection
		   con=DriverManager.getConnection("jdbc:postgresql:NTAJ","postgres","tiger");
		   
		   //Create Prepared Statement obj
		   if(con!=null)
			   ps=con.prepareStatement(INSERT_EMP_QUERY);
		   
		   //Set values to pre_compiled query in sql query
		   if(ps!=null) {
		   ps.setString(1, Ename);
		   ps.setDate(2,sqdob);
		   ps.setString(3, Job);
		   }
		   //execute query
		   int count=0;
		   if(ps!=null)
			   count=ps.executeUpdate();
		   //process the result
		   if(count==0)
			   System.out.println("Record is not inserted");
		   else
			   System.out.println("Record is inserted");
		   		}//try
		catch (SQLException se) {
			se.printStackTrace();
			System.out.println("Problem in record insertion");
				}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally	{
			//close jadbc obj
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
