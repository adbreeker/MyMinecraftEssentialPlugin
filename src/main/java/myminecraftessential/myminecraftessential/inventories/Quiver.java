package myminecraftessential.myminecraftessential.inventories;

import myminecraftessential.myminecraftessential.files.Quivers;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Quiver implements InventoryHolder
{
    private  Inventory inv;

    public Quiver(String owner)
    {
        inv = Bukkit.createInventory(this,18,"Quiver");
        init(owner);
    }

    private void init(String owner)
    {
        if(Quivers.get().contains(owner))
        {
            int index=0;
            List<ItemStack> inQuiver = (List<ItemStack>) Quivers.get().getList(owner);
            for(ItemStack slot : inQuiver)
            {
                if(slot != null)
                {
                    inv.setItem(index,slot);
                }
                index++;
            }

        }
    }

    @Override
    public Inventory getInventory() {return inv;}
}
