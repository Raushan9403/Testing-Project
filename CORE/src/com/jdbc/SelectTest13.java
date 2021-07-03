package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//JDBC App that gives Employee Details who have nth highest salary
public class SelectTest13 {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
	         sc=new Scanner(System.in);
	         int nth=0;
	         if(sc!=null) {
	         System.out.println("Enter nth highest salary::");
	         nth=sc.nextInt();//gives 2th
	         }//if
	         
	         //load JDBC driver class
	         //Class.forName("com.mysql.cj.jdbc.Driver");
			
	         //Establish the Connection
	         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ed","root","system");
	         
	         //Create Statement Obj
	         if(con!=null)
	        	 st=con.createStatement();
	         
	         //Prepare Sql query
	        // select emp_no, name,salary,design from employee e1 where N-1=(select count(distinct salary)from employee e2 where e2.salary>e1.salary)
	         String query=" select emp_no, name,salary,design from employee e1 where "+nth+"-1=(select count(distinct salary)from employee e2 where e2.salary>e1.salary)";
			System.out.println(query);
			
			//Send and execute SQL query in Db s/w
			if(st!=null)
				rs=st.executeQuery(query);
			
			//Process the ResultSet (o or more records)
			if(rs!=null) {
				boolean flag=false;
				while (rs.next()) {
					flag=true;
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
				}//while
				if(flag==false) 
					System.out.println("no record found");
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//Close JDBC obj
			try {
				if(rs!=null)
					rs.close();
				}//
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
				}//
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
				}//
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(sc!=null)
					sc.close();
				}//
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class
