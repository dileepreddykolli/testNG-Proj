package com.framework.utilities;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static String[][] readData(String filename, String sheetname) {
		String[][] data = null;
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\TestData\\" + filename); // to read file
			XSSFWorkbook wb = new XSSFWorkbook(fis); //initialize excel and read file
			XSSFSheet sh = wb.getSheet(sheetname); //read excel sheet
			int totalRows= sh.getPhysicalNumberOfRows(); //total rows with data
			int totalcolumns = sh.getRow(0).getPhysicalNumberOfCells(); //total columns with in the row having data
			data = new String[totalRows-1][totalcolumns];
			for (int i=1;i<totalRows;i++) {
				for(int j=0;j<totalcolumns;j++) {
					data[i-1][j]=sh.getRow(i).getCell(j).getStringCellValue();
				}
			}
																													
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;

	}

}
