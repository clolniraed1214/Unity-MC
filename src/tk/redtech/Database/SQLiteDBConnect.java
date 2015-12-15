package tk.redtech.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDBConnect {
	
	private Connection conn = null;
	private Statement stmt = null;
	private boolean initialized = false;
	
	public SQLiteDBConnect(String name) {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + name);
			stmt = conn.createStatement();
		} catch (Exception e) {
			return;
		}
		
		initialized = true;
	}
	
	public boolean runCommand(String sql) {
		try {
			System.out.println("Attempting to run command");
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			return false;
		}
		return true;		
	}
	
	public ResultSet query(String query) {
		try {
			System.out.println("Returning Query");
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public boolean tableExists(String tableName) {
		try {
			stmt.executeQuery("SELECT * FROM " + tableName);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Connection getConn() {
		return conn;
	}
	
	public boolean getInitialized() {
		return initialized;
	}
}
