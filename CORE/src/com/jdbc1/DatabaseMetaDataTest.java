package com.jdbc1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseMetaDataTest {

	public static void main(String[] args) {
		try(//Establish the Connection
				Connection con=DriverManager.getConnection("jdbc:mysql:///db_ed","root","system");
              ){
			DatabaseMetaData dmd=con.getMetaData();
				//process the Result
	          if(dmd!=null) {
	        	  System.out.println("db s/w name::"+dmd.getDatabaseProductName());
	        	  System.out.println("db s/w version::"+dmd.getDatabaseProductVersion());
	        	  System.out.println("jdbc driver name::"+dmd.getDriverName());
	        	  System.out.println("jdbc driver version::"+dmd.getDriverVersion());
	        	  System.out.println("All Sql keywords::"+dmd.getSQLKeywords());
	        	  System.out.println("All Numberic function::"+dmd.getNumericFunctions());
	        	  System.out.println("All System functions::"+dmd.getSystemFunctions());
	        	  System.out.println("All String functions::"+dmd.getStringFunctions());
	        	  System.out.println("Max chars intable name::"+dmd.getMaxTableNameLength());
	        	  System.out.println("Max tables in Select query::"+dmd.getMaxTablesInSelect());
	        	  System.out.println("Max row size::"+dmd.getMaxRowSize());
	        	  System.out.println("Support P/L sql  procedure ?::"+dmd.supportsStoredProcedures());
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
