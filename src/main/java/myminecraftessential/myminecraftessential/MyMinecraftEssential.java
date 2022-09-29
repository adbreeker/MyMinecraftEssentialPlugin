package myminecraftessential.myminecraftessential;

import myminecraftessential.myminecraftessential.files.Quivers;
import myminecraftessential.myminecraftessential.handlers.ArrowShoot;
import myminecraftessential.myminecraftessential.handlers.OpenQuiver;
import myminecraftessential.myminecraftessential.handlers.QuiverClose;
import myminecraftessential.myminecraftessential.handlers.QuiverSelectArrow;
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
        ArrowManager.init_items();

    }

}
