package tk.redtech.unityMC.event.entity;

import java.util.ArrayList;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class CreatureSpawn implements Listener {

	private static ArrayList<SpawnReason> reasons = new ArrayList<SpawnReason>() {
		private static final long serialVersionUID = 1L;

		{
			this.add(SpawnReason.BREEDING);
			this.add(SpawnReason.CHUNK_GEN);
			this.add(SpawnReason.NATURAL);
		}
	};

	private static ArrayList<EntityType> creatures = new ArrayList<EntityType>() {
		private static final long serialVersionUID = 1L;

		{
			this.add(EntityType.COW);
			this.add(EntityType.PIG);
			this.add(EntityType.SHEEP);
			this.add(EntityType.CHICKEN);
			this.add(EntityType.SQUID);
			this.add(EntityType.RABBIT);
			this.add(EntityType.HORSE);
			this.add(EntityType.WOLF);
		}
	};

	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		if (creatures.contains(event.getEntity().getType())) {
			if (reasons.contains(event.getSpawnReason())) {
				if (Math.random() >= .5) {
					event.setCancelled(true);
				}
			}
		}
	}

}
