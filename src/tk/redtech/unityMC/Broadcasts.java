package tk.redtech.unityMC;

import java.util.ArrayList;

import org.bukkit.ChatColor;

public class Broadcasts {
	public static ArrayList<String> messages = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;

	{
		this.add(ChatColor.BLUE + "Check out our store using /store!\n"
				+ "To get more Gold, type /buy");
		this.add(ChatColor.BLUE + "Join our Steam group at:\n"
				+ "https://steamcommunity.com/groups/unityminecraft");
		this.add(ChatColor.BLUE + "Support our server by donating\n"
				+ "in the /buy menu!");
		this.add(ChatColor.BLUE + "Want to fly? Want a custom hat?\n"
				+ "If so, visit our store with /store!");
		this.add(ChatColor.BLUE + "Invite your friends! The thing that\n"
				+ "truly makes this fun is many people!");
	}};
	
	
	public static String getMessage() {
		int random = (int) Math.floor(Math.random() * messages.size());
		return messages.get(random);
	}
}
