package myminecraftessential.myminecraftessential.commands;

import myminecraftessential.myminecraftessential.inventories.PluginItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MME_Items implements CommandExecutor
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
        if (command.getName().equalsIgnoreCase("MME_Items"))
        {
            PluginItems holder = new PluginItems();
            player.openInventory(holder.getInventory());
        }

        return true;
    }
}
