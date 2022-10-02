package myminecraftessential.myminecraftessential.handlers;

import myminecraftessential.myminecraftessential.MyMinecraftEssential;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

public class InsertingToQuiver implements Listener
{
    MyMinecraftEssential plugin;
    public InsertingToQuiver(MyMinecraftEssential plugin){this.plugin = plugin;}

    @EventHandler
    public void insertToQuiver(InventoryMoveItemEvent event)
    {

    }
}
