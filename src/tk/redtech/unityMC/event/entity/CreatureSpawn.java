package tk.redtech.unityMC.event.entity;

import java.util.ArrayList;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class CreatureSpawn implements Listener {
	
	private static ArrayList<SpawnReason> reasons = new ArrayList<SpawnReason>() {
		private static final long serialVersionUID = 1L;
	{	this.add(SpawnReason.BREEDING);
		this.add(SpawnReason.CHUNK_GEN);
		this.add(SpawnReason.NATURAL);}
	};
	
	@EventHandler
	public void onCreatureSpawn (CreatureSpawnEvent event) {
		if (reasons.contains(event.getSpawnReason())) {
			if (Math.random() >= .5) {
				event.setCancelled(true);
			}
		}
	}
	
}
