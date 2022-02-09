package com.zee.zee5pp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// Get the Data Base Connection and to close the connection.
public class DBUtils {

	public static DBUtils dbutils = null;
	static Properties properties = null;
	Connection connection = null;

	// Singleton Design Pattern
	private DBUtils() throws IOException {
		// TODO Auto-generated constructor stub
		properties = this.loadProperties();
	}

	// private static DBUtils dbUtils = null;

	public static DBUtils getInstance() throws IOException {
		if (dbutils == null)
			dbutils = new DBUtils();
		return dbutils;
	}

	private Properties loadProperties() throws IOException {
		// Read the Propertied file
		InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("application.properties");

		Properties properties = new Properties();
		properties.load(inputStream);
		// It will read the properties internall

		return properties;
	}

	// Properties properties = null;

	public Connection getConnection() {
		// Code to establish the connection with the data base.
		try {
			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection(properties.getProperty("jdbc.url"),
						properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
				connection.setAutoCommit(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//
//		DBUtils dbUtils = DBUtils.getInstance();
//		dbUtils.getConnectin();
//
//		Connection connection = dbUtils.getConnectin();
//		System.out.println(connection != null);
//	}

}
