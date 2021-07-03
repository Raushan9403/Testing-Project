package com.jdbc;
import java.sql.*;

public class SelectTest {

	public static void main(String[] args)throws Exception {
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","system");
   
		Statement st=con.createStatement();
      ResultSet rs=st.executeQuery("SELECT *FROM STUDENTS");
      
      while(rs.next()!=false) {
    	  System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));
      }
      rs.close();
      st.close();
      con.close();
      

	}

}
