package com.gmail.ibmesp1;

import com.gmail.ibmesp1.events.Events;
import com.gmail.ibmesp1.utils.AFKChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;

public final class AFKKicker extends JavaPlugin {

    public String version;
    public String name;
    public HashMap<UUID,Long> lastInput;
    public BukkitTask afkChecker;

    @Override
    public void onEnable() {
        PluginDescriptionFile pdffile = getDescription();
        version = pdffile.getVersion();
        name = ChatColor.DARK_RED + "[" + pdffile.getName() + "]";

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        lastInput = new HashMap<>();
        afkChecker = new AFKChecker(this,lastInput).runTaskTimer(this,0,20L);
        //new Metrics(this,);

        Bukkit.getConsoleSender().sendMessage("[AFK] - Version: " + version + " Enabled - By Ib");
        registerEvents();
    }

    @Override
    public void onDisable() {

    }

    public void registerEvents()
    {
        Bukkit.getPluginManager().registerEvents(new Events(this,lastInput),this);
    }
}
