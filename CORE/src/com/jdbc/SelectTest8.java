package com.jdbc;
//JDBC Application to get employee details to initial Characters

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest8 {

	public static void main(String[] args) {
		//read inputs from end user
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			sc=new Scanner(System.in);
			String initChars=null;
			if(sc!=null) {
				System.out.println("Enter the employee deatials ename from initchars::");
				initChars=sc.next();//gives a
			}
			
			//convert input values as required for SQL query
			initChars="'"+initChars+"%'"; //gives s%
			
			//Register JDBC driver by loading JDBC driver class
			//Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establish the Connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ed","root","system");
		    
			//Create Statement Object
			if(con!=null)
				st=con.createStatement();
			
			//Prepare SQl query
			// select emp_no,name,salary,design from employee where name like 's%';
			String query="SELECT EMP_NO,NAME,SALARY,DESIGN FROM EMPLOYEE where NAME LIKE "+initChars;
		    System.out.println(query);
		    
		    //Send and Execute SQL query in DB S/W
		    if(st!=null)
		    	rs=st.executeQuery(query);
		    
		    //Process the result set Object
		    if(rs!=null) {
		    	System.out.println("The Employee Details of ename from initialletter");
		    	boolean flag=false;
		    	while(rs.next()) {
		    		flag=true;
		    		System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
		    		
		    	}//while
		    	if(flag==false)
		    		System.out.println("NO Record found");
		    }//if
			
		}//try
		catch(SQLException se) {//to handle known Exception
			if(se.getErrorCode()>=1050 && se.getErrorCode()<=1050)
				System.out.println("Invalid column name and Table name or sql keywords");
			se.printStackTrace();//to give detail info raised exception
		}
		catch(Exception e){
			e.printStackTrace();
		}
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
