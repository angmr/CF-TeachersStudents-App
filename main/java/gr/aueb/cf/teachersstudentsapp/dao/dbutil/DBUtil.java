package gr.aueb.cf.teachersstudentsapp.dao.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static Connection conn;
	
	/**
	 * This is a helper class,
	 * therefore no instances will be available.
	 */
	private DBUtil() {}
	
	public static Connection openConnection() throws SQLException{
		String url = "jdbc:mysql://localhost:3306/tsdb?serverTimeZone=UTC";
		String username = "tsuser";
		String password = "Tsuser";

		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}
	
	public static void closeConnection() throws SQLException{
		conn.close();
	}
}
