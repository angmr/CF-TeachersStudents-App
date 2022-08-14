package gr.aueb.cf.teachersstudentsapp.dao.dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
	
	/**
	 * No instances of this class should be available
	 */
	private DBHelper() {}
	
	public static void eraseData() throws SQLException {
		Connection conn = DBUtil.openConnection();
		
		// Disable referential integrity
		conn.prepareStatement("SET @@foreign_key_checks = 0").executeUpdate();
		
		ResultSet rs = conn.prepareStatement("SELECT TABLE_NAME from information_schema.tables WHERE TABLE_SCHEMA = 'tsdb'").executeQuery();
		List<String> tables = mapRStoList(rs);
		
		for (String table : tables) {
			PreparedStatement pst = conn.prepareStatement("DELETE FROM ?");
			pst.setString(1,  table); 
			pst.executeUpdate();
			
			// Reset sequence
			pst = conn.prepareStatement("ALTER TABLE ? AUTO_INCREMENT=1");
			pst.setString(1,  table);
			pst.executeUpdate();
		}
		
		conn.prepareStatement("SET @@foreign_key_checks = 1").executeUpdate();
		DBUtil.closeConnection();
	}
	
	private static List<String> mapRStoList(ResultSet rs) throws SQLException{
		List<String> list = new ArrayList<>();
		while (rs.next()) {
			list.add(rs.getString("TABLE_NAME"));
		}
		
		return list;
	}
}
