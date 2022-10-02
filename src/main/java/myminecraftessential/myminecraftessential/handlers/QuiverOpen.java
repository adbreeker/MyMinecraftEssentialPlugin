package myminecraftessential.myminecraftessential.handlers;

import myminecraftessential.myminecraftessential.MyMinecraftEssential;
import myminecraftessential.myminecraftessential.inventories.Quiver;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class QuiverOpen implements Listener
{
    MyMinecraftEssential plugin;
    public QuiverOpen(MyMinecraftEssential plugin) {this.plugin = plugin;}

    @EventHandler
    public void onQuiverOpen(PlayerInteractEvent event)
    {
        if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)
        {
            if(event.getPlayer().isSneaking())
            {
                if(event.getItem() != null)
                {
                    if(event.getItem().getType() == Material.BOW)
                    {
                        Player player = event.getPlayer();
                        Quiver quiver = new Quiver(player.getUniqueId().toString());
                        player.openInventory(quiver.getInventory());
                        new BukkitRunnable()
                        {
                            @Override
                            public void run()
                            {
                                for(ItemStack item : quiver.getInventory())
                                {
                                    if(item != null)
                                    {
                                        if(item.getType() != Material.ARROW && item.getType() != Material.TIPPED_ARROW && item.getType() != Material.SPECTRAL_ARROW)
                                        {
                                            player.getInventory().addItem(item);
                                            quiver.getInventory().remove(item);
                                            player.sendMessage("§cYou can only put arrows in quiver");
                                        }
                                    }
                                }
                                if(player.getOpenInventory().getTitle()!="Quiver")
                                {
                                    cancel();
                                }
                            }
                        }.runTaskTimer(plugin, 1, 1);

                    }
                }
            }
        }
    }
}
