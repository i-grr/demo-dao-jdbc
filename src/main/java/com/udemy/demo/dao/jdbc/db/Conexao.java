package com.udemy.demo.dao.jdbc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// remover o public
public class Conexao implements AutoCloseable {
	
	private static String url = "jdbc:postgresql://localhost:5432/coursejdbc";
	private static String user = "postgres";
	private static String pass = "admin";
	
	private static Connection conn;
	
	public static Connection abreConexao() {
		try {
			conn = DriverManager.getConnection(url, user, pass);
		} 
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		
		return conn;
	}

	public void close() throws Exception {
		conn.close();
	}

}
