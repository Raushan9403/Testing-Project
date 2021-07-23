package com.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Type3DriverSelectTest {
private static final String STUDENT_QUERY="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	public static void main(String[] args) {
		
	
		try {
             Class.forName("ids.sql.IDSDriver");
			}
		   catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		try(//Establish the Connection
				Connection con=DriverManager.getConnection("jdbc:ids://localhost:12/conn?dsn=accdsn");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(STUDENT_QUERY);
              ){
				//process the Result
	          if(rs!=null) {
	        	  while(rs.next()) {
	        		  System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+rs.getFloat(4));
	        	  }//while
			}//if
		}//try
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
		e.printStackTrace();
		}
	}//main
}//Class
