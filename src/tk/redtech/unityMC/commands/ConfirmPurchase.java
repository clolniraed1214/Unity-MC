package tk.redtech.unityMC.commands;

import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.redtech.database.SQLiteDBConnect;
import tk.redtech.unityMC.event.MenuSelect;
import tk.redtech.unityMC.functions.PlayerCheck;

public class ConfirmPurchase implements CommandExecutor {
	
	private SQLiteDBConnect db;
	private MenuSelect invClick;
	
	public ConfirmPurchase(SQLiteDBConnect db, MenuSelect invClick) {
		this.db = db;
		this.invClick = invClick;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!PlayerCheck.check(sender)) return false;
		
		Player player = (Player) sender;
		try {
			String purchase = db.query("SELECT purchase FROM players WHERE name = '" + player.getName() + "';").getString("purchase");
			if (purchase.equals("null")) {
				player.sendMessage(ChatColor.RED + "You do not have a pending purchase!");
				return false;
			}
			invClick.recievePurchaseRequest(purchase, player);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
