package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//JDBC App to Insert record into  Employee db table only 4 columns(emp_no,name,design,salary) by collecting from enduser
public class InsertTest1 {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		
		try {
			//read input form end user
			sc=new Scanner(System.in);
			String name=null,desi=null;
			int emp_no=0,salary=0;
		   
			
			
			
			if(sc!=null) {
			System.out.println("Enter Employee number:");
			emp_no=sc.nextInt();//gives 112
			System.out.println("Enter Employee name:");
			name=sc.next();//gives rama
			System.out.println("Enter employee salary:");
			salary=sc.nextInt();//gives 2300
			System.out.println("Enter Employee design:");
			desi=sc.next();//gives CIRCLE
			}//if

			//Convert input values as requird fro sql query
			name="'"+name+"'";
			desi="'"+desi+"'";
			
			//register JDBC driver by loading JDBC Driver class
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//Establish the Connection
			con=DriverManager.getConnection("jdbc:mysql:///db_ed","root","system");
			//Create Statement obj
			if(con!=null)
				st=con.createStatement();
			
			//Prepare Sql query
			//insert into employee (emp_no,name,salary,design) values(113,'RAMMU',23000,'CIRCLE');
			String query="INSERT INTO EMPLOYEE (EMP_NO,NAME,SALARY,DESIGN) VALUES("+emp_no+","+name+"," +salary+","+desi+")";
			System.out.println(query);
			
			//send and excute sql query in db s/w
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			
			//Process the Result
			if(count==0)
				System.out.println("Record not inserted");
			else
				System.out.println("Record inserted::"+count);
          }//try
		catch(SQLException se) {
			if(se.getErrorCode()>=1050 && se.getErrorCode()<=1050)
				System.out.println("invalid table name or column name  sql keyword");
			if(se.getErrorCode()==22001)
				System.out.println("Dont insert more then cloumn size data to design");
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//Close all jdbc obj
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
