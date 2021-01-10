package com.udemy.demo.dao.jdbc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB implements AutoCloseable {
	
	private static String url = "jdbc:postgresql://localhost:5432/coursejdbc";
	private static String user = "postgres";
	private static String pass = "admin";
	
	private static Connection conn;
	
	public static Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, user, pass);
		} 
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		
		return conn;
	}

	public void close() {
		try {
			conn.close();
		} 
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
	}
	
	public static void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

}
