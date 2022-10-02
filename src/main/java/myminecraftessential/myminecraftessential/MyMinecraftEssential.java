package myminecraftessential.myminecraftessential;

import myminecraftessential.myminecraftessential.files.Quivers;
import myminecraftessential.myminecraftessential.handlers.*;
import myminecraftessential.myminecraftessential.items.ArrowManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MyMinecraftEssential extends JavaPlugin {

    @Override
    public void onEnable()
    {
        Quivers.setup();
        Quivers.get().options().copyDefaults(true);
        Quivers.save();


        getServer().getPluginManager().registerEvents(new ArrowShoot(this),this);
        getServer().getPluginManager().registerEvents(new OpenQuiver(this),this);
        getServer().getPluginManager().registerEvents(new QuiverClose(this),this);
        getServer().getPluginManager().registerEvents(new QuiverSelectArrow(this),this);
        getServer().getPluginManager().registerEvents(new UsingBowWithoutArrows(this),this);
        getServer().getPluginManager().registerEvents(new InsertingToQuiver(this),this);

        ArrowManager.init_items();

    }

}
