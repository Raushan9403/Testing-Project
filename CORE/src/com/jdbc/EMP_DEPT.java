package com.jdbc;
import java.sql.*;
import java.util.*;
public class EMP_DEPT {

	public static void main(String[] args) throws Exception {
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ed", "root", "system");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("SELECT *FROM DEPARTMENT");
		
		while(rs.next()!=false) {
			//System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getInt(5)+" "+rs.getDate(6));
			System.out.println(rs.getInt(1)+" "+rs.getNString(2)+" "+rs.getString(3)+" "+rs.getDate(4));
		}
		rs.close();
		st.close();
		con.close();

	}

}
