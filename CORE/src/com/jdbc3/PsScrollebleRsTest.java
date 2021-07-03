package com.jdbc3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PsScrollebleRsTest {
	private static final String EMP_SELECT_QUERY="SELECT EMP_NO,NAME,SALARY,DESIGN FROM EMPLOYEE";

	public static void main(String[] args) {
		try(//Establish the Connection
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ed","root", "system");
				//Create Simple Statement Object
				PreparedStatement ps=con.prepareStatement(EMP_SELECT_QUERY,
					                                                            	ResultSet.TYPE_SCROLL_SENSITIVE,
						                                                             ResultSet.CONCUR_UPDATABLE);
				//Create ResultSet object
				ResultSet rs=ps.executeQuery();
				){
			if(rs!=null) {
				System.out.println("Rs record top to bottom");
				while(rs.next()) {
					System.out.println(rs.getRow()+"-------> "+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
				}//while2
				System.out.println("--------------------------------------------------------------------------------------------");
				System.out.println("rs record bottom to top");
				rs.afterLast();
				while(rs.previous()){
					System.out.println(rs.getRow()+"-------> "+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
				}//while1
				rs.first();
				System.out.println(rs.getRow()+"-------> "+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
				System.out.println("-------------------------------");
				rs.last();
				System.out.println(rs.getRow()+"-------> "+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
				System.out.println("-------------------------------");
				rs.absolute(3);
				System.out.println(rs.getRow()+"-------> "+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
				System.out.println("-------------------------------");
				rs.absolute(-6);
				System.out.println(rs.getRow()+"-------> "+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
				System.out.println("-------------------------------");
				rs.relative(3);
				System.out.println(rs.getRow()+"-------> "+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
				System.out.println("-------------------------------");
				rs.relative(-6);
				System.out.println(rs.getRow()+"-------> "+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
				System.out.println("-------------------------------");
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
