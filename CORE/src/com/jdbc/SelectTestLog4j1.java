package com.jdbc;
import java.sql.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
public class SelectTestLog4j1 {
	private static Logger logger=Logger.getLogger(SelectTestLog4j1.class);
	static {
		try {
		PropertyConfigurator.configure("src/com/log4j.Properties");
		logger.info("com.jdbc.SelectTestLog4j::Log4j setup ready");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.fatal("com.jdbc.SelectTestLog4j::Problem while setting up log4j");
		}
	}

	public static void main(String[] args)throws Exception {
		logger.debug("com.jdbc.SelectTestLog4j::start of main(-) method");
		Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try {
        	//register jdbc driver loading jdbc driver class
        	//Class.forName("com.mysql.cj.jdbc.Diver");
        	logger.debug("com.jdbc.SelectTestLog4j::register jdbc driver loading jdbc driver class");
        	
        	//Establish the connection
		    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","system");
		    logger.debug("com.jdbc.SelectTestLog4j::establish the connection db s/w");
		    
		 //Create statement obj
		    if(con!=null)
		    st=con.createStatement();
		    logger.debug("com.jdbc.SelectTestLog4j::Create jdbc statement obj");
		 
		 //send and execute sql in db s/w
		    if(st!=null)
          rs=st.executeQuery("SELECT *FROM STUDENTS");
		    logger.debug("com.jdbc.SelectTestLog4j::SQL query send to db s/w for execution and ResutSet obj is generated");
		    
      if(rs!=null) {
       while(rs.next()) {
    	  System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));
    	  logger.warn("com.jdbc.SelectTestLog4j::getting input values in column and row wise");
      }//while
       logger.debug("com.jdbc.SelectTestLog4j::Result set obj is procedd");
      }//if
      }//try
     catch (SQLException se) {
      se.printStackTrace();
      logger.error("com.jdbc.SelectTestLog4j::Known db problem::"+se.getMessage()+"SQL error code"+se.getErrorCode());
	}  
        catch (Exception e) {
		e.printStackTrace();
		logger.fatal("com.jdbc.SelectTestLog4j::Unknown Problem::"+e.getMessage());
		}
        finally {
        	logger.debug("com.jdbc.SelectTestLog4j::Close jdbc obj");
        	//close all jdbc obj
        	try {
        		if(rs!=null)
        			rs.close();
        		logger.debug("com.jdbc.SelectTestLog4j::ResultSet obj is Closed");
        	}//try
        	catch (SQLException se) {
				se.printStackTrace();
				logger.error("com.jdbc.SelectTestLog4j::Problem in closeing Result set obj"+se.getMessage());
			}
        	try {
        		if(st!=null)
        			st.close();
        		logger.debug("com.jdbc.SelectTestLog4j::Statement obj is Closed");
        	}//try
        	catch (SQLException se) {
				se.printStackTrace();
				logger.error("com.jdbc.SelectTestLog4j::Problem in closing Statment obj"+se.getMessage());
			}
        	try {
        		if(con!=null)
        			con.close();
        		logger.debug("com.jdbc.SelectTestLog4j::Connection obj Closed");
        	}//try
        	catch (SQLException se) {
				se.printStackTrace();
				logger.error("com.jdbc.SelectTestLog4j::Problem in Closing Connection obj::"+se.getMessage());
			}
          }//finally
        logger.debug("com.jdbc.SelectTestLog4j::end of main(-) method");
	}//main
}//class
