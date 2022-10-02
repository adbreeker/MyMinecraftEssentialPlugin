package myminecraftessential.myminecraftessential.handlers;

import myminecraftessential.myminecraftessential.MyMinecraftEssential;
import myminecraftessential.myminecraftessential.files.Quivers;
import myminecraftessential.myminecraftessential.inventories.Quiver;
import myminecraftessential.myminecraftessential.items.ArrowManager;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class ArrowShoot implements Listener
{
    MyMinecraftEssential plugin;

    public ArrowShoot(MyMinecraftEssential plugin) {this.plugin = plugin;}

    @EventHandler
    public void onArrowShoot(EntityShootBowEvent event)
    {

        if(event.getEntity() instanceof Player)
        {
            boolean allreadyShoot = false;
            Player player = (Player) event.getEntity();
            Quiver quiver = new Quiver(player.getUniqueId().toString());
            if(event.getBow().getItemMeta().getLore().get(0).split(": ")[0].equals("Selected Arrow"))
            {
                for(ItemStack arrow_in_quiver : quiver.getInventory())
                {

                    if (arrow_in_quiver != null)
                    {
                        ItemStack arrowBeforeShoot = arrow_in_quiver.clone();
                        if(NonspecialArrow(event, arrow_in_quiver, player, quiver))
                        {
                            CheckArrowAmount(event, arrow_in_quiver, quiver, arrowBeforeShoot);
                            allreadyShoot = true;
                            break;
                        }
                        if (arrow_in_quiver.getItemMeta().getDisplayName().equals(event.getBow().getItemMeta().getLore().get(0).split(": ")[1]))
                        {
                            if(ExplosiveArrow(event, arrow_in_quiver, player, quiver))
                            {
                                CheckArrowAmount(event, arrow_in_quiver, quiver, arrowBeforeShoot);
                                allreadyShoot = true;
                                break;
                            }
                            if(TeleportArrow(event, arrow_in_quiver, player, quiver))
                            {
                                CheckArrowAmount(event, arrow_in_quiver, quiver, arrowBeforeShoot);
                                allreadyShoot = true;
                                break;
                            }
                            if(ExtinguishingArrow(event, arrow_in_quiver, player, quiver))
                            {
                                CheckArrowAmount(event, arrow_in_quiver, quiver, arrowBeforeShoot);
                                allreadyShoot = true;
                                break;
                            }
                        }
                    }
                }
                player.updateInventory();
            }
            if(!allreadyShoot)
            {
                if(ExplosiveArrow(event, event.getConsumable(), player, quiver))
                {
                    return;
                }
                if(TeleportArrow(event, event.getConsumable(), player, quiver))
                {
                    return;
                }
                if(ExtinguishingArrow(event, event.getConsumable(), player, quiver))
                {
                    return;
                }
            }
        }
    }

    public void CheckArrowAmount(EntityShootBowEvent event, ItemStack arrow_in_quiver, Quiver quiver, ItemStack arrowBeforeShoot)
    {
        if(arrow_in_quiver.getAmount() <= 0 || arrow_in_quiver.getType() == Material.AIR)
        {
            boolean noArrows = true;
            for(ItemStack item : quiver.getInventory())
            {
                if(item != null)
                {
                    if(item.getAmount() > 0)
                    {
                        if(item.asOne().equals(arrowBeforeShoot.asOne()))
                        {
                            noArrows = false;
                            break;
                        }
                    }
                }
            }
            if(noArrows)
            {
                List<String> lore = new ArrayList<>();
                lore.add("Selected Arrow: None");
                event.getBow().setLore(lore);
            }
        }
    }

    public boolean NonspecialArrow(EntityShootBowEvent event, ItemStack arrow_in_quiver, Player player, Quiver quiver)
    {
        if(arrow_in_quiver.getType() == Material.getMaterial(event.getBow().getItemMeta().getLore().get(0).split(": ")[1]))
        {
            if(arrow_in_quiver.getType() == Material.ARROW)
            {
                event.setConsumeItem(false);
                arrow_in_quiver.setAmount(arrow_in_quiver.getAmount() - 1);
                Quivers.setQuiver(player.getUniqueId().toString(), quiver.getInventory());
                return true;
            }
            if(arrow_in_quiver.getType() == Material.SPECTRAL_ARROW)
            {
                event.setConsumeItem(false);
                arrow_in_quiver.setAmount(arrow_in_quiver.getAmount() - 1);
                Quivers.setQuiver(player.getUniqueId().toString(), quiver.getInventory());
                SpectralArrow arrow = (SpectralArrow) event.getProjectile().getWorld().spawnEntity(event.getProjectile().getLocation(), EntityType.SPECTRAL_ARROW);
                arrow.setVelocity(event.getProjectile().getVelocity());
                event.setProjectile(arrow);
                return true;
            }
        }
        else
        {
            if(arrow_in_quiver.getType() == Material.TIPPED_ARROW)
            {
                PotionMeta meta = (PotionMeta) arrow_in_quiver.getItemMeta();
                if(meta.getBasePotionData().getType().toString().equals(event.getBow().getItemMeta().getLore().get(0).split(": ")[1]))
                {
                    event.setConsumeItem(false);
                    arrow_in_quiver.setAmount(arrow_in_quiver.getAmount() - 1);
                    Quivers.setQuiver(player.getUniqueId().toString(), quiver.getInventory());
                    Arrow arrow = (Arrow) event.getProjectile();
                    arrow.setBasePotionData(meta.getBasePotionData());
                    return true;
                }
            }
        }

        return false;
    }
    public boolean ExplosiveArrow(EntityShootBowEvent event, ItemStack arrow_in_quiver, Player player, Quiver quiver)
    {
        if(arrow_in_quiver.getType().equals(ArrowManager.ExplosiveArrow.getType()))
        {
            if(arrow_in_quiver.getItemMeta().equals(ArrowManager.ExplosiveArrow.getItemMeta()))
            {
                event.setConsumeItem(false);
                arrow_in_quiver.setAmount(arrow_in_quiver.getAmount() - 1);
                Quivers.setQuiver(player.getUniqueId().toString(), quiver.getInventory());
                Entity arrow = event.getProjectile();
                BukkitTask task = new BukkitRunnable() {
                    Location arrow_loc = event.getProjectile().getLocation();
                     @Override
                     public void run()
                     {
                         if(arrow.getLocation().equals(arrow_loc))
                         {
                             arrow_loc.createExplosion(4);
                             arrow.remove();
                             cancel();
                         }
                         else
                         {
                             arrow_loc = arrow.getLocation();
                         }
                     }
                }.runTaskTimer(plugin, 5, 1);
                return true;
            }
        }
        return false;
    }

    public boolean TeleportArrow(EntityShootBowEvent event, ItemStack arrow_in_quiver, Player player, Quiver quiver)
    {
        if(arrow_in_quiver.getType().equals(ArrowManager.TeleportArrow.getType()))
        {
            if(arrow_in_quiver.getItemMeta().equals(ArrowManager.TeleportArrow.getItemMeta()))
            {
                event.setConsumeItem(false);
                arrow_in_quiver.setAmount(arrow_in_quiver.getAmount() - 1);
                Quivers.setQuiver(player.getUniqueId().toString(), quiver.getInventory());
                Entity arrow = event.getProjectile();
                BukkitTask task = new BukkitRunnable()
                {
                    Location arrow_loc = event.getProjectile().getLocation();

                    @Override
                    public void run()
                    {
                        if(arrow.getLocation().equals(arrow_loc))
                        {
                            player.teleport(arrow_loc);
                            arrow.remove();
                            cancel();
                        }
                        else
                        {
                            arrow_loc = arrow.getLocation();
                        }
                    }
                }.runTaskTimer(plugin, 5, 1);
                return true;
            }
        }
        return false;
    }

    public boolean ExtinguishingArrow(EntityShootBowEvent event, ItemStack arrow_in_quiver, Player player, Quiver quiver)
    {
        if(arrow_in_quiver.getType().equals(ArrowManager.ExtinguishingArrow.getType()))
        {
            if(arrow_in_quiver.getItemMeta().equals(ArrowManager.ExtinguishingArrow.getItemMeta()))
            {
                event.setConsumeItem(false);
                arrow_in_quiver.setAmount(arrow_in_quiver.getAmount() - 1);
                Quivers.setQuiver(player.getUniqueId().toString(), quiver.getInventory());
                Entity arrow = event.getProjectile();
                BukkitTask task = new BukkitRunnable()
                {
                    Location arrow_loc = event.getProjectile().getLocation();

                    @Override
                    public void run()
                    {
                        if(arrow.getLocation().equals(arrow_loc))
                        {
                            ThrownPotion potion = (ThrownPotion) arrow_loc.getWorld().spawnEntity(arrow_loc, EntityType.SPLASH_POTION);
                            PotionMeta meta = potion.getPotionMeta();
                            meta.setColor(Color.BLUE);
                            potion.setPotionMeta(meta);
                            int radius = 10;
                            for(int x = radius*(-1); x<=radius; x++)
                            {
                                for(int y = radius*(-1); y<=radius; y++)
                                {
                                    for(int z = radius*(-1); z<=radius; z++)
                                    {
                                        Block block = arrow_loc.getWorld().getBlockAt(arrow_loc.getBlockX()+x, arrow_loc.getBlockZ()+y, arrow_loc.getBlockZ()+z);
                                        if(block.getType() == Material.FIRE)
                                        {
                                            block.setType(Material.AIR);
                                        }
                                        if(block.getType() == Material.LAVA)
                                        {
                                            block.setType(Material.OBSIDIAN);
                                        }
                                    }
                                }
                            }
                            arrow.remove();
                            cancel();
                        }
                        else
                        {
                            arrow_loc = arrow.getLocation();
                        }
                    }
                }.runTaskTimer(plugin, 5, 1);
                return true;
            }
        }
        return false;
    }
}
