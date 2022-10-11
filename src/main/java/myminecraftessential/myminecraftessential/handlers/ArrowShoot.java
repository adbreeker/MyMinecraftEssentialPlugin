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
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
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
            if(event.getBow().getItemMeta() != null)
            {
                if(event.getBow().getItemMeta().getLore() != null)
                {
                    if(event.getBow().getItemMeta().getLore().get(0).split(": ")[0].equals("§7Selected Arrow"))
                    {
                        for(ItemStack arrow_in_quiver : quiver.getInventory())
                        {

                            if (arrow_in_quiver != null)
                            {
                                ItemStack arrowBeforeShoot = arrow_in_quiver.clone();
                                if(NonSpecialArrow(event, arrow_in_quiver, player, quiver))
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
                                    if(PrisonArrow(event, arrow_in_quiver, player, quiver))
                                    {
                                        CheckArrowAmount(event, arrow_in_quiver, quiver, arrowBeforeShoot);
                                        allreadyShoot = true;
                                        break;
                                    }
                                    if(FrostArrow(event, arrow_in_quiver, player, quiver))
                                    {
                                        CheckArrowAmount(event, arrow_in_quiver, quiver, arrowBeforeShoot);
                                        allreadyShoot = true;
                                        break;
                                    }
                                    if(HomingArrow(event, arrow_in_quiver, player, quiver))
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
                }
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
                if(PrisonArrow(event, event.getConsumable(), player, quiver))
                {
                    return;
                }
                if(FrostArrow(event, event.getConsumable(), player, quiver))
                {
                    return;
                }
                if(HomingArrow(event, event.getConsumable(), player, quiver))
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
                lore.add("§7Selected Arrow: None");
                event.getBow().setLore(lore);
            }
        }
    }

    public void setArrow(EntityShootBowEvent event, String arrowType, PotionData data, List<PotionEffect> effects, Color color)
    {
        if(arrowType == "normal")
        {
            Arrow arrow = (Arrow) event.getProjectile().getWorld().spawnEntity(event.getProjectile().getLocation(), EntityType.ARROW);
            arrow.setShooter(event.getEntity());
            arrow.setVelocity(event.getProjectile().getVelocity());
            if(event.getProjectile() instanceof Arrow)
            {
                Arrow pom = (Arrow) event.getProjectile();
                arrow.setDamage(pom.getDamage());
                arrow.setCritical(pom.isCritical());
                arrow.setKnockbackStrength(pom.getKnockbackStrength());
                arrow.setFireTicks(pom.getFireTicks());
            }
            if(event.getProjectile() instanceof SpectralArrow)
            {
                SpectralArrow pom = (SpectralArrow) event.getProjectile();
                arrow.setDamage(pom.getDamage());
                arrow.setCritical(pom.isCritical());
                arrow.setKnockbackStrength(pom.getKnockbackStrength());
                arrow.setFireTicks(pom.getFireTicks());
            }
            if(color != null)
            {
                arrow.setColor(color);
            }
            event.setProjectile(arrow);
        }
        if(arrowType == "spectral")
        {
            SpectralArrow arrow = (SpectralArrow) event.getProjectile().getWorld().spawnEntity(event.getProjectile().getLocation(), EntityType.SPECTRAL_ARROW);
            arrow.setShooter(event.getEntity());
            arrow.setVelocity(event.getProjectile().getVelocity());
            if(event.getProjectile() instanceof Arrow)
            {
                Arrow pom = (Arrow) event.getProjectile();
                arrow.setDamage(pom.getDamage());
                arrow.setCritical(pom.isCritical());
                arrow.setKnockbackStrength(pom.getKnockbackStrength());
                arrow.setFireTicks(pom.getFireTicks());
            }
            if(event.getProjectile() instanceof SpectralArrow)
            {
                SpectralArrow pom = (SpectralArrow) event.getProjectile();
                arrow.setDamage(pom.getDamage());
                arrow.setCritical(pom.isCritical());
                arrow.setKnockbackStrength(pom.getKnockbackStrength());
                arrow.setFireTicks(pom.getFireTicks());
            }
            event.setProjectile(arrow);
        }
        if(arrowType == "tipped" && (data != null || effects != null))
        {
            Arrow arrow = (Arrow) event.getProjectile().getWorld().spawnEntity(event.getProjectile().getLocation(), EntityType.ARROW);
            arrow.setShooter(event.getEntity());
            arrow.setVelocity(event.getProjectile().getVelocity());
            if(event.getProjectile() instanceof Arrow)
            {
                Arrow pom = (Arrow) event.getProjectile();
                arrow.setDamage(pom.getDamage());
                arrow.setCritical(pom.isCritical());
                arrow.setKnockbackStrength(pom.getKnockbackStrength());
                arrow.setFireTicks(pom.getFireTicks());
            }
            if(event.getProjectile() instanceof SpectralArrow)
            {
                SpectralArrow pom = (SpectralArrow) event.getProjectile();
                arrow.setDamage(pom.getDamage());
                arrow.setCritical(pom.isCritical());
                arrow.setKnockbackStrength(pom.getKnockbackStrength());
                arrow.setFireTicks(pom.getFireTicks());
            }
            if(data != null)
            {
                arrow.setBasePotionData(data);
            }
            if(effects != null)
            {
                for(PotionEffect effect : effects)
                {
                    arrow.addCustomEffect(effect, true);
                }
            }
            if(color != null)
            {
                arrow.setColor(color);
            }
            event.setProjectile(arrow);
        }
    }

    public boolean NonSpecialArrow(EntityShootBowEvent event, ItemStack arrow_in_quiver, Player player, Quiver quiver)
    {
        if(arrow_in_quiver.getType() == Material.getMaterial(event.getBow().getItemMeta().getLore().get(0).split(": ")[1]))
        {
            if(arrow_in_quiver.getType() == Material.ARROW)
            {
                event.setConsumeItem(false);
                arrow_in_quiver.setAmount(arrow_in_quiver.getAmount() - 1);
                Quivers.setQuiver(player.getUniqueId().toString(), quiver.getInventory());
                setArrow(event, "normal", null, null, null);
                return true;
            }
            if(arrow_in_quiver.getType() == Material.SPECTRAL_ARROW)
            {
                event.setConsumeItem(false);
                arrow_in_quiver.setAmount(arrow_in_quiver.getAmount() - 1);
                Quivers.setQuiver(player.getUniqueId().toString(), quiver.getInventory());
                setArrow(event, "spectral", null, null, null);
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
                    setArrow(event, "tipped", meta.getBasePotionData(), null, null);
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
                setArrow(event, "normal", null, null, Color.RED);
                Arrow arrow = (Arrow) event.getProjectile();
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
                setArrow(event, "normal", null, null, Color.BLUE);
                Arrow arrow = (Arrow) event.getProjectile();
                arrow.setFireTicks(0);
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
                                        Block block = arrow_loc.getWorld().getBlockAt(arrow_loc.getBlockX()+x, arrow_loc.getBlockY()+y, arrow_loc.getBlockZ()+z);
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

    public boolean PrisonArrow(EntityShootBowEvent event, ItemStack arrow_in_quiver, Player player, Quiver quiver)
    {
        if(arrow_in_quiver.getType().equals(ArrowManager.PrisonArrow.getType()))
        {
            if(arrow_in_quiver.getItemMeta().equals(ArrowManager.PrisonArrow.getItemMeta()))
            {
                event.setConsumeItem(false);
                arrow_in_quiver.setAmount(arrow_in_quiver.getAmount() - 1);
                Quivers.setQuiver(player.getUniqueId().toString(), quiver.getInventory());
                List<PotionEffect> effects = new ArrayList<>();
                effects.add(new PotionEffect(PotionEffectType.SLOW, 20*30, 4, true, true));
                effects.add(new PotionEffect(PotionEffectType.REGENERATION, 20*10, 5, true, true));
                setArrow(event, "tipped", null, effects, Color.GRAY);
                Arrow arrow = (Arrow) event.getProjectile();
                arrow.setDamage(0.0);
                arrow.setKnockbackStrength(0);

                BukkitTask task = new BukkitRunnable()
                {
                    Location arrow_loc = event.getProjectile().getLocation();

                    @Override
                    public void run()
                    {
                        if(arrow.getLocation().equals(arrow_loc))
                        {
                            makePrison(arrow_loc.getBlock().getLocation());
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

    public void makePrison(Location c)
    {
        World w = c.getWorld();
        List<Block> blocks = new ArrayList<>();
        for(int y = -2; y<=2; y++)
        {
            if(y == -2 || y == 2)
            {
                blocks.add(w.getBlockAt(c.getBlockX(), c.getBlockY()+y, c.getBlockZ()));
                blocks.add(w.getBlockAt(c.getBlockX()+1, c.getBlockY()+y, c.getBlockZ()));
                blocks.add(w.getBlockAt(c.getBlockX()-1, c.getBlockY()+y, c.getBlockZ()));
                blocks.add(w.getBlockAt(c.getBlockX(), c.getBlockY()+y, c.getBlockZ()+1));
                blocks.add(w.getBlockAt(c.getBlockX(), c.getBlockY()+y, c.getBlockZ()-1));
                blocks.add(w.getBlockAt(c.getBlockX()+1, c.getBlockY()+y, c.getBlockZ()+1));
                blocks.add(w.getBlockAt(c.getBlockX()+1, c.getBlockY()+y, c.getBlockZ()-1));
                blocks.add(w.getBlockAt(c.getBlockX()-1, c.getBlockY()+y, c.getBlockZ()+1));
                blocks.add(w.getBlockAt(c.getBlockX()-1, c.getBlockY()+y, c.getBlockZ()-1));
                blocks.add(w.getBlockAt(c.getBlockX()+2, c.getBlockY()+y, c.getBlockZ()));
                blocks.add(w.getBlockAt(c.getBlockX()-2, c.getBlockY()+y, c.getBlockZ()));
                blocks.add(w.getBlockAt(c.getBlockX(), c.getBlockY()+y, c.getBlockZ()+2));
                blocks.add(w.getBlockAt(c.getBlockX(), c.getBlockY()+y, c.getBlockZ()-2));
            }
            else
            {
                blocks.add(w.getBlockAt(c.getBlockX()+3, c.getBlockY()+y, c.getBlockZ()));
                blocks.add(w.getBlockAt(c.getBlockX()+3, c.getBlockY()+y, c.getBlockZ()+1));
                blocks.add(w.getBlockAt(c.getBlockX()+3, c.getBlockY()+y, c.getBlockZ()-1));
                blocks.add(w.getBlockAt(c.getBlockX()-3, c.getBlockY()+y, c.getBlockZ()));
                blocks.add(w.getBlockAt(c.getBlockX()-3, c.getBlockY()+y, c.getBlockZ()+1));
                blocks.add(w.getBlockAt(c.getBlockX()-3, c.getBlockY()+y, c.getBlockZ()-1));
                blocks.add(w.getBlockAt(c.getBlockX(), c.getBlockY()+y, c.getBlockZ()+3));
                blocks.add(w.getBlockAt(c.getBlockX()+1, c.getBlockY()+y, c.getBlockZ()+3));
                blocks.add(w.getBlockAt(c.getBlockX()-1, c.getBlockY()+y, c.getBlockZ()+3));
                blocks.add(w.getBlockAt(c.getBlockX(), c.getBlockY()+y, c.getBlockZ()-3));
                blocks.add(w.getBlockAt(c.getBlockX()+1, c.getBlockY()+y, c.getBlockZ()-3));
                blocks.add(w.getBlockAt(c.getBlockX()-1, c.getBlockY()+y, c.getBlockZ()-3));

                blocks.add(w.getBlockAt(c.getBlockX()+2, c.getBlockY()+y, c.getBlockZ()-1));
                blocks.add(w.getBlockAt(c.getBlockX()+2, c.getBlockY()+y, c.getBlockZ()+1));
                blocks.add(w.getBlockAt(c.getBlockX()-2, c.getBlockY()+y, c.getBlockZ()-1));
                blocks.add(w.getBlockAt(c.getBlockX()-2, c.getBlockY()+y, c.getBlockZ()+1));
                blocks.add(w.getBlockAt(c.getBlockX()+1, c.getBlockY()+y, c.getBlockZ()+2));
                blocks.add(w.getBlockAt(c.getBlockX()-1, c.getBlockY()+y, c.getBlockZ()+2));
                blocks.add(w.getBlockAt(c.getBlockX()+1, c.getBlockY()+y, c.getBlockZ()-2));
                blocks.add(w.getBlockAt(c.getBlockX()-1, c.getBlockY()+y, c.getBlockZ()-2));

                blocks.add(w.getBlockAt(c.getBlockX()+2, c.getBlockY()+y, c.getBlockZ()+2));
                blocks.add(w.getBlockAt(c.getBlockX()+2, c.getBlockY()+y, c.getBlockZ()-2));
                blocks.add(w.getBlockAt(c.getBlockX()-2, c.getBlockY()+y, c.getBlockZ()+2));
                blocks.add(w.getBlockAt(c.getBlockX()-2, c.getBlockY()+y, c.getBlockZ()-2));
            }
        }

        for(Block block : blocks)
        {
            if(!block.isSolid())
            {
                if(block.getY() == c.getBlockY())
                {
                    block.setType(Material.IRON_BARS);
                }
                else
                {
                    if((block.getY() == c.getBlockY()+2 || block.getY() == c.getBlockY()-2) && block.getX() == c.getBlockX() && block.getZ() == c.getBlockZ())
                    {
                        block.setType(Material.GLOWSTONE);
                    }
                    else
                    {
                        block.setType(Material.OBSIDIAN);
                    }
                }
            }
        }
    }

    public boolean FrostArrow(EntityShootBowEvent event, ItemStack arrow_in_quiver, Player player, Quiver quiver)
    {
        if(arrow_in_quiver.getType().equals(ArrowManager.FrostArrow.getType()))
        {
            if(arrow_in_quiver.getItemMeta().equals(ArrowManager.FrostArrow.getItemMeta()))
            {
                event.setConsumeItem(false);
                arrow_in_quiver.setAmount(arrow_in_quiver.getAmount() - 1);
                Quivers.setQuiver(player.getUniqueId().toString(), quiver.getInventory());
                List<PotionEffect> effects = new ArrayList<>();
                effects.add(new PotionEffect(PotionEffectType.SLOW, 20*15, 5, true, true));
                setArrow(event, "tipped", null, effects, Color.AQUA);
                Arrow arrow = (Arrow) event.getProjectile();
                arrow.setFireTicks(0);
                arrow.setKnockbackStrength(0);

                BukkitTask task = new BukkitRunnable()
                {
                    Location arrow_loc = event.getProjectile().getLocation();

                    @Override
                    public void run()
                    {
                        if(arrow.getLocation().equals(arrow_loc) || (arrow.isInWater() && !arrow.isInRain()))
                        {
                            for(Entity entity : arrow.getNearbyEntities(2,2,2))
                            {
                                if(entity instanceof LivingEntity)
                                {
                                    new BukkitRunnable()
                                    {
                                        @Override
                                        public void run()
                                        {
                                            Location el;
                                            el = entity.getLocation();
                                            List<Block> blocks = new ArrayList<>();
                                            for(int x = -1; x<=1; x++)
                                            {
                                                for(int y = 0; y<=2; y++)
                                                {
                                                    for(int z = -1; z<=1; z++)
                                                    {
                                                        blocks.add(entity.getWorld().getBlockAt(el.getBlockX()+x, el.getBlockY()+y, el.getBlockZ()+z));
                                                    }
                                                }
                                            }
                                            blocks.add(entity.getWorld().getBlockAt(el.getBlockX()+2, el.getBlockY(), el.getBlockZ()));
                                            blocks.add(entity.getWorld().getBlockAt(el.getBlockX()+2, el.getBlockY()+1, el.getBlockZ()));
                                            blocks.add(entity.getWorld().getBlockAt(el.getBlockX()-2, el.getBlockY(), el.getBlockZ()));
                                            blocks.add(entity.getWorld().getBlockAt(el.getBlockX()-2, el.getBlockY()+1, el.getBlockZ()));
                                            blocks.add(entity.getWorld().getBlockAt(el.getBlockX(), el.getBlockY(), el.getBlockZ()+2));
                                            blocks.add(entity.getWorld().getBlockAt(el.getBlockX(), el.getBlockY()+1, el.getBlockZ()+2));
                                            blocks.add(entity.getWorld().getBlockAt(el.getBlockX(), el.getBlockY(), el.getBlockZ()-2));
                                            blocks.add(entity.getWorld().getBlockAt(el.getBlockX(), el.getBlockY()+1, el.getBlockZ()-2));
                                            blocks.add(entity.getWorld().getBlockAt(el.getBlockX(), el.getBlockY()+3, el.getBlockZ()));

                                            for(Block block : blocks)
                                            {
                                                if(!block.isSolid())
                                                {
                                                    block.setType(Material.ICE);
                                                    new BukkitRunnable()
                                                    {
                                                        @Override
                                                        public void run()
                                                        {
                                                            block.setType(Material.AIR);
                                                        }
                                                    }.runTaskLater(plugin, 4*20);
                                                }
                                            }
                                        }
                                    }.runTaskLater(plugin, 10);
                                }
                            }

                            int radius = 3;
                            for(int x = radius*(-1); x<=radius; x++)
                            {
                                for(int y = -2; y<=2; y++)
                                {
                                    for(int z = radius*(-1); z<=radius; z++)
                                    {
                                        Block block = arrow_loc.getWorld().getBlockAt(arrow_loc.getBlockX()+x, arrow_loc.getBlockY()+y, arrow_loc.getBlockZ()+z);
                                        if(block.getType() == Material.WATER)
                                        {
                                            block.setType(Material.BLUE_ICE);
                                            new BukkitRunnable()
                                            {
                                                int i = 0;
                                                @Override
                                                public void run()
                                                {
                                                    if(i==0)
                                                    {
                                                        block.setType(Material.PACKED_ICE);
                                                    }
                                                    if(i==1)
                                                    {
                                                        block.setType(Material.ICE);
                                                    }
                                                    if(i==2)
                                                    {
                                                        block.setType(Material.WATER);
                                                        cancel();
                                                    }
                                                    i++;
                                                }
                                            }.runTaskTimer(plugin, 3*20, 3*20);
                                        }
                                    }
                                }
                            }
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

    public boolean HomingArrow(EntityShootBowEvent event, ItemStack arrow_in_quiver, Player player, Quiver quiver)
    {
        if(arrow_in_quiver.getType().equals(ArrowManager.HomingArrow.getType()))
        {
            if(arrow_in_quiver.getItemMeta().equals(ArrowManager.HomingArrow.getItemMeta()))
            {
                double multiply = event.getProjectile().getVelocity().length();
                event.setConsumeItem(false);
                arrow_in_quiver.setAmount(arrow_in_quiver.getAmount() - 1);
                Quivers.setQuiver(player.getUniqueId().toString(), quiver.getInventory());
                setArrow(event, "normal", null, null, Color.WHITE);
                Arrow arrow = (Arrow) event.getProjectile();
                arrow.setDamage(arrow.getDamage()+2.0);

                BukkitTask task = new BukkitRunnable()
                {
                    Location arrow_loc = event.getProjectile().getLocation();
                    Boolean targetSelected = false;
                    Entity target = null;

                    @Override
                    public void run()
                    {
                        if(!targetSelected)
                        {
                            for(Entity potential: arrow.getNearbyEntities(4,4, 4))
                            {
                                if(potential != event.getEntity() && potential instanceof LivingEntity)
                                {
                                    target = potential;
                                    targetSelected = true;
                                    break;
                                }
                            }
                        }
                        if(target != null && !target.isDead())
                        {
                            arrow.setVelocity(target.getLocation().toVector().subtract(arrow.getLocation().toVector()).normalize());
                            arrow.setVelocity(arrow.getVelocity());
                            arrow.setVelocity(arrow.getVelocity().multiply(multiply));
                        }
                        if(arrow.getLocation().equals(arrow_loc))
                        {
                            cancel();
                        }
                        else
                        {
                            arrow_loc = arrow.getLocation();
                        }
                    }
                }.runTaskTimer(plugin, 3, 1);
                return true;
            }
        }
        return false;
    }

}
