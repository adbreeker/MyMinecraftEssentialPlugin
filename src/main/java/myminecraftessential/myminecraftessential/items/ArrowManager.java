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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArrowManager
{
    public static ItemStack ExplosiveArrow, TeleportArrow, ExtinguishingArrow, PrisonArrow, FrostArrow, HomingArrow;

    public static void init_items()
    {
        createExplosiveArrow();
        createTeleportArrow();
        createExtinguishingArrow();
        createPrisonArrow();
        createFrostArrow();
        createHomingArrow();
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
        lore.add("§4Causes explosion on impact");
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
        lore.add("§5Teleporting user to arrow impact point");
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
        lore.add("§3Extinguishing near fire and lava on impact");
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

    private static void createPrisonArrow()
    {
        ItemStack item = new ItemStack(Material.TIPPED_ARROW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§8Prison Arrow");
        meta.addEnchant(Enchantment.IMPALING, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        List <String> lore = new ArrayList<>();
        lore.add("§8Creating prison on impact");
        meta.setLore(lore);
        item.setItemMeta(meta);
        PotionMeta potionMeta = (PotionMeta) item.getItemMeta();
        potionMeta.setColor(Color.GRAY);
        item.setItemMeta(potionMeta);
        PrisonArrow = item;

        ShapedRecipe new_prison_arrow = new ShapedRecipe(NamespacedKey.minecraft("prison_arrow"),PrisonArrow);
        new_prison_arrow.shape(" O ", "BAB", " O ");
        new_prison_arrow.setIngredient('O',Material.OBSIDIAN);
        new_prison_arrow.setIngredient('A',Material.ARROW);
        new_prison_arrow.setIngredient('B',Material.IRON_BARS);
        Bukkit.getServer().addRecipe(new_prison_arrow);
    }

    private static void createFrostArrow()
    {
        ItemStack item = new ItemStack(Material.TIPPED_ARROW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§bFrost Arrow");
        meta.addEnchant(Enchantment.FROST_WALKER, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        List <String> lore = new ArrayList<>();
        lore.add("§bFreezing water and entities on impact");
        meta.setLore(lore);
        item.setItemMeta(meta);
        PotionMeta potionMeta = (PotionMeta) item.getItemMeta();
        potionMeta.setColor(Color.AQUA);
        item.setItemMeta(potionMeta);
        FrostArrow = item;

        ShapedRecipe new_frost_arrow = new ShapedRecipe(NamespacedKey.minecraft("frost_arrow"),FrostArrow);
        new_frost_arrow.shape(" I ", " A ", "   ");
        new_frost_arrow.setIngredient('I',Material.ICE);
        new_frost_arrow.setIngredient('A',Material.ARROW);
        Bukkit.getServer().addRecipe(new_frost_arrow);
    }

    private static void createHomingArrow()
    {
        ItemStack item = new ItemStack(Material.TIPPED_ARROW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§fHoming Arrow");
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        List <String> lore = new ArrayList<>();
        lore.add("§fFollowing entities after shoot");
        meta.setLore(lore);
        item.setItemMeta(meta);
        PotionMeta potionMeta = (PotionMeta) item.getItemMeta();
        potionMeta.setColor(Color.WHITE);
        item.setItemMeta(potionMeta);
        HomingArrow = item;

        ShapedRecipe new_homing_arrow = new ShapedRecipe(NamespacedKey.minecraft("homing_arrow"),HomingArrow);
        new_homing_arrow.shape(" C ", " A ", "   ");
        new_homing_arrow.setIngredient('C',Material.COMPASS);
        new_homing_arrow.setIngredient('A',Material.ARROW);
        Bukkit.getServer().addRecipe(new_homing_arrow);
    }

}
