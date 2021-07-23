package com.jdbc3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchUpdationTest {

	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ed","root", "system");
				Statement st=con.createStatement();){
			//add batch query to batch these query can belong to same db table and different db tables  but must be
			st.addBatch("INSERT INTO COLLEGE VALUES(1005,'RAMESH','EEE','SMECT')");
			st.addBatch("UPDATE COLLEGE SET BRANCH='MAC' WHERE CLID=1004");
			st.addBatch("DELETE FROM COLLEGE WHERE CLID=1003");
			
			/*st.addBatch("INSERT INTO CUTOMER VALUES(7,'RAMESH','HYD',7878)");
			st.addBatch("UPDATE CUSTOMER SET CMO=CMO-700 WHERE CNO=1");
			st.addBatch("DELETE FROM CUSTOMER WHERE CNO=2");*/
			//EXECUTE THE BATCH
			int result[]=st.executeBatch();
			//process the result
			int sum=0;
			for(int i=0;i<result.length;i++)
				sum=sum+result[i];
			System.out.println("total number of record that are effected::"+sum);
		}
       catch (SQLException se) {
		se.printStackTrace();
	}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
