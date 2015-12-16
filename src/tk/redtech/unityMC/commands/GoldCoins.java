package tk.redtech.unityMC.commands;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.redtech.database.SQLiteDBConnect;
import tk.redtech.unityMC.functions.PlayerCheck;

public class GoldCoins implements CommandExecutor {
	
	private SQLiteDBConnect db;
	
	public GoldCoins (SQLiteDBConnect db) {
		this.db = db;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {		
		if (args.length != 3 && args.length != 0) {
			properUse(sender);
			return false;
		}
		
		
		if (args.length == 0) {
			if ( !(PlayerCheck.check(sender)) ) return false;
			Player player = (Player) sender;
			int playerGold = getGold(player.getName());
			player.sendMessage(ChatColor.DARK_AQUA + "Currect Gold Coin Balance: " + ChatColor.RED + playerGold);
		}
		
		if (args.length == 3) {
			int changeGold = Integer.parseInt(args[2]);
			
			if (args[0] == "set") {
				setGold(args[1], changeGold);
				sender.sendMessage(ChatColor.DARK_AQUA + "You now have " + String.valueOf(changeGold) + " Gold Coins!");
			} else if (args[0] == "give") {
				setGold(args[1], getGold(args[1]) + changeGold);
				sender.sendMessage(ChatColor.DARK_AQUA + "You have been given " + String.valueOf(changeGold) + " Gold Coins!");
			} else if (args[0] == "take") {
				setGold(args[1], getGold(args[1]) - changeGold);
				sender.sendMessage(ChatColor.DARK_AQUA + String.valueOf(changeGold) + " Gold Coins have been taken away!");
			} else {
				properUse(sender);
				return false;
			}
		}
		
		return true;
	}
	
	private static void properUse(CommandSender sender) {
		sender.sendMessage("Proper Usage is: /gc <set/give/take> <player> <amount>");
	}
	
	private int getGold(String playerName) {
		ResultSet rs = db.query("SELECT gold FROM players WHERE name = \'" + playerName + "\';");
		int playerGold = 0;
		try {
			playerGold = rs.getInt("gold");
			return playerGold;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1; //This is if the player is non-existent, I'll know
		}
	}
	
	private void setGold(String playerName, int gold) {
		db.runCommand("UPDATE players SET gold = " + gold + " WHERE name = \'" + playerName + "\';");
	}

}
