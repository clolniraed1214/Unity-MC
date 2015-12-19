package tk.redtech.unityMC.commands;

import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.redtech.database.SQLiteDBConnect;
import tk.redtech.unityMC.functions.PlayerCheck;
import tk.redtech.unityMC.menu.StoreSelections;

public class ConfirmPurchase implements CommandExecutor {
	
	private SQLiteDBConnect db;
	private StoreSelections store;
	
	public ConfirmPurchase(SQLiteDBConnect db, StoreSelections store) {
		this.db = db;
		this.store = store;
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
			
			store.recievePurchaseRequest(purchase, player);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
