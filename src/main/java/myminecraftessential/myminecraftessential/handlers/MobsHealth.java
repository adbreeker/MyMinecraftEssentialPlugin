package myminecraftessential.myminecraftessential.handlers;

import myminecraftessential.myminecraftessential.MyMinecraftEssential;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class MobsHealth implements Listener
{
    MyMinecraftEssential plugin;
    public MobsHealth(MyMinecraftEssential plugin) {this.plugin = plugin;}

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event)
    {
        System.out.println("Spawnie: " + event.getEntityType());
        event.getEntity().setCustomName(event.getEntityType().toString() + " ❤ " + event.getEntity().getHealth());
        event.getEntity().setCustomNameVisible(true);
    }
}
