package com.jdbc3;

import java.sql.Connection;


import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;




public class InSensetiveRsTest1{
	private static final String CUM_SELECT_QUERY="SELECT  CNO,CNAME,CADD,CMO FROM CUSTOMER";

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
				
				while(rs.next()) {
					System.out.println(rs.getRow()+"  "+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getDouble(4));
				}//while1
				//insert operation
				/*rs.moveToInsertRow();
				rs.updateInt(1, 7);
				rs.updateString(2,"rohan");
				rs.updateString(3, "raipur");
				rs.updateInt(4, 34445);
				rs.insertRow();*/
				
				//update operation
				/*rs.absolute(6);
				rs.updateInt(4, 777);
				rs.updateRow();*/
				
				//deleate operation
				rs.absolute(5);
				rs.deleteRow();
				
				System.out.println("Record update ::");
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
