package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

public class DbTableWriteInExcel {
	Connection connection;
    static PreparedStatement preparedStatement;
    
    
	public static String WriteDBTable(String query, String path) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522/orcl", "hr", "hr");

		preparedStatement = connection.prepareStatement(query);
		ResultSet  rs = preparedStatement.executeQuery();
		
		
		Workbook workbook = new Workbook();
		Worksheet worksheet = workbook.getWorksheets().get(0);

		// Import the ResultSet to the worksheet.

		worksheet.getCells().importResultSet(rs, "A1", true);

		// Save the excel file.

		workbook.save(path);
		System.out.println("Excelwrite was successfull");
		// step5 close the connection object
		
		connection.close();

		rs.close();
		return "Write Excel";
	}

}
