package com.webcrm.generic.fileutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	
	

		public String getDataFromPropertiesFile(String key) throws IOException {
		FileInputStream fis= new FileInputStream("C:\\Users\\AnjuR\\eclipse-workspace\\Selenium-WEB-CRM-GUI-Framework\\configAppData\\commonData.properties");
		Properties prop= new Properties();
		prop.load(fis);	
		String data=prop.getProperty(key);
		return data;
			
			
		}	
	
}
