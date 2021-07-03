package com.jdbc1;
import java.sql.*;

public class PsSelectTest {
	private static final String STUDENTS_SELECT_QUERY="SELECT *FROM STUDENTS";
	public static void main(String[] args)throws Exception {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//load jdbc driver class
			//Class.forName("com.mysql.cj.jdbc.Driver");
        
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","system");
		
			//create prepared statemt obj
        if(con!=null)   
		ps=con.prepareStatement(STUDENTS_SELECT_QUERY);
      
     
       rs=ps.executeQuery();
      
       if(rs!=null) {
    	   while(rs.next()) {
    		   System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
    	   }
     
     
       }//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)
					rs.close();
			}//try
			catch (SQLException se) {
	             se.printStackTrace();
			}

			try {
				if(con!=null)
					con.close();
			}//try
			catch (SQLException se) {
	             se.printStackTrace();
			}
	}//finally
	}//main
}//class
