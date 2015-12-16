package tk.redtech.unityMC.event;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tk.redtech.database.SQLiteDBConnect;
import tk.redtech.unityMC.UnityMC;

public class PlayerJoin implements Listener {
	
	private SQLiteDBConnect db;
	
	public PlayerJoin (UnityMC plugin, SQLiteDBConnect db) {
		this.db = db;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		ResultSet rs = db.query("SELECT gold FROM players WHERE name = '" + event.getPlayer().getName() + "';");
		try {
			rs.getInt("gold");
		} catch (SQLException e) {
			db.runCommand("INSERT INTO players (name, gold) VALUES ('" + event.getPlayer().getName() + "', 0;");
		}
	}
	
}
