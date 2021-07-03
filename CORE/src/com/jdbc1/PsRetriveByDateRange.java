package com.jdbc1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsRetriveByDateRange {
   private static final String RETRIVE_DATE_QUERY="SELECT PID,PNAME,PDOB,PDOJ,PDOM FROM PERSION_INFO_DATES WHERE PDOB>=? AND PDOB<=?";
	public static void main(String[] args) {
                           
		            Connection con=null;
		            PreparedStatement ps=null;
		            ResultSet rs=null;
		            Scanner sc=null;
		
	                     try {
	                    	 //read inputs from enduser
	                    	 sc=new Scanner(System.in);
	                    	 String sdob=null,edob=null;
	                    	 if(sc!=null) {
	                    		 System.out.println("Enter  Start DOB:: ");
	                    		 sdob=sc.next();
	                    		 System.out.println("Enter  End DOB:: ");
	                    		 edob=sc.next();
	                    	 }//if
	                    	 //Convert String date value to java.sql.util class boj
	                    	 SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
	                    	 java.sql.Date ssqdob=new java.sql.Date(sdf1.parse(sdob).getTime());
	                    	 java.sql.Date sqedob=new java.sql.Date(sdf1.parse(edob).getTime());
	                    	 //Register jdbc Driver loading Jdbc driver class
	                    	 Class.forName("com.mysql.cj.jdbc.Driver");
	                    	 
	                    	 //Establish the Connection
	                    	 con=DriverManager.getConnection("jdbc:mysql:///db_ed","root","system");
	                    	 
	                    	 //Create Prepared Statment Obj
	                    	 if(con!=null)
	                    	 ps=con.prepareStatement(RETRIVE_DATE_QUERY);
	                    	 
	                    	 //Seat value query Param
	                    	 if(ps!=null) {
	                    		 ps.setDate(1,ssqdob);
	                    		 ps.setDate(2, sqedob);
	                    		
	                    	 }
	                    	 
	                    	 //execute query
	                    	 if(ps!=null)
	                    		 rs=ps.executeQuery();
	                    	 
	                    	 /*if(rs!=null) {
	                    		 while(rs.next()) {
	                    			 System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
	                    		 }//while
	                    	 }//if*/
	                    	 System.out.println("<------------------------------------------------>");
	                    	 if(rs!=null) {
	                    		 boolean flag=false;
	                    		 while(rs.next()) {
	                    			 flag=true;
                                   int pid=rs.getInt(1);
                                   String pname=rs.getString(2);
                                   java.sql.Date sqpdob=rs.getDate(3);
                                   java.sql.Date sqpdoj=rs.getDate(4);
                                   java.sql.Date sqpdom=rs.getDate(5);
                                   
                                   //convert java.sql.Date class obj to String date values
                                   SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
                                   String spdob=sdf.format(sqpdob);
                                   String spdoj=sdf.format(sqpdoj);
                                   String spdom=sdf.format(sqpdom);
                                   System.out.println(pid+"  "+pname+"  "+spdob+"  "+spdoj+"  "+spdom);
	                    		 }//while
	                    		 if(flag==false)
	                    			 System.out.println("no record found");
	                    	 }//if
	   
	                     }//try
	                     catch (SQLException se) {
						se.printStackTrace();
						}
	                     catch (Exception e) {
	                    e.printStackTrace();	 
						}
	                     finally {
	                    	 //close all jdbc obj
	                    	 try {
	                    		 if(rs!=null)
	                    			 rs.close();
	                    	 }//try
	                    	 catch (SQLException se) {
								se.printStackTrace();
							}
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
