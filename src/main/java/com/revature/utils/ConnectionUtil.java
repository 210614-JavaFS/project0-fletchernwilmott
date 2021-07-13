package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public static Connection getConnection() throws SQLException {
		
		try {
			Class.forName("org.postgresql.Driver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:postgresql://java-react-batch.cchuth94zv3i.us-east-2.rds.amazonaws.com:5432/demos";
		
		// this is bad practice
		String username = "postgres";
		String password = "password";
		return DriverManager.getConnection(url, username, password);
	}
	
	
	
}
