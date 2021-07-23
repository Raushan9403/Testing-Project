package com.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

//JDBC App that gives Employee Details who have nth highest salary
public class SelectTest13WithPropertiesFile {
	private static final String LOGIN_QUERY="SELECT COUNT(*) FROM IRTC_TAB WHERE UNAME=? AND PWD=?";
	public static void main(String[] args) {
		 String uname=null,pwd=null;
		 Properties props=null;
		 try(InputStream is=new FileInputStream("src/com/nt/commons/jdbc.properties")){
			 props=new Properties();
			 props.load(is);
	
		 }
		 catch (FileNotFoundException fne) {
			fne.printStackTrace();
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
		try(Scanner sc=new Scanner(System.in)) {
			 
			if(sc!=null) {
				System.out.println("Enter login user name::");
				uname=sc.next();
				System.out.println("Enter login user pasword::");
				pwd=sc.next();
							}//if
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Class.forName(props.getProperty("jdbc.driver"));
		
			
		}
		catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		try(//Establish the Connection
			Connection con=DriverManager.getConnection(props.getProperty("jdbc.url"),
					                                                                                props.getProperty("jdbc.username"),
					                                                                                props.getProperty("jdbc.pwd"));
				//Create Prepared Statement Obj
			PreparedStatement	ps=con.prepareStatement(LOGIN_QUERY);
){							
			//Set values of param pri-compiled query send and execute query in db s/w
			if(ps!=null) 
			ps.setString(1, uname); ps.setString(2, pwd);
			ResultSet rs=ps.executeQuery();
			
			
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
	
	}//main
}//class
