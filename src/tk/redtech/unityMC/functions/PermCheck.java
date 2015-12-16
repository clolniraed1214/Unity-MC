package tk.redtech.unityMC.functions;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PermCheck {
	public static boolean permCheck(Player player, String perm) {
		if (!player.hasPermission(perm)) {
			player.sendMessage(ChatColor.RED + "You do not have access to that command!");
			return false;
		} else {
			return true;
		}
	}
}
