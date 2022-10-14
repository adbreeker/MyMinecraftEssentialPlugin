package myminecraftessential.myminecraftessential.commands;

import com.destroystokyo.paper.block.TargetBlockInfo;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.*;

public class Sort implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage("Only players can use this command!");
            return true;
        }
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("Sort"))
        {
            Block targeted = player.getTargetBlock(5);
            if(targeted.getState() instanceof Lidded && targeted.getType() != Material.ENDER_CHEST)
            {
                sortItems(null, (Container) targeted.getState());
            }
            else
            {
                sortItems(player, null);
            }
        }

        return true;
    }

    public void sortItems(Player player, Container chest)
    {
        if(player == null && chest == null)
        {
            return;
        }
        if(player != null)
        {
            PlayerInventory inventory = player.getInventory();
            System.out.println("Sortuje czlowieka");
            List<String> materials = new ArrayList<>();
            List<ItemStack> items = new ArrayList<>();
            for(int i=9; i<=35; i++)
            {
                if(inventory.getItem(i) != null)
                {
                    materials.add(inventory.getItem(i).getType().toString());
                    items.add(inventory.getItem(i));
                    inventory.setItem(i, null);
                }
            }
            Collections.sort(materials);
            while(!materials.isEmpty())
            {
                for(ItemStack item : items)
                {
                    if(item != null)
                    {
                        if(item.getType() == Material.getMaterial(materials.get(0)))
                        {
                            inventory.addItem(item);
                            materials.remove(0);
                            items.remove(item);
                            break;
                        }
                    }
                }
            }

        }
        else
        {
            Inventory inventory = chest.getInventory();
            System.out.println("Sortuje skrzynie");
            List<String> materials = new ArrayList<>();
            List<ItemStack> items = new ArrayList<>();
            for(int i=0; i<inventory.getSize(); i++)
            {
                if(inventory.getItem(i) != null)
                {
                    materials.add(inventory.getItem(i).getType().toString());
                    items.add(inventory.getItem(i));
                    inventory.setItem(i, null);
                }
            }
            Collections.sort(materials);
            while(!materials.isEmpty())
            {
                for(ItemStack item : items)
                {
                    if(item != null)
                    {
                        if(item.getType() == Material.getMaterial(materials.get(0)))
                        {
                            inventory.addItem(item);
                            materials.remove(0);
                            items.remove(item);
                            break;
                        }
                    }
                }
            }
        }
    }
}
