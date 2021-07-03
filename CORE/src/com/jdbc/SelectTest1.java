package com.jdbc;
import java.sql.*;
import java.util.*;
public class SelectTest1 {

	public static void main(String[] args)throws Exception {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter start range of employee salary");
        int startSalary=sc.nextInt();
        System.out.println("Enter end range of employee salary");
        int endSlary=sc.nextInt();
        
        
      //Class.forName("com.mysql.cj.jdbc.Driver");
        
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ed","root", "system");
        Statement st=con.createStatement();
        
        //select *from employee where salary>=9000 and salary<=16000;
        String query="select *from employee where salary>="+startSalary+" and salary<="+endSlary;
       
        
        ResultSet rs=st.executeQuery(query);
       
        while(rs.next()!=false) {
        	System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
        }
        rs.close();
        st.close();
        con.close();
	}

}
