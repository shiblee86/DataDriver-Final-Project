package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;



public class DbTablePrint {
	
	public static ArrayList<String> getWholeDBTable(String SQLqyeries) throws Exception {
  
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522/orcl", "hr", "hr");
	
		PreparedStatement preparedStatement = connection.prepareStatement(SQLqyeries);
		ResultSet  rs = preparedStatement.executeQuery();
	
		
		
		ResultSetMetaData rsmd = rs.getMetaData();
	
		int columnsNumber = rsmd.getColumnCount();                     

		// Iterate through the data in the result set and display it. 
		ArrayList<String> columnValue = new ArrayList<String>();
		
		for (int i=1; i<=columnsNumber; i++) 
	    {
	        String columnName = rsmd.getColumnName(i);
	        System.out.print(columnName+ "|| \t");
	      
	        columnValue.add(rsmd.getColumnName(i));
	    }
	
		  System.out.println("\n ");
		
		while (rs.next()) {
		//Print one row          
		for(int i = 1 ; i <= columnsNumber; i++){
			// String columnName = rsmd.getColumnName(i);
		     //  System.out.print(columnName+ "||\t");
		      
		     System.out.print(rs.getString(i) + "      \t"); //Print one element of a row
		      columnValue.add(rs.getString(i) );
		}

		  System.out.println();//Move to the next line to print the next row.           

		    }
		    
		connection.close();
	
		rs.close();
		return columnValue;


	}

}

