package tk.redtech.unityMC.commands;

import java.sql.ResultSet;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.redtech.database.SQLiteDBConnect;
import tk.redtech.unityMC.functions.PermCheck;
import tk.redtech.unityMC.functions.PlayerCheck;

public class GoldCoins implements CommandExecutor {

	private SQLiteDBConnect db;

	public GoldCoins(SQLiteDBConnect db) {
		this.db = db;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (args.length == 0) {
			if (!(PlayerCheck.check(sender)))
				return false;
			Player player = (Player) sender;
			int playerGold = getGold(player.getName(), db);
			player.sendMessage(ChatColor.DARK_AQUA + "Currect Gold Coin Balance: " + ChatColor.RED + playerGold);
			return true;
		}

		if (sender instanceof Player) {
			if (!PermCheck.permCheck((Player) sender, "unity.gold")) {
				return false;
			}
		}
		
		if (args.length == 1) {
			int gold = getGold(args[0], db);
			if (gold != -1) {
				sender.sendMessage(args[0] + " has " + gold + " gold coins.");
			}
		}

		if (args.length == 3) {
			int changeGold = Integer.parseInt(args[2]);
			int gold = getGold(args[1], db);
			if (gold == -1)
				sender.sendMessage("That player does not exist!");

			if (args[0].equalsIgnoreCase("set")) {
				setGold(args[1], changeGold, db);
				sender.sendMessage(ChatColor.DARK_AQUA + "You now have " + String.valueOf(changeGold) + " Gold Coins!");
			} else if (args[0].equalsIgnoreCase("give")) {
				setGold(args[1], getGold(args[1], db) + changeGold, db);
				sender.sendMessage(
						ChatColor.DARK_AQUA + "You have been given " + String.valueOf(changeGold) + " Gold Coins!");
			} else if (args[0].equalsIgnoreCase("take")) {
				setGold(args[1], getGold(args[1], db) - changeGold, db);
				sender.sendMessage(
						ChatColor.DARK_AQUA + String.valueOf(changeGold) + " Gold Coins have been taken away!");
			} else {
				properUse(sender);
				sender.sendMessage("BLAH");
				sender.sendMessage(args[0]);
				return false;
			}
		}

		return true;
	}

	private static void properUse(CommandSender sender) {
		sender.sendMessage("Proper Usage is: /gold <set/give/take> <player> <amount>");
	}

	public static int getGold(String playerName, SQLiteDBConnect db) {
		try {
			ResultSet rs = db.query("SELECT gold FROM players WHERE name = '" + playerName + "';");
			int playerGold = 0;
			playerGold = rs.getInt("gold");
			rs.close();
			db.closeStmt();
			return playerGold;
		} catch (Exception e) {
			e.printStackTrace();
			return -1; // This is if the player is non-existent, I'll know
		}
	}

	public static void setGold(String playerName, int gold, SQLiteDBConnect db) {
		db.runCommand("UPDATE players SET gold = " + gold + " WHERE name = '" + playerName + "';");
	}
	
	public static void changeGold(String playerName, int amount, SQLiteDBConnect db) {
		int gold = getGold(playerName, db);
		db.closeStmt();
		gold += amount;
		setGold(playerName, gold, db);
	}

}
