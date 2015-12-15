package tk.redtech.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteTest {
	public static void main(String[] args) {
		System.out.println("Creating Database");
		Database db = new Database("bin/test.db");
		System.out.println("Creating Table");
		if (!db.tableExists("celebs")) db.runCommand("CREATE TABLE celebs (id INTEGER, name TEXT, age INTEGER);");
		System.out.println("Inserting Data");
		db.runCommand("INSERT INTO celebs (id, name, age) VALUES (1, \'Collin Read\', 15);");
		ResultSet rs = db.query("SELECT * FROM celebs;");
		try {
			do {
				System.out.println(rs.getString("name"));
			} while (rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}