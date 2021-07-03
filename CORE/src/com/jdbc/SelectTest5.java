package com.jdbc;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


//JDBC App that gets Employee details from EMP db table based on given 2 design

public class SelectTest5 {

	public static void main(String[] args) {
		//read inputs from end user
				Scanner sc=null;
				String des1=null,des2=null;
				Connection con=null;
				Statement st=null;
				ResultSet rs=null;
				try {
					sc=new Scanner(System.in);
					if(sc!=null) {
					System.out.println("Enter des1::");
					des1=sc.next().toUpperCase(); //gives PIRAMID
					System.out.println("Enter des2::");
					des2=sc.next().toUpperCase(); //gives HEXAGONAL
					}//if
					
					//Convert intput  values as required for sql query
					des1="'"+des1+"'"; //gives PIRAMID
					des2="'"+des2+"'"; //gives HEXAGONAL
					
					//Load JDBC DRIVER Class
					//Class.forName("com.mysql.cj.jdbc.Driver");
					
					//establish the connection 
					 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ed", "root", "system");
					
					//Create Statemnt Object
					if(con!=null)
					st=con.createStatement();
					
				    //preapre sql query
					 //select emp_no,name,salary,design from employee where design in('PIRAMID','HEXAGONAL') order by design
					String query="select emp_no,name,salary,design from employee where design in("+des1+","+des2+") order by design";
					
					System.out.println(query);
					
					//Send and exute sql query in db s/w
					if(st!=null)
					 rs=st.executeQuery(query);
					
					if(rs!=null) {
						 System.out.println("The employee details are:");
			             while(rs.next()!=false) {
			            	 System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
			             }//while
					}//if
				}//try
					
				 catch(SQLException se) { //to handle know exception 
					 se.printStackTrace(); //gives detail info raised exception
				 }
				 catch(Exception e) {
					 e.printStackTrace();
				 }
				 //close JDBC object & stream obj
				finally{
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
				}// finally


}//main
}//class
		


