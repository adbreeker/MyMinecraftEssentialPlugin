package myminecraftessential.myminecraftessential.handlers;

import myminecraftessential.myminecraftessential.MyMinecraftEssential;
import myminecraftessential.myminecraftessential.files.Quivers;
import myminecraftessential.myminecraftessential.inventories.Quiver;
import myminecraftessential.myminecraftessential.items.ArrowManager;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class ArrowShoot implements Listener
{
    MyMinecraftEssential plugin;

    public ArrowShoot(MyMinecraftEssential plugin) {this.plugin = plugin;}

    @EventHandler
    public void onArrowShoot(EntityShootBowEvent event)
    {
        if(event.getBow().getItemMeta().getLore().get(0).split(": ")[0].equals("Selected Arrow"))
        {
            if(event.getEntity() instanceof Player)
            {
                Player player = (Player) event.getEntity();
                Quiver quiver = new Quiver(player.getUniqueId().toString());
                for(ItemStack arrow_in_quiver : quiver.getInventory())
                {
                    if (arrow_in_quiver != null)
                    {
                        if(ExplosiveArrow(event, arrow_in_quiver, player, quiver))
                        {
                            return;
                        }
                        if(TeleportArrow(event, arrow_in_quiver, player, quiver))
                        {
                            return;
                        }
                        if(ExtinguishingArrow(event, arrow_in_quiver, player, quiver))
                        {
                            return;
                        }
                    }
                }
            }
        }
    }

    public boolean ExplosiveArrow(EntityShootBowEvent event, ItemStack arrow_in_quiver, Player player, Quiver quiver)
    {
        if (arrow_in_quiver.getItemMeta().getDisplayName().equals(event.getBow().getItemMeta().getLore().get(0).split(": ")[1]))
        {
            if (arrow_in_quiver.getType().equals(ArrowManager.ExplosiveArrow.getType()))
            {
                if (arrow_in_quiver.getItemMeta().equals(ArrowManager.ExplosiveArrow.getItemMeta()))
                {
                    event.setConsumeItem(false);
                    arrow_in_quiver.setAmount(arrow_in_quiver.getAmount() - 1);
                    Quivers.setQuiver(player.getUniqueId().toString(), quiver.getInventory());
                    Entity arrow = event.getProjectile();
                    BukkitTask task = new BukkitRunnable() {
                        Location arrow_loc = event.getProjectile().getLocation();

                        @Override
                        public void run() {
                            if (arrow.getLocation().equals(arrow_loc)) {
                                arrow_loc.createExplosion(3, false, true);
                                arrow.remove();
                                cancel();
                            } else {
                                arrow_loc = arrow.getLocation();
                            }
                        }
                    }.runTaskTimer(plugin, 5, 1);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean TeleportArrow(EntityShootBowEvent event, ItemStack arrow_in_quiver, Player player, Quiver quiver)
    {
        if (arrow_in_quiver.getItemMeta().getDisplayName().equals(event.getBow().getItemMeta().getLore().get(0).split(": ")[1]))
        {
            if (arrow_in_quiver.getType().equals(ArrowManager.ExplosiveArrow.getType()))
            {
                if (arrow_in_quiver.getItemMeta().equals(ArrowManager.ExplosiveArrow.getItemMeta()))
                {
                    event.setConsumeItem(false);
                    arrow_in_quiver.setAmount(arrow_in_quiver.getAmount() - 1);
                    Quivers.setQuiver(player.getUniqueId().toString(), quiver.getInventory());
                    Entity arrow = event.getProjectile();
                    BukkitTask task = new BukkitRunnable() {
                        Location arrow_loc = event.getProjectile().getLocation();

                        @Override
                        public void run() {
                            if (arrow.getLocation().equals(arrow_loc)) {
                                arrow_loc.createExplosion(3, false, true);
                                arrow.remove();
                                cancel();
                            } else {
                                arrow_loc = arrow.getLocation();
                            }
                        }
                    }.runTaskTimer(plugin, 5, 1);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean ExtinguishingArrow(EntityShootBowEvent event, ItemStack arrow_in_quiver, Player player, Quiver quiver)
    {
        if (arrow_in_quiver.getItemMeta().getDisplayName().equals(event.getBow().getItemMeta().getLore().get(0).split(": ")[1]))
        {
            if (arrow_in_quiver.getType().equals(ArrowManager.ExplosiveArrow.getType()))
            {
                if (arrow_in_quiver.getItemMeta().equals(ArrowManager.ExplosiveArrow.getItemMeta()))
                {
                    event.setConsumeItem(false);
                    arrow_in_quiver.setAmount(arrow_in_quiver.getAmount() - 1);
                    Quivers.setQuiver(player.getUniqueId().toString(), quiver.getInventory());
                    Entity arrow = event.getProjectile();
                    BukkitTask task = new BukkitRunnable() {
                        Location arrow_loc = event.getProjectile().getLocation();

                        @Override
                        public void run() {
                            if (arrow.getLocation().equals(arrow_loc)) {
                                arrow_loc.createExplosion(3, false, true);
                                arrow.remove();
                                cancel();
                            } else {
                                arrow_loc = arrow.getLocation();
                            }
                        }
                    }.runTaskTimer(plugin, 5, 1);
                    return true;
                }
            }
        }
        return false;
    }
}
