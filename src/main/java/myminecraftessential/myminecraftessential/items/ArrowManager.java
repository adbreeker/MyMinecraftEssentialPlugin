package myminecraftessential.myminecraftessential.items;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArrowManager
{
    public static ItemStack ExplosiveArrow, TeleportArrow, ExtinguishingArrow;

    public static void init_items()
    {
        createExplosiveArrow();
        createTeleportArrow();
        createExtinguishingArrow();
    }

    // electric Lamp ------------------------------------------------------------------------------------------------------------------------- electric lamp
    private static void createExplosiveArrow()
    {
        ItemStack item = new ItemStack(Material.TIPPED_ARROW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§4Explosive Arrow");
        meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        List <String> lore = new ArrayList<>();
        lore.add("Causes explosion on impact");
        meta.setLore(lore);
        item.setItemMeta(meta);
        PotionMeta potionMeta = (PotionMeta) item.getItemMeta();
        potionMeta.setColor(Color.RED);
        item.setItemMeta(potionMeta);
        ExplosiveArrow = item;

        ShapedRecipe new_explosive_arrow = new ShapedRecipe(NamespacedKey.minecraft("explosivve_arrow"),ExplosiveArrow);
        new_explosive_arrow.shape(" T ", " A ", "   ");
        new_explosive_arrow.setIngredient('T',Material.TNT);
        new_explosive_arrow.setIngredient('A',Material.ARROW);
        Bukkit.getServer().addRecipe(new_explosive_arrow);
    }

    private static void createTeleportArrow()
    {
        ItemStack item = new ItemStack(Material.TIPPED_ARROW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§5Teleport Arrow");
        meta.addEnchant(Enchantment.SOUL_SPEED, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        List <String> lore = new ArrayList<>();
        lore.add("Teleporting user to arrow impact point");
        meta.setLore(lore);
        item.setItemMeta(meta);
        PotionMeta potionMeta = (PotionMeta) item.getItemMeta();
        potionMeta.setColor(Color.PURPLE);
        item.setItemMeta(potionMeta);
        TeleportArrow = item;

        ShapedRecipe new_teleport_arrow = new ShapedRecipe(NamespacedKey.minecraft("teleport_arrow"),TeleportArrow);
        new_teleport_arrow.shape(" E ", " A ", "   ");
        new_teleport_arrow.setIngredient('E',Material.ENDER_PEARL);
        new_teleport_arrow.setIngredient('A',Material.ARROW);
        Bukkit.getServer().addRecipe(new_teleport_arrow);
    }

    private static void createExtinguishingArrow()
    {
        ItemStack item = new ItemStack(Material.TIPPED_ARROW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§3Extinguishing Arrow");
        meta.addEnchant(Enchantment.WATER_WORKER, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        List <String> lore = new ArrayList<>();
        lore.add("Extinguishing near fire and lava on impact");
        meta.setLore(lore);
        item.setItemMeta(meta);
        PotionMeta potionMeta = (PotionMeta) item.getItemMeta();
        potionMeta.setColor(Color.BLUE);
        item.setItemMeta(potionMeta);
        ExtinguishingArrow = item;

        ShapedRecipe new_extinguishing_arrow = new ShapedRecipe(NamespacedKey.minecraft("extinguishing_arrow"),ExtinguishingArrow);
        new_extinguishing_arrow.shape(" W ", " A ", "   ");
        new_extinguishing_arrow.setIngredient('W',Material.WATER_BUCKET);
        new_extinguishing_arrow.setIngredient('A',Material.ARROW);
        Bukkit.getServer().addRecipe(new_extinguishing_arrow);
    }

}
