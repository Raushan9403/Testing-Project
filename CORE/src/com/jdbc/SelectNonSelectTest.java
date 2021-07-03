package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectNonSelectTest {
	

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		//read inputs 
		try {
               sc=new Scanner(System.in);
               int no=0;
               String query=null;
               if(sc!=null) {
               System.out.println("Enter Sql query (Select or non_Select::)");
               query=sc.nextLine();
               }//read inputs
			//Register JDBC Driver loading JDBC driver class
               //Class.forName("com.mysql.cj.jdbc.Driver");
               
               //Esatablish the Connection 
               con=DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","system" );
               
               //create statement obj
               if(con!=null)
            	   st=con.createStatement();
               //send and execute sql query in db s/w
               if(st!=null) {
            	   boolean flag=st.execute(query);
            	   System.out.println(flag);
            	   if(flag==true) {
            		   System.out.println("Select sql query is executed");
            		   //Gather and process the resultset
            		    rs=st.executeQuery(query);
            		    //process the result set obj
            		    if(rs!=null) {
            		    	while(rs.next()) {
            		    		System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
            		    	}//while
            		    }//if
            	   }//if
            	   else {
            		   System.out.println("Non-select sql query is executed");
            		   //gather the result
            		   int count=st.getUpdateCount();
            		   System.out.println("no of records that are effected::"+count);
            	   }//else
               }//if
		}//try
		catch (SQLException se) {
			if(se.getErrorCode()>=1050 && se.getErrorCode()<=1050)
				System.out.println("invalid table name or column name  sql keyword");
			if(se.getErrorCode()==22001)
				System.out.println("Dont insert more then cloumn size data to design");
			se.printStackTrace();
     	}
		catch (Exception e) {
			e.printStackTrace();
		}
		//close jdbc obj
		finally {
			try {
				if(rs!=null)
					rs.close();
			}//try
			catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
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
