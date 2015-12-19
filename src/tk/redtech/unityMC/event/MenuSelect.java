package tk.redtech.unityMC.event;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import tk.redtech.database.SQLiteDBConnect;

public class MenuSelect implements Listener {

	private SQLiteDBConnect db;

	public MenuSelect(SQLiteDBConnect db) {
		this.db = db;
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		String invName = event.getInventory().getName();
		if (!(invName.equals("Premium Store")))
			return;

		event.setCancelled(true);

		String itemName = "Blah";
		try {
			itemName = event.getCurrentItem().getItemMeta().getDisplayName();
		} catch (Exception e) {
			return;
		}

		Player player = (Player) event.getWhoClicked();
		player.closeInventory();

		player.sendMessage(ChatColor.GREEN + "You have selected: " + ChatColor.GOLD + itemName);
		player.sendMessage(ChatColor.GREEN + "Type \"/confirm\" to complete purchase.");

		db.runCommand("UPDATE players SET purchase = '" + itemName + "' WHERE name = '" + player.getName() + "';");
	}
}
