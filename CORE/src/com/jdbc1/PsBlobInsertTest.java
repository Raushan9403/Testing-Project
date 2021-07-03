package com.jdbc1;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsBlobInsertTest {
	private static final String INSERT_ARTIST_QUERY="INSERT INTO ARTIST_INFO(NAME,ADDRS,PHOTO)  VALUES(?,?,?)";

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in)){
			//read inputs 
			String name=null,addrs=null,photolocation=null;
			if(sc!=null) {
				System.out.println("Enter Artist name::");
				name=sc.next();
				System.out.println("Enter Artist address::");
				addrs=sc.next();
				System.out.println("Enter Artist photo location::");
				photolocation=sc.next().replace("?","");
			}//if
			//Create InputStream pointing to photo file
			try(FileInputStream is=new FileInputStream(photolocation)){
				
				//establish the connection and create prepared statment obj
				try(Connection con=DriverManager.getConnection("jdbc:mysql:///db_ed","root","system");
						PreparedStatement ps=con.prepareStatement(INSERT_ARTIST_QUERY); ){
					
					//seat values to query param
					if(ps!=null) {
						ps.setString(1,name);
						ps.setString(2, addrs);
						ps.setBlob(3, is,is.available());
					}//if
					
					//execute the query
					int count=0;
					if(ps!=null)
						count=ps.executeUpdate();
						
					//Process the result set
					if(count==0) 
						System.out.println("record is not inserted");
					else
						System.out.println("record is inserted");
					
				}//try3
				
			}//try2
			
		}//try1
		catch (SQLException se) {
			se.printStackTrace();
			System.out.println("Problem in record insertion");
					}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("problem in application");
		}
	}//main

}//class
