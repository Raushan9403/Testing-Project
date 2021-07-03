package com.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleToMySqlDataTransferTest {
    private static final String MYSQL_INSERT_STUDENT="INSERT INTO STUDENTS  VALUES(?,?,?,?)";
    private static final String ORACLE_SELECT_STUDENT="SELECT  SNO,SNAME,SADD,AVGNO FROM STUDENTS";
	public static void main(String[] args) {
		Connection oracon=null,mysqlcon=null;
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try{
			//register Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//establish the connection
			oracon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			mysqlcon=DriverManager.getConnection("jdbc:mysql///db_ed","root","system");
			
			//create statment obj
			if(oracon!=null)
				st=oracon.createStatement();
			
			if(mysqlcon!=null)
				ps=mysqlcon.prepareStatement(MYSQL_INSERT_STUDENT);
			
			//send and execute select query in oracle db s/w and and get result set obj
			if(st!=null)
				rs=st.executeQuery(ORACLE_SELECT_STUDENT);
			
				//gather each record of rs object and insert to mysql db table
			if(rs!=null && ps!=null) {
				while(rs.next()) {
				//gather each record from Rs
				int no=rs.getInt(1);
				String name=rs.getString(2);
				String addrs=rs.getString(3);
				int avgno=rs.getInt(4);
				
				//set each record values as insert query param vlues to insert to myssql db table
				ps.setInt(1, no);
				ps.setString(2, name);
				ps.setString(3, addrs);
				ps.setInt(4, avgno);
				
				//execute the query
				int count=ps.executeUpdate();
				}//while
				System.out.println("Record are copied from oracle db table to mysql db table successfully");
					}//if
		}//try
		catch (SQLException se) {
			se.printStackTrace();
			System.out.println("Record are not copied to oracle db table to mysql db table");
		}
		catch (Exception e) {
		e.printStackTrace();
		System.out.println("Problem in app execution");
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
				if(st!=null)
					st.close();
			}//try
				catch (SQLException se) {
				se.printStackTrace();
				}
			try {
				if(ps!=null)
					rs.close();
			}//try
				catch (SQLException se) {
				se.printStackTrace();
				}
			try {
				if(oracon!=null)
					oracon.close();
			}//try
				catch (SQLException se) {
				se.printStackTrace();
				}
			try {
				if(mysqlcon!=null)
					mysqlcon.close();
			}//try
				catch (SQLException se) {
				se.printStackTrace();
				}
			}//finally
		}//main
	}//class


