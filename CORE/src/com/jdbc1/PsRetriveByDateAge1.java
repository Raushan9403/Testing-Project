package com.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsRetriveByDateAge1 {
//   private static final String RETRIVE_DATE_QUERY="SELECT TIMESTAMPDIFF(YEAR,PDOB,CURDATE()) FROM PERSION_INFO_DATES WHERE PID=?";
   private static final String RETRIVE_DATE_QUERY="SELECT TIMESTAMPDIFF(DAY,PDOB,CURDATE())/365.25 FROM PERSION_INFO_DATES WHERE PID=?";

   public static void main(String[] args) {
  		            Connection con=null;
		            PreparedStatement ps=null;
		       		  Scanner sc=null;
		       		  ResultSet rs=null;
		       		  try {
	                    	 //read inputs from enduser
	                    	 sc=new Scanner(System.in);
	                    	 int  pid=0;
	                    	 if(sc!=null) {
	                    		 System.out.println("Enter  Persion PID ::");
	                    		 pid=sc.nextInt();
	           	              }//if
	                    	   //Register jdbc Driver loading Jdbc driver class
	                    	 Class.forName("com.mysql.cj.jdbc.Driver");
	                    	 
	                    	 //Establish the Connection
	                    	 con=DriverManager.getConnection("jdbc:mysql:///db_ed","root","system");
	                    	 
	                    	 //Create Prepared Statment Obj
	                    	 if(con!=null)
	                    	 ps=con.prepareStatement(RETRIVE_DATE_QUERY);
	                    	 
	                    	 //Seat value query Param
	                    	 if(ps!=null) {
	                    		 ps.setInt(1, pid);
	                           }
	                    	 //execute query
	                    	
	                    	 if(ps!=null) 
	                          rs=ps.executeQuery();
	                  //process the result
	                     	 if(rs!=null) {
	                     		 if(rs.next()) {
	                     		 float age=rs.getFloat(1);
	                     		 System.out.println("person age::"+age);
	                     		 }//if
	                     		 else {
	                     			 System.out.println("person age is not count");
	                     		 }
	                         }//if
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
