package tk.redtech.unityMC.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.redtech.database.SQLiteDBConnect;
import tk.redtech.unityMC.functions.PlayerCheck;
import tk.redtech.unityMC.menu.StoreMenus;

public class Store implements CommandExecutor {
	
	SQLiteDBConnect db;
	public Store(SQLiteDBConnect db) {
		this.db = db;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!PlayerCheck.check(sender)) return false;
		Player player = (Player) sender;
		
		StoreMenus.mainMenu(player, db);
		
		return true;
	}

}
