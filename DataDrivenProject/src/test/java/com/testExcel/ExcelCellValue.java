package com.testExcel;

import com.excelFactory.ExcelTable;

public class ExcelCellValue {

	public static void main(String[] args) throws Throwable {
		String excelPath = "./TestData/Login Test Cases.xlsx";
		String value = ExcelTable.getCellData(0, 2, 2, excelPath);
		System.out.println(value);
	}

}
