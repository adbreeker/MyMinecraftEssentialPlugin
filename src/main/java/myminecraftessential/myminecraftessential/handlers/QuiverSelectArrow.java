package myminecraftessential.myminecraftessential.handlers;

import myminecraftessential.myminecraftessential.MyMinecraftEssential;
import myminecraftessential.myminecraftessential.inventories.Quiver;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.TippedArrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.List;

public class QuiverSelectArrow implements Listener
{
    MyMinecraftEssential plugin;
    public QuiverSelectArrow(MyMinecraftEssential plugin) {this.plugin = plugin;}

    @EventHandler
    public void onArrowSelect(InventoryClickEvent event)
    {
        if(event.getClickedInventory() != null)
        {
            if(event.getClickedInventory().getHolder() instanceof Quiver)
            {
                if(event.isRightClick())
                {
                    if(event.getCurrentItem() != null)
                    {
                        event.setCancelled(true);
                        List<String> lore = new ArrayList<>();

                        if(event.getCurrentItem().getItemMeta().getDisplayName()!= "")
                        {
                            lore.add("§7Selected Arrow: " + event.getCurrentItem().getItemMeta().getDisplayName());
                        }
                        else
                        {
                            if(event.getCurrentItem().getType() == Material.TIPPED_ARROW)
                            {
                                PotionMeta meta = (PotionMeta) event.getCurrentItem().getItemMeta();
                                lore.add("§7Selected Arrow: " + meta.getBasePotionData().getType().toString());
                            }
                            else
                            {
                                lore.add("§7Selected Arrow: " + event.getCurrentItem().getType());
                            }
                        }

                        event.getWhoClicked().getInventory().getItemInMainHand().setLore(lore);
                    }
                }
            }
        }
    }
}
