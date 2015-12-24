package tk.redtech.unityMC.event.player;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tk.redtech.database.SQLiteDBConnect;

public class PlayerJoin implements Listener {

	private SQLiteDBConnect db;

	public PlayerJoin(SQLiteDBConnect db) {
		this.db = db;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) throws CommandException, SQLException {
		try {
			ResultSet rs = db.query("SELECT gold FROM players WHERE name = '" + event.getPlayer().getName() + "';");
			rs.getInt("gold");
			rs.close();
			db.closeStmt();
		} catch (Exception e) {
			try {
				db.runCommand("INSERT INTO players (name, gold, purchase, homes, fly) VALUES ('"
						+ event.getPlayer().getName() + "', 0, 'null',  0, 'false');");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		if (db.query("SELECT fly FROM players WHERE name = '" + event.getPlayer().getName() + "';").getString("fly").equals("offline")) {
			db.runCommand("UPDATE players SET fly = 'false' WHERE name = '" + event.getPlayer().getName() + "';");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fly " + event.getPlayer().getName());
			db.closeStmt();
		}
	}

}
