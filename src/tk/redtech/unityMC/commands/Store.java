package tk.redtech.unityMC.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;

import tk.redtech.unityMC.functions.PlayerCheck;

public class Store implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!PlayerCheck.check(sender)) return false;
		
		Inventory inv = Bukkit.createInventory(null, 9, "Premium Store");
		
				
				
		return false;
	}

}
