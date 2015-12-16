package tk.redtech.unityMC.functions;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EasyItem {
	public static ItemStack item(Material material, int count, String name, List<String> lore) {
		ItemStack item = new ItemStack(material, count);
		
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(name);
		itemMeta.setLore(lore);
		
		item.setItemMeta(itemMeta);
		return item;
	}
	
	public static ArrayList<String> getLore(String[] loreItems) {
		ArrayList<String> lore = new ArrayList<>();
		for (String item : loreItems) {
			lore.add(item);
		}
		
		return lore;
	}
}
