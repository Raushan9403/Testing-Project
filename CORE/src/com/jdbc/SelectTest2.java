package com.jdbc;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest2 {

	public static void main(String[] args)throws Exception {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter start Range of Department id");
		int startDept_id=sc.nextInt();
		System.out.println("Enter End Range of Department id");
		int endDept_id=sc.nextInt();
    
        
      //Class.forName("com.mysql.cj.jdbc.Driver");
        
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ed","root", "system");
        Statement st=con.createStatement();
        //select *from department where dept_id>=900 and dept_id<=1005;
        
        String query="select *from department where dept_id>="+startDept_id+" and dept_id<="+endDept_id;
        System.out.println(query);
        
        ResultSet rs=st.executeQuery(query);
       
        while(rs.next()!=false) {
        	System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
        }
        rs.close();
        st.close();
        con.close();
	}
}
