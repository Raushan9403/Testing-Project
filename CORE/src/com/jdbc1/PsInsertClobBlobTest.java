package com.jdbc1;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertClobBlobTest {
private static final String JOB_SEEKER_QUERY="INSERT INTO JOB_SEEKER(JSNAME,JSADDRS,RESUME,PHOTO) VALUES(?,?,?,?)";
	public static void main(String[] args) {
	try(Scanner sc=new Scanner(System.in)){
		String jsname=null,jsaddrs=null,resume_location=null,photo_location=null;
		//read inputs from enduser
		if(sc!=null) {
			System.out.println("Enter job seeker name::");
			jsname=sc.next();//gives Ravi
			System.out.println("Enter job seeker addras::");
			jsaddrs=sc.next();//gives hzp
			System.out.println("Enter job seeker rsume location::");
			resume_location=sc.next().replace("?","");
			System.out.println("Enter job seeker photo  location::");
			photo_location=sc.next().replace("?","");
		}//if1
		try(Reader reader=new FileReader(resume_location);
				InputStream is=new FileInputStream(photo_location)){
			//establish the connection
			try(Connection con=DriverManager.getConnection("jdbc:mysql:///db_ed","root","system");
					PreparedStatement ps=con.prepareStatement(JOB_SEEKER_QUERY)){
				//seat values to query param
				if(ps!=null) {
					ps.setString(1, jsname);
					ps.setString(2, jsaddrs);
					ps.setCharacterStream(3, reader);
					ps.setBinaryStream(4, is);
				}//if2
				//execute the query param
				int count =0;
				if(ps!=null)
					count=ps.executeUpdate();
				//process the result
				if(count==0)
					System.out.println("record is not inserted");
				else
					System.out.println("record is inserted");
			}//try3
					}//try2
			}//try1
	     catch (SQLException se) {
			se.printStackTrace();
			System.out.println("problem in record insertion::");
		}
	catch (Exception e) {
		e.printStackTrace();
		System.out.println("problem in application");
	}
}//main
}//class
