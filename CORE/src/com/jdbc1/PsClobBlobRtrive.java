package com.jdbc1;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class PsClobBlobRtrive {
private static final String JOB_SEEKER_RETRIVE_QUERY="SELECT JSID,JSNAME,JSADDRS,RESUME,PHOTO FROM JOB_SEEKER WHERE JSID=?";
	public static void main(String[] args) {
	try(Scanner sc=new Scanner(System.in)){
		int jsid=0;
		//read input form enuser
		if(sc!=null) {
			System.out.println("Enter Jsid");
			jsid=sc.nextInt();
		}//if
		//Establish the connection
		try(Connection con=DriverManager.getConnection("jdbc:mysql:///db_ed","root","system");
				//Create Prepared Statement obj
				PreparedStatement ps=con.prepareStatement(JOB_SEEKER_RETRIVE_QUERY); )
		{
			
			//Seat query param
			if(ps!=null)
				ps.setInt(1, jsid);
			
			//execute query
			 try(ResultSet rs=ps.executeQuery()){
				 
				 //process the result
				 if(rs!=null) {
					 if(rs.next()) {
						 jsid=rs.getInt(1);
						 String jsname=rs.getString(2);
						 String jsaddrs=rs.getString(3);
						 System.out.println(jsid+" "+jsname+" "+jsaddrs);
						 
						 //get input Stream pointing to blob & clob col value
						 try(Reader reader=rs.getCharacterStream(4);
								 InputStream is=rs.getBinaryStream(5);
								 //Create OutputStream Pointing to destination file
								 Writer writer=new FileWriter("retrive_resume.txt");
								 OutputStream os=new FileOutputStream("retrive_imag.jpg");)
						 {	
							 //Copy Blob Copy File to Destination File
							 IOUtils.copy(is, os);
							 IOUtils.copy(reader,writer);
							 System.out.println("BLOB & CLOB Value is retrived and stored in the file");
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
