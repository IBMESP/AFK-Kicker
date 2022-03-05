package com.gmail.ibmesp1;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class AFK extends JavaPlugin {

    public HashMap<UUID,Long> afk;

    @Override
    public void onEnable() {
        afk = new HashMap<>();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
