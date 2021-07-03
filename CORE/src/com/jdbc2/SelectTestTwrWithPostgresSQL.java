package com.jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTestTwrWithPostgresSQL {

	public static void main(String[] args) {
		/*try {
			Class.forName("org.postgresql.Driver");
		}
		catch (ClassNotFoundException cnfe) {
		           cnfe.printStackTrace();
		}*/
		try(//Establish the connection
				//Connection con=DriverManager.getConnection("jdbc:postgresql:NTAJ","postgres","tiger");
				Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/NTAJ","postgres","tiger");
				//Create statement obj
				Statement st=con.createStatement();
				
				//execute query in db db s/w
				ResultSet rs=st.executeQuery("SELECT  *FROM PRODUT_INFO");
				){
			//process the resul set obj
			
			if(rs!=null) {
				boolean flag=false;	
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3)+"  "+rs.getInt(4)+" "+rs.getString(5));
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
