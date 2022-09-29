package myminecraftessential.myminecraftessential.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Quivers
{
    private static File file;
    private static FileConfiguration customFile;

    public static void setup()
    {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("MyMinecraftEssential").getDataFolder(), "quivers.yml");
        if(!file.exists())
        {
            try
            {
                file.createNewFile();
            }
            catch (IOException ex)
            {

            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get()
    {
        return customFile;
    }

    public static void setQuiver(String owner, Inventory quiver)
    {
        List<ItemStack> items = new ArrayList<>();
        for(int index=0; index<quiver.getSize(); index++)
        {
            ItemStack item = quiver.getItem(index);
            items.add(item);
        }
        customFile.addDefault(owner,items);
        customFile.set(owner,items);

        save();
        reload();
    }

    public static void save()
    {
        try
        {
            customFile.save(file);
        }
        catch(IOException ex)
        {
            System.out.println("inventory save exception");
        }
    }

    public static void reload()
    {
        customFile = YamlConfiguration.loadConfiguration(file);
    }
}
