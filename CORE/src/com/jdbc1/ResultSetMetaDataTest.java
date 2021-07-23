package com.jdbc1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataTest {

	public static void main(String[] args) {
		try(//Establish the Connection
				Connection con=DriverManager.getConnection("jdbc:mysql:///students","root","system");
				//create Statement
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("SELECT *FROM STUDENTS");
              ){
			//Create ResultSetMetaData Obj
			ResultSetMetaData rsmd=null;
			if(rs!=null)
				rsmd=rs.getMetaData();
			//get Columan count
			if(rsmd!=null) {
				int colCount=rsmd.getColumnCount();
				for(int i=1;i<=colCount;i++) {
					System.out.print(rsmd.getColumnName(i)+"    ");
				}
				System.out.println();
				for(int i=1;i<=colCount;i++) {
					System.out.print(rsmd.getColumnTypeName(i)+"   ");
				}
				System.out.println();
				while(rs.next()) {
				for(int i=1;i<=colCount;i++) {
					System.out.print(rs.getString(i)+"  ");
				}
				System.out.println();
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
