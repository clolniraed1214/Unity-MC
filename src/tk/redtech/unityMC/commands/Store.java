package tk.redtech.unityMC.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import tk.redtech.unityMC.functions.EasyItem;
import tk.redtech.unityMC.functions.PlayerCheck;

public class Store implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!PlayerCheck.check(sender)) return false;
		Player player = (Player) sender;
		
		Inventory inv = Bukkit.createInventory(null, 9, "Premium Store");
		ArrayList<String> loreAdd;
		
		// 1 Extra Home
		loreAdd = EasyItem.getLore(new String[]{"One-Time Pay", ChatColor.GOLD + "5 Gold Coins"});
		inv.setItem(0, EasyItem.item(Material.BED, 1, "1 Extra Home", loreAdd));
		
		// Access to /enderchest
		loreAdd = EasyItem.getLore(new String[]{"/Enderchest Access", "1-Time Pay", ChatColor.GOLD + "50 Gold Coins"});
		inv.setItem(1, EasyItem.item(Material.ENDER_CHEST, 1, "Portable Enderchest", loreAdd));
		
		// Access to /workbench
		loreAdd = EasyItem.getLore(new String[]{"/Workbench Access", "1-Time Pay", ChatColor.GOLD + "10 Gold Coins"});
		inv.setItem(2, EasyItem.item(Material.WORKBENCH, 1, "Portable Workbench", loreAdd));
		
		player.openInventory(inv);
		
		return true;
	}

}
