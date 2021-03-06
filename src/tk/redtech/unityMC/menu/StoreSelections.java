package tk.redtech.unityMC.menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import tk.redtech.database.SQLiteDBConnect;
import tk.redtech.unityMC.UnityMC;
import tk.redtech.unityMC.commands.GoldCoins;

public class StoreSelections {
	
	private SQLiteDBConnect db;
	private UnityMC plugin;
	private Player player;
	
	public static HashMap<Integer, Integer> housePrices = new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;

		{
			put(0, 10);
			put(1, 20);
			put(2, 30);
			put(3, 40);
			put(4, 50);
		}
	};
	
	public StoreSelections(SQLiteDBConnect db, UnityMC plugin) {
		this.db = db;
		this.plugin = plugin;
	}
	
	public void recievePurchaseRequest(String name, Player player) {
		this.player = player;

		switch (name) {
		case "Portable Enderchest":
			attemptPurchase("Portable Enderchest", player, "essentials.enderchest", new String[] {}, 50);
			break;
		case "Portable Workbench":
			attemptPurchase("Portable Workbench", player, "essentials.workbench", new String[] {}, 20);
			break;
		case "Additional Home":
			buyHome(player);
			break;
		case "Icarus Wings":
			if (attemptPurchase("Icarus' Wings", player, "unity.wings", new String[] { "fly %s" }, 50))
				buyFly();
			break;
		case "Custom Hat":
			attemptPurchase("Custom Hat", player, "essentials.hat", new String[] {}, 10);
			break;
		case "Repair Item":
			attemptPurchase("Repair Item", player, "null", new String[] {"repairitem %s"}, 15);
			break;
		case "Angel Trip":
			attemptPurchase("Angel Trip", player, "essentials.top", new String[] {}, 30);
			break;
		default:
			player.sendMessage(ChatColor.AQUA + "Item not implemented! Sorry!");
		}

		db.runCommand("UPDATE players SET purchase = 'null' WHERE name = '" + player.getName() + "';");
	}

	private boolean attemptPurchase(String purchaseName, Player player, String markerPerm, String[] commands,
			int price) {
		if (player.hasPermission(new Permission(markerPerm)) && !markerPerm.equals("null")) {
			player.sendMessage(ChatColor.GREEN + "You already own that item!");
			return false;
		}

		if (GoldCoins.getGold(player.getName(), db) >= price) {
			GoldCoins.changeGold(player.getName(), price * -1, db);
			for (String command : commands) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), String.format(command, player.getName()));
			}
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
					String.format("pex user %s add " + markerPerm, player.getName()));
			player.sendMessage(ChatColor.GREEN + "Thank you for purchasing the " + ChatColor.GOLD + purchaseName
					+ ChatColor.GREEN + "!");
			Bukkit.getLogger().info(player.getName() + " purchased " + purchaseName);
		} else {
			player.sendMessage(ChatColor.RED + "You do not have the money to buy this!\n"
					+ "Go to our BuyCraft website to buy more!");
		}

		return true;
	}

	private void buyHome(Player player) {
		int playerHomes = getHouses(player, db);
		if (playerHomes >= housePrices.size()) {
			player.sendMessage("You already have the max number of homes!");
			return;
		}

		int price = housePrices.get(playerHomes);
		String permission = "essentials.sethome.multiple." + (playerHomes + 2) + "homes";
		boolean incHouses = attemptPurchase("Extra House", player, permission, new String[] {}, price);
		if (incHouses) {
			db.runCommand(
					"UPDATE players SET homes = " + (playerHomes + 1) + " WHERE name = '" + player.getName() + "';");
		}
	}

	public static int getHouses(Player player, SQLiteDBConnect db) {
		ResultSet rs = db.query("SELECT homes FROM players WHERE name = '" + player.getName() + "';");

		try {
			int houses = rs.getInt("homes");
			rs.close();
			db.closeStmt();
			return houses;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	private Player getPlayer() {
		return player;
	}

	private void buyFly() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public final Player player = getPlayer();

			@Override
			public void run() {
				player.sendMessage(ChatColor.RED + "Fly ending in 3 Minutes!");
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public final String playerName = player.getName();

					@Override
					public void run() {
						if (player.isOnline()) {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fly " + playerName);
							db.runCommand("UPDATE players SET fly = 'false' WHERE name = '" + playerName + "';");
						} else {
							db.runCommand("UPDATE players SET fly = 'offline' WHERE name = '" + playerName + "';");
						}
						
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
								"pex user " + playerName + " remove unity.wings");

					}
				}, 3600L);
			}
		}, 32400L);
	}
	
}
