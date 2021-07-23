package com.jdbc1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ParameterMetaDataTest {

	public static void main(String[] args) {
		try(//Establish the Connection
				Connection con=DriverManager.getConnection("jdbc:mysql:///students","root","system");
				//create Statement
				PreparedStatement ps=con.prepareStatement("INSERT INTO STUDENTS VALUES(?,?,?,?)");
              ){
          //create ParameterMetaData Obj
			ParameterMetaData pmd=ps.getParameterMetaData();
			if(pmd!=null) {
				int paramCount=pmd.getParameterCount();
				for(int i=1;i<=paramCount;i++) {
					System.out.println("parameter number::"+i);
					System.out.println("parameter name mode::"+pmd.getParameterMode(i));
					System.out.println("parameter type name:;"+pmd.getParameterTypeName(i));
					System.out.println("parameter signed::"+pmd.isSigned(i));
				}//for
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
