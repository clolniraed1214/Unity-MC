package tk.redtech.unityMC.functions;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerCheck {
	public static boolean check(CommandSender sender) {
		if ( !(sender instanceof Player) ) {
			sender.sendMessage("You must be a player to use this command!");
			return false;
		}
		return true;
	}
}
