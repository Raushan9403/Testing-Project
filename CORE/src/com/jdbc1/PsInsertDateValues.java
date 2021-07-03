package com.jdbc1;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsInsertDateValues {
private static final String INSERT_DATE_QUERY="INSERT INTO PERSION_INFO_DATES(PNAME,PDOB,PDOJ,PDOM) VALUES(?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		try {
			//read inputs
		   sc=new Scanner(System.in);
		   String Pname=null,PDOB=null,PDOJ = null,PDOM=null;
		   if(sc!=null) {
			   System.out.println("Enter Person name::");
			   Pname=sc.next();
			   System.out.println("Enter Person DOB in dd-MM-yyyy::");
			   PDOB=sc.next();
			   System.out.println("Enter Person DOJ in yyyy-MM-dd::");
			   PDOJ=sc.next();
			   System.out.println("Enter Person DOM in MMM-dd-yyyy::");
			   PDOM=sc.next();
		   }//if
		   //convert string date values to java.sql.date class obj
		   // For PDOB(dd-MM-yyyy) 
		   //Convert String Date vlues to java.util.Date class obj
		   SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
		   java.util.Date updob=sdf1.parse(PDOB);
		   //Convert java.util.Date Class obj to java.sql.Date class obj
		   long ms=updob.getTime();
		   java.sql.Date sqpdob=new java.sql.Date(ms);
		   
		 //For PDOJ(yyyy-MM-dd)
		  //converting string date values to java.sql.Date class obj
		   java.sql.Date sqpdoj=java.sql.Date.valueOf(PDOJ);
		   
		 // For PDOM(MMM-dd-yyyy)
		   //Convert String Date vlues to java.util.Date class obj
		   SimpleDateFormat sdf2=new SimpleDateFormat("MMM-dd-yyyyy");
		   java.util.Date updom=sdf2.parse(PDOM);
		   //Convert java.util.Date Class obj to java.sql.Date class obj
		    ms=updom.getTime();
		   java.sql.Date sqpdom=new java.sql.Date(ms);

		   //register jdbc Driver loading  jdbc Driver class
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   
		   //Establish the Connection
		   con=DriverManager.getConnection("jdbc:mysql:///db_ed","root","system");
		   
		   //Create Prepared Statement obj
		   if(con!=null)
			   ps=con.prepareStatement(INSERT_DATE_QUERY);
		   
		   //Set values to pre_compiled query in sql query
		   if(ps!=null) {
		   ps.setString(1, Pname);
		   ps.setDate(2,sqpdob);
		   ps.setDate(3, sqpdoj);
		   ps.setDate(4, sqpdom);
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
