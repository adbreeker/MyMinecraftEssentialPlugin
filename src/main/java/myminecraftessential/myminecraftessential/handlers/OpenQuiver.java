package myminecraftessential.myminecraftessential.handlers;

import myminecraftessential.myminecraftessential.MyMinecraftEssential;
import myminecraftessential.myminecraftessential.inventories.Quiver;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OpenQuiver implements Listener
{
    MyMinecraftEssential plugin;
    public OpenQuiver(MyMinecraftEssential plugin) {this.plugin = plugin;}

    @EventHandler
    public void onQuiverOpen(PlayerInteractEvent event)
    {
        if(event.getAction() == Action.LEFT_CLICK_AIR)
        {
            if(event.getItem() != null)
            {
                if(event.getItem().getType() == Material.BOW)
                {
                    Player player = event.getPlayer();
                    Quiver quiver = new Quiver(player.getUniqueId().toString());
                    player.openInventory(quiver.getInventory());
                }
            }
        }
    }
}
