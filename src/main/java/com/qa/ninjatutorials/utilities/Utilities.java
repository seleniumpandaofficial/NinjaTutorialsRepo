package com.qa.ninjatutorials.utilities;

import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {
	
	public static String generateDateTimeStamp() {
		Date date = new Date();
		String timeStamp =  date.toString().replace(" ", "_").replace(":", "_");
		return "seleniumpanda" + timeStamp + "@gmail.com";
		
	}
	
	public static final int IMPLICIT_WAIT_TIME = 10;
	public static final int PAGELOAD_TIME = 10;
	
	@SuppressWarnings("resource")
	public static Object[][] getDataFromExcel(String sheetName) throws Exception {
		XSSFWorkbook workbook = null;
		FileInputStream ip = new FileInputStream("C:\\Users\\PIIT\\eclipse-workspace\\TestNG_Hybrid_TutorialsNinja\\src\\main\\java\\com\\qa\\ninjatutorials\\utilities\\NinjaTestData.xlsx");
		workbook = new XSSFWorkbook(ip);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cols];
		
		for(int i=0 ; i<rows ; i++) {
		  XSSFRow row = sheet.getRow(i+1);
		  
		   for(int j=0 ; j<cols ; j++) {
			   XSSFCell cell = row.getCell(j);
			     CellType celltype = cell.getCellType();
			     
			     switch(celltype) {
			     
			     case STRING:
			    	 data[i][j] = cell.getStringCellValue();
			    	 break;
			    	 
			     case NUMERIC:
			    	 data[i][j] = Integer.toString((int)cell.getNumericCellValue());
			    	 break;
			     }
		   }
		}
		
		return data;
		}

}
