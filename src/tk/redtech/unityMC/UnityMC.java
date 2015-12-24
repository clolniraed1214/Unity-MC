package tk.redtech.unityMC;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import tk.redtech.database.SQLiteDBConnect;
import tk.redtech.unityMC.commands.ConfirmPurchase;
import tk.redtech.unityMC.commands.GoldCoins;
import tk.redtech.unityMC.commands.RepairItem;
import tk.redtech.unityMC.commands.SQLCommand;
import tk.redtech.unityMC.commands.ServerMessage;
import tk.redtech.unityMC.commands.Store;
import tk.redtech.unityMC.event.entity.CreatureSpawn;
import tk.redtech.unityMC.event.player.MenuSelect;
import tk.redtech.unityMC.event.player.PlayerJoin;
import tk.redtech.unityMC.menu.StoreSelections;

public class UnityMC extends JavaPlugin {
	
	private final PluginDescriptionFile pdfFile = getDescription();
	private final Logger logger = getLogger();
	private StoreSelections store;
	
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
		this.store = new StoreSelections(db, this);
	}
	
	private void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerJoin(db), this);
		pm.registerEvents(new MenuSelect(db), this);
		pm.registerEvents(new CreatureSpawn(), this);
	}
	
	private void registerCommands() {
		getCommand("gold").setExecutor(new GoldCoins(db));
		getCommand("store").setExecutor(new Store(db));
		getCommand("confirm").setExecutor(new ConfirmPurchase(db, store));
		getCommand("inject").setExecutor(new SQLCommand(db));
		getCommand("serverad").setExecutor(new ServerMessage());
		getCommand("repairitem").setExecutor(new RepairItem());
	}
	
	private void registerConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	private void registerBroadcasts() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				Bukkit.broadcastMessage(Broadcasts.getMessage());
			}			
		}, 18000L, 24000L);
	}
}
