package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//JDBC App that gives Employee Details who have highest salary
public class SelectTest11 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//load JDBC driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establish the Conncetion
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ed", "root","system");
			
			//Create Statement Obj
			if(con!=null)
				st=con.createStatement();
			
			//Prepare SQL query
			//select emp_no,name,salary,design from employee where salary=(select max(salary) from employee)
			String query="SELECT EMP_NO,NAME,SALARY,DESIGN FROM EMPLOYEE WHERE SALARY=(SELECT MAX(SALARY) FROM EMPLOYEE)";
			System.out.println(query);
			
			//send and execute sql query in db s/w
			if(st!=null)
				rs=st.executeQuery(query);
			
			//Process the ResultSet (0 or more records)
			if(rs!=null) {
				boolean flag=false;
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
				}//while
				if(flag==false)
				System.out.println("Records not found");
			}//if
		}//try
		catch (SQLException se) {
			       se.printStackTrace();
		}
		catch (Exception e) {
			      e.printStackTrace();
		}
		finally {
			//Close JDBC obj
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
		}//finally
		

	}//main

}//class
