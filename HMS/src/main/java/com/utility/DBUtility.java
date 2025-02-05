package com.utility;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBUtility {
	private static Connection con = null;
	private DBUtility() {
		
	}
	 public static Connection getDBConnection() {

	 	try {
	  Class.forName("com.mysql.cj.jdbc.Driver");
	  
	  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root","Pankaj@123"); 
	
	} catch (Exception e) { 
		System.out.println("DBUTILITY  " + e);

		 }
	  return con; 
	}
	 

}
