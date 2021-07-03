package com.jdbc1;

import java.io.FileOutputStream;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;



public class ShadiAppRetrive {
private static final String RETRIVE_SHADI_QUERY="SELECT CID, CNAME,GENDER,DOB,PHOTO,RESUME,DOJ,BIODATA,AUDIO,VIDEO FROM SHADI_APP WHERE CID=?";
	public static <CharacterStream> void main(String[] args) {
		Connection con=null;
		Scanner sc=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
	try {
	 sc=new Scanner(System.in);
		int cid=0;
		//read inputs from end user
		if(sc!=null) {
			System.out.println("Enter Customer id::");
			cid=sc.nextInt();
		}//if1
						 //register jdbc driver loading jdbc driver class
					 Class.forName("com.mysql.cj.jdbc.Driver");
					//Establish the Connecton
					con=DriverManager.getConnection("jdbc:mysql:///db_ed","root","system");
				 //create Prepared Statement obj
					if(con!=null)
						ps=con.prepareStatement(RETRIVE_SHADI_QUERY);
				//Seat query param
					if(ps!=null) {
						ps.setInt(1, cid);
					}//if2
					//execute the query
				if(ps!=null)
					rs=ps.executeQuery();
				//process the result
				if(rs!=null) {
					if(rs.next()) {
					cid=rs.getInt(1);
					String cname=rs.getString(2);
					String gender=rs.getString(3);
					java.sql.Date sqdob=rs.getDate(4);
					java.sql.Date sqdoj=rs.getDate(7);
					//get input stream pointing to clob and blob value
					InputStream is1=rs.getBinaryStream(5);
					Reader reader=rs.getCharacterStream(6);
					InputStream is2=rs.getBinaryStream(8);
					InputStream is3=rs.getBinaryStream(9);
					InputStream is4=rs.getBinaryStream(10);
				//Create outputStram Pointing to Destination File
					OutputStream os1=new FileOutputStream("retrive_image.jpg");
					FileWriter fw=new FileWriter("retrive_resume.txt");
					OutputStream os2=new FileOutputStream("retrive_biodata.txt");
					OutputStream os3=new FileOutputStream("retrive_audio");
					OutputStream os4=new FileOutputStream("retrive_video");
					//Copy Blob & Clob File to Destination File
					IOUtils.copy(is1, os1);
					IOUtils.copy(reader, fw);
					IOUtils.copy(is2, os2);
					IOUtils.copy(is3, os3);
					IOUtils.copy(is4, os4);
					//Convert java.sql.Date class obj to String Date values
					SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
					String sdob=sdf.format(sqdob);
					String sdoj=sdf.format(sqdoj);
					System.out.println(cid+" "+cname+" "+gender+" "+sdob+" "+sdoj);
					System.out.println("Shadi app values retrive in the file");
				}//if2
				}//if1
				else {
					System.out.println("Shadi app values is not  found");
				}
	}//try1
	catch (SQLException se) {
		se.printStackTrace();
		System.out.println("Problem in record insertion ");
	}
	catch (Exception e) {
            e.printStackTrace();
    		System.out.println("Problem in Application ");
	}
	finally {
		//close all jdbc obj
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
}//class
