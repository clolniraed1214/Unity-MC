package tk.redtech.unityMC;

import org.bukkit.ChatColor;

public class Broadcasts {

	public static String[] messages = new String[] {
			ChatColor.BLUE + "Check out our store using /store!\n" + "To get more Gold, type /buy",
			ChatColor.BLUE + "Join our Steam group at:\n" + "https://steamcommunity.com/groups/unityminecraft",
			ChatColor.BLUE + "Support our server by donating\n" + "in the /buy menu!",
			ChatColor.BLUE + "Want to fly? Want a custom hat?\n" + "If so, visit our store with /store!",
			ChatColor.BLUE + "Invite your friends! The thing that\n" + "truly makes this fun is many people!" };

	public static String getMessage() {
		int random = (int) Math.floor(Math.random() * messages.length);
		return messages[random];
	}
}
