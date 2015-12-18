package tk.redtech.unityMC;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.ChatColor;

public class Broadcasts {
	public ArrayList<String> messages = new ArrayList<String>();
	
	public Broadcasts() {
		messages.add(ChatColor.BLUE + "Check out our store using /store!\n"
				+ "To get more Gold, type /buy");
		messages.add(ChatColor.BLUE + "Join our Steam group at:\n"
				+ "https://steamcommunity.com/groups/unityminecraft");
		messages.add("Support our server by donating\n"
				+ "in the /buy menu!");
		messages.add("Want to fly? Want a custom hat?\n"
				+ "If so, visit our store with /store!");
	}
	
	public String getMessage() {
		Random randomizer = new Random();
		String message = messages.get(randomizer.nextInt(messages.size()));
		
		return message;
	}
}
