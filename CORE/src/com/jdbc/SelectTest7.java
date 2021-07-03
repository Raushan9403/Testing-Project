package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//JDBC APP that gets Students details  based on given sadd
public class SelectTest7 {

	public static void main(String[] args) {
		//read inputs from end user
		Scanner sc=null;
		String sadd1=null,sadd2=null,sadd3=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Students sadd1::");
				sadd1=sc.next().toUpperCase();//gives "hyd","HYD";
				
				System.out.println("Enter Students sadd2::");
				sadd2=sc.next().toUpperCase();//gives "hzp","HZP";

				System.out.println("Enter Students sadd3::");
				sadd3=sc.next().toUpperCase();//gives "mzp","MZP";
			}//if
			
			//Convert input values as requied for sql query
			sadd1="'"+sadd1+"'";//gives "hyd","HYD";
		    sadd2="'"+sadd2+"'";//gives "hzp","HZP";
		    sadd3="'"+sadd3+"'";//gives "mzp","MZP";
		    
		    //Load JDBC Driver class
		    //Class.forName("com.mysql.cj.jdbc.Driver");
			
		    //establish the Connection
		    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","system");
		    
		    //Create Statement Object
		    if(con!=null)
		    	st=con.createStatement();
		    
		    //Prepare sql query 
		   // select sno,sname,sadd,avgno from students where sadd in('hyd','HYD','mzp','MZP','hzp','HZP') order by sadd;
		    String query="select sno,sname,sadd,avgno from students where sadd in("+sadd1+","+sadd2+","+sadd3+") order by sadd";
		    System.out.println(query);
            
		    //Send and Execute sql query in db s/w
		    if(st!=null)
		    	rs=st.executeQuery(query);
		    
		    if(rs!=null){
		    	System.out.println("The Students details are:");
		    	while(rs.next()!=false){
		    		System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));
		    	}//while
		    }//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//Close JDBC OBJ stream obje
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
