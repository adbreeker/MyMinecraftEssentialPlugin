package myminecraftessential.myminecraftessential.inventories;

import myminecraftessential.myminecraftessential.files.Quivers;
import myminecraftessential.myminecraftessential.items.ArrowManager;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PluginItems implements InventoryHolder
{
    private  Inventory inv;

    public PluginItems()
    {
        inv = Bukkit.createInventory(this,18,"MME Items");
        init();
    }

    private void init()
    {
        inv.addItem(ArrowManager.ExplosiveArrow);
        inv.addItem(ArrowManager.TeleportArrow);
        inv.addItem(ArrowManager.ExtinguishingArrow);
        inv.addItem(ArrowManager.PrisonArrow);
        inv.addItem(ArrowManager.FrostArrow);
        inv.addItem(ArrowManager.HomingArrow);
        inv.addItem(ArrowManager.ThunderArrow);
        inv.addItem(ArrowManager.FlameArrow);
        inv.addItem(ArrowManager.TorchArrow);
        inv.addItem(ArrowManager.BurialArrow);
        inv.addItem(ArrowManager.DeathBringerArrow);
    }

    @Override
    public Inventory getInventory() {return inv;}
}
