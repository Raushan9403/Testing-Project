package com.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
/*
CREATE PROCEDURE `P_GET_EMP_DETAILS_BY_ID`(
	IN `no` INT,
	OUT `name` VARCHAR(20),
	OUT `salary` DOUBLE,
	OUT `design` VARCHAR(20)
)
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
SELECT NAME,salary,design INTO NAME,salary,design FROM employee WHERE emp_no=NO;
END
*/
public class CsProcedureTest1 {
	private static final String CALL_PROCEDURE="{CALL P_GET_EMP_DETAILS_BY_ID(?,?,?,?) }";
	public static void main(String[] args) {
		//read inputs
	   int emp_no=0;
		try(Scanner sc=new Scanner(System.in);){
			if(sc!=null) {
			System.out.println("Enter Emp_no");
			 emp_no=sc.nextInt();
	
			}
			//Establish the Connection
			try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ed","root", "system");
					//Create calleble statement obj having the query calling pl/SQL procedure as the pre compiled Sql Query
					CallableStatement cs=con.prepareCall(CALL_PROCEDURE); 	){
           //register out param of jdbc data types
				if(cs!=null) {
					cs.registerOutParameter(2,Types.VARCHAR);
					cs.registerOutParameter(3, Types.DOUBLE);
					cs.registerOutParameter(4, Types.VARCHAR);
					
				}
				
				//Seat value to in Param
				if(cs!=null) {
					cs.setInt(1,emp_no);
				
				}
				//execute or call the PL/SQL Function
				if(cs!=null)
					cs.execute();
				//gather result from out Params
				
				if(cs!=null) {
					String name=cs.getString(2);
					Double salary=cs.getDouble(3);
					String design=cs.getString(4);
				System.out.println("name::"+name+"salary="+salary+"design="+design);
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
