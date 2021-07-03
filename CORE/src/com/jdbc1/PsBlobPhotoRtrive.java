package com.jdbc1;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class PsBlobPhotoRtrive {
private static final String ARTIST_RETRIVE_QUERY="SELECT AID,NAME,ADDRS,PHOTO FROM ARTIST_INFO WHERE AID=?";
	public static void main(String[] args) {
	try(Scanner sc=new Scanner(System.in)){
		int aid=0;
		//read input form enuser
		if(sc!=null) {
			System.out.println("Enter Aid");
			aid=sc.nextInt();
		}//if
		//Establish the connection
		try(Connection con=DriverManager.getConnection("jdbc:mysql:///db_ed","root","system");
				//Create Prepared Statement obj
				PreparedStatement ps=con.prepareStatement(ARTIST_RETRIVE_QUERY); )
		{
			
			//Seat query param
			if(ps!=null)
				ps.setInt(1, aid);
			
			//execute query
			 try(ResultSet rs=ps.executeQuery()){
				 
				 //process the result
				 if(rs!=null) {
					 if(rs.next()) {
						 aid=rs.getInt(1);
						 String name=rs.getString(2);
						 String addrs=rs.getString(3);
						 System.out.println(aid+" "+name+" "+addrs);
						 
						 //get input Stream pointing to blob col value
						 try(InputStream is=rs.getBinaryStream(4);
								 //Create OutputStream Pointing to destination file
								 OutputStream os=new FileOutputStream("retrive_imag.jpg");)
						 {	
							 //Copy Blob Copy File to Destination File
							 IOUtils.copy(is, os);
							 System.out.println("BLOB Value is retrived and stored in the file");
						 }//try4
					}//if2
					 else{
						 System.out.println("Record not found");
					 }//elses
				 }//if1
			 }//try3
		}//try2
	}//try1
	catch (SQLException se) {
		se.printStackTrace();
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	}//main
}//class
