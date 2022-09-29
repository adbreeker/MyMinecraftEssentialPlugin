package myminecraftessential.myminecraftessential.handlers;

import myminecraftessential.myminecraftessential.MyMinecraftEssential;
import myminecraftessential.myminecraftessential.inventories.Quiver;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class QuiverSelectArrow implements Listener
{
    MyMinecraftEssential plugin;
    public QuiverSelectArrow(MyMinecraftEssential plugin) {this.plugin = plugin;}

    @EventHandler
    public void onArrowSelect(InventoryClickEvent event)
    {
        if(event.getInventory().getHolder() instanceof Quiver)
        {
            if(event.isRightClick())
            {
                event.setCancelled(true);
                List<String> lore = new ArrayList<>();
                lore.add("Selected Arrow: " + event.getCurrentItem().getItemMeta().getDisplayName());
                event.getWhoClicked().getInventory().getItemInMainHand().setLore(lore);
            }
        }
    }
}
