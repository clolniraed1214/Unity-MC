package tk.redtech.unityMC;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class UnityMC extends JavaPlugin {
	
	private final PluginDescriptionFile pdfFile = getDescription();
	private final Logger logger = getLogger();
	
	public void onEnable() {
		logger.info(pdfFile.getName() + " has been enabled!");
	}
	
	public void onDisable() {
		logger.info(pdfFile.getName() + " has been disabled!");
	}
}
