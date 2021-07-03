package com.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
/*
CREATE DEFINER=`root`@`localhost` PROCEDURE `first_pro`(
	IN `X` INT,
	IN `Y` INT,
	OUT `Z` INT
)
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
SET Z:=(SELECT SUM(X+Y) FROM first_pro);
END
*/
public class CsProcedureTest {
	private static final String CALL_PROCEDURE="{CALL FIRST_PRO(?,?,?) }";

	public static void main(String[] args) {
		//read inputs
		int first=0,second=0;
		try(Scanner sc=new Scanner(System.in);){
			if(sc!=null) {
			System.out.println("Enter first value");
			 first=sc.nextInt();
			System.out.println("Enter Second value");
			 second=sc.nextInt();
			}
			//Establish the Connection
			try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ed","root","system");
					//Create calleble statement obj having the query calling pl/SQL procedure as the pre compiled Sql Query
					CallableStatement cs=con.prepareCall(CALL_PROCEDURE); 	){
           //register out param of jdbc data types
				if(cs!=null)
				cs.registerOutParameter(3,Types.INTEGER);
				//Seat value to in Param
				if(cs!=null) {
					cs.setInt(1,first);
					cs.setInt(2, second);
				}
				//execute or call the PL/SQL Function
				if(cs!=null)
					cs.execute();
				//gather result from out Params
				int result=0;
				if(cs!=null)
					result=cs.getInt(3);
				System.out.println("sum::"+result);
				}//try2
		}//try1
		catch (SQLException se) {
		se.printStackTrace();
		}
		catch (Exception e) {
        e.printStackTrace();
		}
	}//main
}//class
