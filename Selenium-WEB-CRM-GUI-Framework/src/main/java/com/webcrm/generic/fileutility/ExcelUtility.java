package com.webcrm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	public String getDataFromExcel(String sheetName,int rowNum,int cellNum) throws EncryptedDocumentException, IOException {

		FileInputStream fis= new FileInputStream("C:\\Users\\AnjuR\\eclipse-workspace\\Selenium-WEB-CRM-GUI-Framework\\testData\\testScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		String data= wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).toString();
		wb.close();
		return data;
		
	}

	public int getRowCount(String SheeetName) throws EncryptedDocumentException, IOException {
		FileInputStream fis= new FileInputStream("C:\\Users\\AnjuR\\eclipse-workspace\\Selenium-WEB-CRM-GUI-Framework\\testData\\testScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		int rowCount=wb.getSheet(SheeetName).getLastRowNum();
		wb.close();
		return rowCount;
	}

	public void setDataIntoExcel(String sheetName,int rowNum,int cellNum,String data) throws EncryptedDocumentException, IOException {

		FileInputStream fis= new FileInputStream("C:\\Users\\AnjuR\\eclipse-workspace\\Selenium-WEB-CRM-GUI-Framework\\testData\\testScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum);
		
		//open in write mode
		FileOutputStream fos= new FileOutputStream("C:\\Users\\AnjuR\\eclipse-workspace\\Selenium-WEB-CRM-GUI-Framework\\testData\\testScriptData.xlsx");
		wb.write(fos);
		wb.close();	
	
	
	}
}
