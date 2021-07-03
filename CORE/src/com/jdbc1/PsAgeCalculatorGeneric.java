package com.jdbc1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsAgeCalculatorGeneric {
  private static final String GET_DOB="SELECT PDOB FROM PERSION_INFO_DATES WHERE PID=?";

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
	                    	 ps=con.prepareStatement(GET_DOB);
	                    	 
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
	                     		java.sql.Date sqdob=rs.getDate(1);
	                     		java.util.Date sysdate=new java.util.Date();
	                     		float age=(sysdate.getTime()-sqdob.getTime())/(1000.0f*60.0f*60.0f*24.0f*365.25f);
	                     		/*DecimalFormat df=new DecimalFormat();
	                     		df.setMaximumFractionDigits(2);*/
	                     		DecimalFormat df=new DecimalFormat("#.##");
	                     		System.out.println("person age"+df.format(age));
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
