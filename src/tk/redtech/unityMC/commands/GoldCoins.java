package tk.redtech.unityMC.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GoldCoins implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length != 2) {
			properUse(sender);
			return false;
		}
		
		properUse(sender);
		return false;
	}
	
	private static void properUse(CommandSender sender) {
		sender.sendMessage("Proper Usage is: /gc <set/give/take> <amount>");
	}

}
