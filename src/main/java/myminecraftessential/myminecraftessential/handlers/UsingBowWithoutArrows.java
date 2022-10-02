package myminecraftessential.myminecraftessential.handlers;

import myminecraftessential.myminecraftessential.MyMinecraftEssential;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class UsingBowWithoutArrows implements Listener
{
    MyMinecraftEssential plugin;
    public UsingBowWithoutArrows(MyMinecraftEssential plugin) {this.plugin = plugin;}

    @EventHandler
    public void onBowDraw(PlayerInteractEvent event)
    {
        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction()==Action.RIGHT_CLICK_BLOCK)
        {
            if(event.getHand() == EquipmentSlot.HAND && event.getItem().getType() == Material.BOW)
            {
                ItemStack ofhand = event.getPlayer().getInventory().getItemInOffHand();
                if(ofhand.getType() != Material.ARROW && ofhand.getType() != Material.TIPPED_ARROW && ofhand.getType() != Material.SPECTRAL_ARROW)
                {
                    Inventory inv = event.getPlayer().getInventory();
                    if(!inv.contains(Material.ARROW) && !inv.contains(Material.TIPPED_ARROW) && !inv.contains(Material.SPECTRAL_ARROW))
                    {
                        event.getPlayer().sendMessage("§cYou can't use bow without at least one arrow in your inventory");
                    }
                }
            }
        }
    }
}
