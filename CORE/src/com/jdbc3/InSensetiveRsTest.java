package com.jdbc3;

import java.sql.Connection;


import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;




public class InSensetiveRsTest{
	private static final String CUM_SELECT_QUERY="SELECT  *FROM CUSTOMER";

	public static void main(String[] args) {
		try(//Establish the Connection
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ed","root", "system");
				//Create Simple Statement Object
		java.sql.Statement st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					
						                                                                                  					                                                                                  
				//Create ResultSet object
				ResultSet rs=st.executeQuery(CUM_SELECT_QUERY);
				){
			if(rs!=null) {
				System.out.println("Rs record top to bottom");
				int count=0;
				while(rs.next()) {
					if(count==0)
					
						Thread.sleep(30000);
					
					
					count++;
					System.out.println(rs.getRow()+"  "+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getDouble(4));
				}//while1
			}//if
		}//try1
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
