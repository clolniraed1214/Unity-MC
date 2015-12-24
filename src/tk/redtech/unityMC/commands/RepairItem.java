package tk.redtech.unityMC.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import tk.redtech.unityMC.functions.PermCheck;

public class RepairItem implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			if (!PermCheck.permCheck((Player) sender, "unity.repair")) return false; 
		}
		
		if (args.length != 1) {
			properUse(sender);
			return false;
		}
		
		Player player = Bukkit.getPlayer(args[0]);
		if (player == null) sender.sendMessage(ChatColor.RED + "That player does not exist!");
		
		ItemStack item = player.getItemInHand();
		
		if (item == null) {
			sender.sendMessage(ChatColor.RED + "Player does not have an item in their hand!");
		}
		
		item.setDurability((short) 0);
		player.setItemInHand(item);
		
		return true;
	}
	
	private void properUse(CommandSender sender) {
		sender.sendMessage(ChatColor.RED + "Proper use is /repairitem <player>");
	}

}
