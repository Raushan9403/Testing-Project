package com.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
/*
CREATE PROCEDURE `P_college`(
	IN `id` INT
)
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT 'test'
BEGIN
SELECT *FROM college WHERE CLID=ID;
END
*/
public class CsProcedureTest2 {
	private static final String CALL_PROCEDURE="{CALL P_college(?) }";
	public static void main(String[] args) {
		//read inputs
	   int clid=0;
		try(Scanner sc=new Scanner(System.in);){
			if(sc!=null) {
			System.out.println("Enter CollegeId");
			 clid=sc.nextInt();
	
			}
			//Establish the Connection
			try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ed","root", "system");
					//Create calleble statement obj having the query calling pl/SQL procedure as the pre compiled Sql Query
					CallableStatement cs=con.prepareCall(CALL_PROCEDURE); 	){
           //register out param of jdbc data types
				if(cs!=null) 
				
					cs.registerOutParameter(1, Types.VARCHAR);
					
				
				
				//Seat value to in Param
				if(cs!=null) {
					cs.setInt(1,clid);
				
				}
				//execute or call the PL/SQL Function
				if(cs!=null)
					cs.execute();
				//gather result from out Params
				int result=0;
				if(cs!=null) {
					result=cs.getInt(result);
				System.out.println("get all clg detail::"+result);
				}//if
				}//try2
		}//try1
		catch (SQLException se) {
			System.out.println("requested data is not available");
		se.printStackTrace();
		}
		catch (Exception e) {
        e.printStackTrace();
		}
	}//main
}//class
