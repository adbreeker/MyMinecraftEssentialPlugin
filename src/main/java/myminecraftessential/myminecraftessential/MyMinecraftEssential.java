package myminecraftessential.myminecraftessential;

import myminecraftessential.myminecraftessential.commands.MME_Items;
import myminecraftessential.myminecraftessential.files.Quivers;
import myminecraftessential.myminecraftessential.handlers.*;
import myminecraftessential.myminecraftessential.items.ArrowManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MyMinecraftEssential extends JavaPlugin {

    @Override
    public void onEnable()
    {
        // files --------------------------------------------------------------------------------------------------------------
        Quivers.setup();
        Quivers.get().options().copyDefaults(true);
        Quivers.save();

        // files --------------------------------------------------------------------------------------------------------------
        getCommand("MME_Items").setExecutor(new MME_Items());

        // handlers --------------------------------------------------------------------------------------------------------------
        getServer().getPluginManager().registerEvents(new ArrowShoot(this),this);
        getServer().getPluginManager().registerEvents(new QuiverOpen(this),this);
        getServer().getPluginManager().registerEvents(new QuiverClose(this),this);
        getServer().getPluginManager().registerEvents(new QuiverSelectArrow(this),this);
        getServer().getPluginManager().registerEvents(new UsingBowWithoutArrows(this),this);
        getServer().getPluginManager().registerEvents(new MobsHealth(this),this);

        // items -----------------------------------------------------------------------------------------------------------------
        ArrowManager.init_items();

    }

}
