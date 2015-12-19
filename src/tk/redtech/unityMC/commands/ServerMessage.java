package tk.redtech.unityMC.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.redtech.unityMC.Broadcasts;
import tk.redtech.unityMC.functions.PermCheck;

public class ServerMessage implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			if (!PermCheck.permCheck((Player) sender, "unity.serverad")) return false;
		}
		
		Bukkit.broadcastMessage(Broadcasts.getMessage());
		
		return false;
	}

}
