package tk.redtech.unityMC;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import tk.redtech.Database.SQLiteDBConnect;
import tk.redtech.unityMC.commands.GoldCoins;

public class UnityMC extends JavaPlugin {
	
	private final PluginDescriptionFile pdfFile = getDescription();
	private final Logger logger = getLogger();
	private SQLiteDBConnect db = new SQLiteDBConnect("test.db");
	
	public void onEnable() {
		logger.info(pdfFile.getName() + " has been enabled!");
	}
	
	public void onDisable() {
		logger.info(pdfFile.getName() + " has been disabled!");
	}
	
	public void registerCommands() {
		getCommand("gold").setExecutor(new GoldCoins(db));
	}
}
