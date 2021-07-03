package com.jdbc;
import java.sql.*;

public class ConnTest {

	public static void main(String[] args) throws Exception {
		
		//Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","system");
		System.out.println("Connection obj Class name:"+con.getClass());
		
		Statement st=con.createStatement();
		System.out.println("Statment obj (st)class name:"+st.getClass());
				
		if(con==null)
			System.out.println("connection is not established");
		else
			System.out.println("connection is established");
	

	}

}
