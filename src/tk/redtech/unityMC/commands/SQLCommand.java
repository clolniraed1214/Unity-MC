package tk.redtech.unityMC.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.redtech.database.SQLiteDBConnect;

public class SQLCommand implements CommandExecutor {

	private SQLiteDBConnect db;
	
	public SQLCommand (SQLiteDBConnect db) {
		this.db = db;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (!player.isOp()) {
				player.sendMessage("You are not allowed to use this command!");
				return false;
			}
		}
		
		String sql = "";
		int loopNum = 0;
		for (String arg : args) {
			sql += arg;
			if (!(loopNum == args.length)) {
				sql += " ";
			}
		}
		
		db.runCommand(sql);
		
		return true;
	}
	
}
