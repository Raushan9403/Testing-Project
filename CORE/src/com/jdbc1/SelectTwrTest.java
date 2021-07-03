package com.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTwrTest {

	public static void main(String[] args) {
		try(//Establish the connection
				Connection con=DriverManager.getConnection("jdbc:mysql:///db_ed","root","system");
				
				//Create statement obj
				Statement st=con.createStatement();
				
				//execute query in db db s/w
				ResultSet rs=st.executeQuery("SELECT  *FROM COLLEGE");
				){
			//process the resul set obj
			
			if(rs!=null) {
				boolean flag=false;	
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getDouble(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));
				}//while
				if(flag==false)
				System.out.println("record not found");
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
