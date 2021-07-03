package com.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class PsRetriveDateValue {
   private static final String RETRIVE_DATE_QUERY="SELECT PID,PNAME,PDOB,PDOJ,PDOM FROM PERSION_INFO_DATES";
	public static void main(String[] args) {
                           
		            Connection con=null;
		            PreparedStatement ps=null;
		            ResultSet rs=null;
		
	                     try {
	                    	 //Register jdbc Driver loading Jdbc driver class
	                    	 Class.forName("com.mysql.cj.jdbc.Driver");
	                    	 
	                    	 //Establish the Connection
	                    	 con=DriverManager.getConnection("jdbc:mysql:///db_ed","root","system");
	                    	 
	                    	 //Create Prepared Statment Obj
	                    	 if(con!=null)
	                    	 ps=con.prepareStatement(RETRIVE_DATE_QUERY);
	                    	 
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
	                    		 while(rs.next()) {
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
	                     }//finally
	}//main
}//class
