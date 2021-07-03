package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//JDBC APP that gets Employee details  based on given dept_no

public class SelectTest6 {

	public static void main(String[] args) {
		//read inputs from enduser
		Scanner sc=null;
		Object  dept_no=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter dept_no");
				dept_no=sc.nextInt();
			}//if
			
			//load JDBC Driver Class
			//Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establish the Connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ed","root","system");
			
			//Create Statement Object
			if(con!=null)
				st=con.createStatement();
			
			//Prepare sql query
			//select emp_no,name,salary,design,dept_id from employee where dept_id in(1008);
			
			String query="select emp_no,name,salary,design,dept_id from employee where dept_id in("+dept_no+")";
			System.out.println(query);
			
			//Send and Excuete SQL query in db s/w
           if(st!=null)
        	   rs=st.executeQuery(query);
           
           if(rs!=null) {
        	   System.out.println("The employee details throw deptno:");
        	   while(rs.next()!=false) {
        		   System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getInt(5));
        		   
        	   }//while
           }//if
		}//try
		catch(SQLException se) {//to handle  known Exception
			se.printStackTrace(); //gives detail info raised exception
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		//Close JDBC obj and Strem obj
       finally {
    	   try {
    	   if(rs!=null) 
    		   rs.close();
    	   }//try
    	   catch(SQLException se) {
    		   se.printStackTrace();
    	   }
    	   try {
    		   if(st!=null)
    			   st.close();
    	   }//try
    	   catch(SQLException se) {
    		   se.printStackTrace();
    	   }
    	   try {
    		   if(con!=null)
    			   con.close();
    	   }//try
    	   catch(SQLException se) {
    		   se.printStackTrace();
    	   }
    	   try {
    		   if(sc!=null)
    			   sc.close();
    	   }//try
    	   catch(Exception e) {
    		   e.printStackTrace();
    	   }
    		   
    			   
    			   
    			   
    			   
    	   }//finally
    	   
       }//main
		
		

	}//class


