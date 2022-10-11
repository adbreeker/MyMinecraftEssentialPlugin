package myminecraftessential.myminecraftessential.handlers;

import myminecraftessential.myminecraftessential.MyMinecraftEssential;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class MobsHealth implements Listener
{
    MyMinecraftEssential plugin;
    public MobsHealth(MyMinecraftEssential plugin) {this.plugin = plugin;}

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event)
    {
        if(event.getEntity().getCustomName() == null)
        {
            event.getEntity().setCustomName(event.getEntityType().toString() + " §4❤§f " + event.getEntity().getHealth());
            event.getEntity().setCustomNameVisible(false);
        }
        else
        {
            String name = event.getEntity().getCustomName().split(" §4❤§f ")[0];
            event.getEntity().setCustomName(name + " §4❤§f " + event.getEntity().getHealth());
            event.getEntity().setCustomNameVisible(false);
        }
    }

    @EventHandler
    public void onMobDamage(EntityDamageEvent event)
    {
        if(event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof Player))
        {
            LivingEntity mob = (LivingEntity) event.getEntity();
            if(event.getEntity().getCustomName() == null)
            {
                mob.setCustomName(mob.getType().toString() + " §4❤§f " + (mob.getHealth()-event.getDamage()));
                mob.setCustomNameVisible(false);
            }
            else
            {
                String name = mob.getCustomName().split(" §4❤§f ")[0];
                mob.setCustomName(name + " §4❤§f " + (mob.getHealth()-event.getDamage()));
                mob.setCustomNameVisible(false);
            }
        }
    }

    @EventHandler
    public void onMobRegenerate(EntityRegainHealthEvent event)
    {
        if(event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof Player))
        {
            LivingEntity mob = (LivingEntity) event.getEntity();
            if(event.getEntity().getCustomName() == null)
            {
                mob.setCustomName(mob.getType().toString() + " §4❤§f " + (mob.getHealth()+event.getAmount()));
                mob.setCustomNameVisible(false);
            }
            else
            {
                String name = mob.getCustomName().split(" §4❤§f ")[0];
                mob.setCustomName(name + " §4❤§f " + (mob.getHealth()+event.getAmount()));
                mob.setCustomNameVisible(false);
            }
        }
    }
}
