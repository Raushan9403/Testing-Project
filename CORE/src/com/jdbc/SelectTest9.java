package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//JDBC App that give the Department from dept_id based on the given dept_id
public class SelectTest9 {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			//read inputs from enduser
			sc=new Scanner(System.in);
			int dept_id=0;
			if(sc!=null) {
				System.out.println("Enter the dept_id::");
				dept_id=sc.nextInt();//gives 1001
			}
			//load JDBC driver class
			//Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establish the Connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ed","root","system");
			
			//Create JDBC Statement Obj
			if(con!=null)
				st=con.createStatement();
			
			//prepare sql query
			 //select  dept_id,dname,loc from department where dept_id=1001;
			String query="SELECT  DEPT_ID,DNAME,LOC FROM DEPARTMENT WHERE DEPT_ID="+dept_id;
			System.out.println(query);
			
			//send and execute sql query in db s/w
			if(st!=null)
				rs=st.executeQuery(query);
			
			//Process the ResultSet (0 or 1) record
			if(rs!=null) {
				if(rs.next())
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
				else
					System.out.println("NO RECORD FOUND");
				
			}//if
			
		}//try
		catch(SQLException se) {//to handle known Exception
			se.printStackTrace();//to give detail info raised exception
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//Close JDBC obj
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
