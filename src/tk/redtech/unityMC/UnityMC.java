package tk.redtech.unityMC;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import tk.redtech.database.SQLiteDBConnect;
import tk.redtech.unityMC.commands.GoldCoins;
import tk.redtech.unityMC.event.PlayerJoin;

public class UnityMC extends JavaPlugin {
	
	private final PluginDescriptionFile pdfFile = getDescription();
	private final Logger logger = getLogger();
	
	private SQLiteDBConnect db;
	
	public void onEnable() {
		logger.info(pdfFile.getName() + " has been enabled!");
		
		registerConfig();
		this.db = new SQLiteDBConnect("plugins/Unity-MC/database.db");
		initializeDB();
		
		registerCommands();
		registerEvents();
	}

	public void onDisable() {
		logger.info(pdfFile.getName() + " has been disabled!");
		db.closeDB();
	}
	
	private void initializeDB() {
		db.createTable("players", "name TEXT, gold INTEGER");
	}
	
	private void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerJoin(null, db), this);
	}
	
	private void registerCommands() {
		getCommand("gold").setExecutor(new GoldCoins(db));
	}
	
	private void registerConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
}
