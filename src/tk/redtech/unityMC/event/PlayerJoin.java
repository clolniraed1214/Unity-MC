package tk.redtech.unityMC.event;

import java.sql.ResultSet;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tk.redtech.database.SQLiteDBConnect;

public class PlayerJoin implements Listener {
	
	private SQLiteDBConnect db;
	
	public PlayerJoin (SQLiteDBConnect db) {
		this.db = db;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		try {
			ResultSet rs = db.query("SELECT gold FROM players WHERE name = '" + event.getPlayer().getName() + "';");
			rs.getInt("gold");
			rs.close();
			db.closeStmt();
		} catch (Exception e) {
			try {
				db.runCommand("INSERT INTO players (name, gold, purchase, homes) VALUES ('" + event.getPlayer().getName() + "', 0, 'null',  0);");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
