package com.webcrm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBaseUtility {
	
	Connection conn;

	public void getDbConnection() {

		try {
			Driver driver = new Driver();

			// Register Driver

			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice_DataBase","root","root");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeDbConnection() {

		try {
			  
		         conn.close();
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ResultSet executeSelectQuery(String query) {
		ResultSet result = null;
		try {

			Statement sta = conn.createStatement();
			result = sta.executeQuery(query);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public int executeNonSelectQuery(String query) {

		int result = 0;
		try {
			Statement stat = conn.createStatement();
			result=stat.executeUpdate(query);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return result;
	}	
	


}
