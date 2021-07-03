package com.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsRetriveByDateAge {
   private static final String RETRIVE_DATE_QUERY="SELECT  DATE_FORMAT(FROM_DAYS(DATEDIFF(NOW(), ?)), '%Y')+0 AGE";
   		public static void main(String[] args) {
  		            Connection con=null;
		            PreparedStatement ps=null;
		       		  Scanner sc=null;
		       		  ResultSet rs=null;
		       		  try {
	                    	 //read inputs from enduser
	                    	 sc=new Scanner(System.in);
	            	         String dob=null;
	                    	 if(sc!=null) {
	                    		 System.out.println("Enter  Persion DOB in dd-MM-yyyy:: ");
	                    		 dob=sc.next();
	           	              }//if
	                    	   //convert string date values to java.sql.date class obj
	              		   // For DOB(dd-MM-yyyy) 
	              		   //Convert String Date vlues to java.util.Date class obj
	              		   SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
	              		   java.util.Date udob=sdf.parse(dob);
	              		   //Convert java.util.Date Class obj to java.sql.Date class obj
	              		   long ms=udob.getTime();
	              		   java.sql.Date sqdob=new java.sql.Date(ms);
	                    	 
	                    	 //Register jdbc Driver loading Jdbc driver class
	                    	 Class.forName("com.mysql.cj.jdbc.Driver");
	                    	 
	                    	 //Establish the Connection
	                    	 con=DriverManager.getConnection("jdbc:mysql:///db_ed","root","system");
	                    	 
	                    	 //Create Prepared Statment Obj
	                    	 if(con!=null)
	                    	 ps=con.prepareStatement(RETRIVE_DATE_QUERY);
	                    	 
	                    	 //Seat value query Param
	                    	 if(ps!=null) {
	                    		 ps.setDate(1, sqdob);
	                    	 }
	                    	 //execute query
	                    	
	                    	 if(ps!=null) 
	                          rs=ps.executeQuery();
	                  //process the result
	                    	 if(rs!=null) {
	                    		 rs.next();
	                    		 int count=rs.getInt(1);
	                    		 System.out.println("age count ::"+count);
	                    	 }
	                     }//try
	                     catch (SQLException se) {
						se.printStackTrace();
						System.out.println("Problem in record insertion");
						}
	                     catch (Exception e) {
	                    e.printStackTrace();	 
						}
	                     finally {
	                    	 //close all jdbc obj
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
