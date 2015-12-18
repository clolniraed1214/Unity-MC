package tk.redtech.unityMC;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import tk.redtech.database.SQLiteDBConnect;
import tk.redtech.unityMC.commands.ConfirmPurchase;
import tk.redtech.unityMC.commands.GoldCoins;
import tk.redtech.unityMC.commands.SQLCommand;
import tk.redtech.unityMC.commands.Store;
import tk.redtech.unityMC.event.MenuSelect;
import tk.redtech.unityMC.event.PlayerJoin;

public class UnityMC extends JavaPlugin {
	
	private final PluginDescriptionFile pdfFile = getDescription();
	private final Logger logger = getLogger();
	private MenuSelect invClick;
	private Broadcasts broadcasts = new Broadcasts();
	
	private SQLiteDBConnect db;
	
	public void onEnable() {
		logger.info(pdfFile.getName() + " has been enabled!");
		
		registerConfig();
		this.db = new SQLiteDBConnect("plugins/Unity-MC/database.db");
		initializeDB();
		
		registerIntances();
		registerCommands();
		registerEvents();
		
		registerBroadcasts();
	}

	public void onDisable() {
		logger.info(pdfFile.getName() + " has been disabled!");
		db.closeDB();
	}
	
	private void initializeDB() {
		db.createTable("players", "name TEXT, gold INTEGER, purchase TEXT, homes INTEGER, fly TEXT");
	}
	
	private void registerIntances() {
		invClick = new MenuSelect(this.db, this);
		
	}
	
	private void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerJoin(db), this);
		pm.registerEvents(invClick, this);
	}
	
	private void registerCommands() {
		getCommand("gold").setExecutor(new GoldCoins(db));
		getCommand("store").setExecutor(new Store(db));
		getCommand("confirm").setExecutor(new ConfirmPurchase(db, invClick));
		getCommand("inject").setExecutor(new SQLCommand(db));
	}
	
	private void registerConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	private void registerBroadcasts() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				Bukkit.broadcastMessage(broadcasts.getMessage());
			}			
		}, 12000L, 24000L);
	}
}
