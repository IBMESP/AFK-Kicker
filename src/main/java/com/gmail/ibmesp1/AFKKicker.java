package com.gmail.ibmesp1;

import com.gmail.ibmesp1.events.Events;
import com.gmail.ibmesp1.utils.AFKChecker;
import com.gmail.ibmesp1.utils.Metrics;
import com.gmail.ibmesp1.utils.UpdateCAFK;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

public final class AFKKicker extends JavaPlugin {

    public String version;
    public String name;
    public HashMap<UUID,Long> lastInput;
    public BukkitTask afkChecker;
    public long afkCheck;

    @Override
    public void onEnable() {
        PluginDescriptionFile pdffile = getDescription();
        version = pdffile.getVersion();
        name = ChatColor.DARK_RED + "[" + pdffile.getName() + "]";
        Logger log = Bukkit.getLogger();
        afkCheck = getConfig().getLong("secondsInterval");

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        lastInput = new HashMap<>();
        afkChecker = new AFKChecker(this,lastInput).runTaskTimer(this,0,afkCheck*20L);
        new Metrics(this,14551);


        Bukkit.getConsoleSender().sendMessage("[AFK-Kicker] - Version: " + version + " Enabled - By Ib");
        registerEvents();

        new UpdateCAFK(this,100525).getLatestVersion(version -> {
            if(this.getDescription().getVersion().equalsIgnoreCase(version)) {
                log.info("[AFK-Kicker] AFK-Kicker is up to date");
            } else {
                log.warning("[AFK-Kicker] AFK-Kicker has a new update");
            }
        });
    }

    @Override
    public void onDisable() {

    }

    public void registerEvents()
    {
        Bukkit.getPluginManager().registerEvents(new Events(this,lastInput),this);
    }
}
