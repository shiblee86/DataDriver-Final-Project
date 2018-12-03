package com.testdatabase;

import com.database.Database;
import com.database.DbTablePrint;
import com.database.DbTableWriteInExcel;

public class DBQueryTesting {
	
	static String path ="./Database Table to Excel/Employee_Table.xlsx";
	public static void main(String[] args) throws Throwable {
		String table = "Select * from Employees";
		String maxSalary = "Select max(salary) from employees";
		
		/*String tableBySalaryOrder ="Select * From employees Order by salary desc";
		
		String tableBySalaryOrderWithName ="Select first_name,last_name,salary From employees Order by salary desc";
		
		
		
		String maxSalaryWithName ="Select first_name, last_name, salary from employees "
				+ "Where salary = (select max(salary)from employees)";
		
		String secondHigestSalary= "Select * "
				+ "From (select employee_id,first_name,last_name,salary, DENSE_RANK() " + 
				"over(ORDER BY salary desc)  ranking "
				+ "from employees) "
				+ "Where ranking= 7";*/

		
		//DbTablePrint.getWholeDBTable(table);
		
		//Database.getDataTableColumn(table, 1);
		
		
		DbTableWriteInExcel.WriteDBTable(table,path);
		
		

	}

}
