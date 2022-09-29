package myminecraftessential.myminecraftessential.handlers;

import myminecraftessential.myminecraftessential.MyMinecraftEssential;
import myminecraftessential.myminecraftessential.files.Quivers;
import myminecraftessential.myminecraftessential.inventories.Quiver;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class QuiverClose implements Listener
{
    MyMinecraftEssential plugin;
    public QuiverClose(MyMinecraftEssential plugin) {this.plugin = plugin;}

    @EventHandler
    public void onQuiverClose(InventoryCloseEvent event)
    {
        if(event.getInventory().getHolder() instanceof Quiver)
        {
            Quivers.setQuiver(event.getPlayer().getUniqueId().toString(), event.getInventory());
        }
    }
}
