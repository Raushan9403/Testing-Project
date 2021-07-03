package com.jdbc;
import java.sql.*;
import java.util.*;

public class SelectTest4 {
	public static void main(String[] args) throws Exception{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Start Range of Students avgno");
		int startAvgno=sc.nextInt();
		System.out.println("Enter End Range of Students avgno");
		int endAvgno=sc.nextInt();
	//Class.forName("com.mysql.cj.jdbc.Driver");
    
    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root", "system");
    Statement st=con.createStatement();
    //select *from students where avgno>=30 and avgno<=90;
    String query="select *from students where avgno>="+startAvgno+" and avgno<="+endAvgno;
    System.out.println(query);
   
    
    ResultSet rs=st.executeQuery(query);
    System.out.println("Students details of Avgno Range"+startAvgno+" "+endAvgno);
   
    while(rs.next()!=false) {
    	System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));
    }
    rs.close();
    st.close();
    con.close();
	
		
	}

}
