package com.jdbc1;

import java.io.FileInputStream
;

import java.io.FileReader;

import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ShadiApp {
private static final String INSERT_SHADI_QUERY="INSERT INTO SHADI_APP(CNAME,GENDER,DOB,PHOTO,RESUME,DOJ,BIODATA,AUDIO,VIDEO) VALUES(?,?,?,?,?,?,?,?,?)";
	public static <CharacterStream> void main(String[] args) {
		Connection con=null;
		Scanner sc=null;
		PreparedStatement ps=null;
	try {
	 sc=new Scanner(System.in);
		String cname=null,gender=null,dob=null,photo=null,resume=null,doj=null,biodata=null,audio=null,video=null;
		//read inputs from end user
		if(sc!=null) {
			System.out.println("Enter Customer name::");
			cname=sc.next();
			System.out.println("Enter Customer gender::");
			gender=sc.next();
			System.out.println("Enter Customer DOB::");
			dob=sc.next();
			System.out.println("Enter Customer Photo::");
			photo=sc.next().replace("?","");
			System.out.println("Enter Customer resume::");
			resume=sc.next().replace("?","");
			System.out.println("Enter Customer First_job_DOJ::");
			doj=sc.next();
			System.out.println("Enter Customer biodata::");
			biodata=sc.next().replace("?","");
			System.out.println("Enter Customer Info audio::");
			audio=sc.next().replace("?","");
			System.out.println("Enter Customer Info video::");
			video=sc.next().replace("?","");
		}//if1
		//create input Stream pointing to photo,resume,biodata,audio,video
		InputStream is1=new FileInputStream(photo);
				Reader reader=new FileReader(resume);
				InputStream is2=new FileInputStream(biodata);
				InputStream is3=new FileInputStream(audio);
				InputStream is4=new FileInputStream(video);
	   //Converting String date values to java.sql.date obj
			        SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
					SimpleDateFormat sdf2=new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date udob=sdf1.parse(dob);
					java.util.Date udoj=sdf2.parse(doj);
					//Converting java.util.Date class boj to java.Sql.Date class obj
					long ms=udob.getTime();
					 ms=udoj.getTime();
					 java.sql.Date sqdob=new java.sql.Date(ms);
					 java.sql.Date sqdoj=new java.sql.Date(ms);
					 //register jdbc driver loading jdbc driver class
					 Class.forName("com.mysql.cj.jdbc.Driver");
					//Establish the Connecton
					con=DriverManager.getConnection("jdbc:mysql:///db_ed","root","system");
				 //create Prepared Statement obj
					if(con!=null)
						ps=con.prepareStatement(INSERT_SHADI_QUERY);
				//Seat values in pre-Compiled Query in the sql query
					if(ps!=null) {
						ps.setString(1,cname);
						ps.setString(2, gender);
						ps.setDate(3, sqdob);
						ps.setBinaryStream(4, is1);
						ps.setCharacterStream(5, reader);
						ps.setDate(6, sqdoj);
						ps.setBinaryStream(7,is2);
						ps.setBinaryStream(8, is3);
						ps.setBinaryStream(9, is4);
					}//if2
					//execute the query
					int count=0;
					if(ps!=null)
						count=ps.executeUpdate();
					//process the result set
					if(count==0)
						System.out.println("record is  not inserted");
					else
						System.out.println("record is inserted");
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
