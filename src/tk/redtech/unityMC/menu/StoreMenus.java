package tk.redtech.unityMC.menu;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import tk.redtech.database.SQLiteDBConnect;
import tk.redtech.unityMC.event.MenuSelect;
import tk.redtech.unityMC.functions.EasyItem;

public class StoreMenus {

	public static void mainMenu(Player player, SQLiteDBConnect db) {
		Inventory inv = Bukkit.createInventory(null, 9, "Premium Store");
		ArrayList<String> loreAdd;

		if (MenuSelect.getHouses(player, db) < MenuSelect.housePrices.size()) {
			// 1 Extra Home
			loreAdd = EasyItem.getLore(new String[] { "One-Time Pay", ChatColor.GOLD
					+ String.valueOf(MenuSelect.housePrices.get(MenuSelect.getHouses(player, db))) + " Gold Coins" });
			inv.setItem(8, EasyItem.item(Material.BED, 1, "Additional Home", loreAdd));
		}

		// Access to /enderchest
		loreAdd = EasyItem
				.getLore(new String[] { "/Enderchest Access", "1-Time Pay", ChatColor.GOLD + "50 Gold Coins" });
		inv.setItem(0, EasyItem.item(Material.ENDER_CHEST, 1, "Portable Enderchest", loreAdd));

		// Access to /workbench
		loreAdd = EasyItem
				.getLore(new String[] { "/Workbench Access", "1-Time Pay", ChatColor.GOLD + "10 Gold Coins" });
		inv.setItem(1, EasyItem.item(Material.WORKBENCH, 1, "Portable Workbench", loreAdd));

		// 30 Minutes of Flying
		loreAdd = EasyItem
				.getLore(new String[] { "30-Minute time", ChatColor.GOLD + "50 Gold Coins" });
		inv.setItem(2, EasyItem.item(Material.FEATHER, 1, "Icarus Wings", loreAdd));

		player.openInventory(inv);
	}
}
