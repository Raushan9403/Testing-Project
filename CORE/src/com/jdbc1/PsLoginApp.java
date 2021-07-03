package com.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PsLoginApp {
private static final String LOGIN_QUERY="SELECT COUNT(*) FROM IRTC_TAB WHERE UNAME=? AND PWD=?";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//read inputs from end user
			sc=new Scanner(System.in);
			String uname=null,pwd=null;
			
			if(sc!=null) {
				System.out.println("Enter login user name::");
				uname=sc.next();
				System.out.println("Enter login user pasword::");
				pwd=sc.next();
							}//if
			//register jdbc driver loading jdbc driver class
			//Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establish the Connection
			con=DriverManager.getConnection("jdbc:mysql:///db_ed","root","system");
			
			//Create Prepared Statement Obj
			ps=con.prepareStatement(LOGIN_QUERY);
			
			//Set values of param pri-compiled query send and execute query in db s/w
			if(ps!=null) {
			ps.setString(1, uname); ps.setString(2, pwd);
			rs=ps.executeQuery();
			}//if
			
			//Process the Result Set obj
			if(rs!=null){
				rs.next(); //moves the first record from BFR
				int count=rs.getInt(1); //get the first column values of first record
				
				//process the Result
				if(count==0)
					System.out.println("INVALID CREDAITIALS");
				else
					System.out.println("VALID CREDAITIALS");
			}//if
		}//try
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
		e.printStackTrace();
		}
		finally {
			//close all jdbc obj
			try {
				if(rs!=null)
					rs.close();
			}//try
			catch (SQLException se) {
			      se.printStackTrace();
			}
			try {
				if(ps!=null)
					ps.close();
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
}//Class
