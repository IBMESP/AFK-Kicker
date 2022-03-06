package com.gmail.ibmesp1.utils;

import com.gmail.ibmesp1.AFK;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class AFKChecker extends BukkitRunnable {

    private AFK plugin;
    private HashMap<UUID,Long> lastInput;

    public AFKChecker(AFK plugin, HashMap<UUID, Long> lastInput) {
        this.plugin = plugin;
        this.lastInput = lastInput;
    }

    @Override
    public void run() {
        for (Player p: Bukkit.getOnlinePlayers()) {
            if(p.hasPermission("bp.bypass")){return;}

            long lastIn = lastInput.get(p.getUniqueId());
            long time = System.currentTimeMillis();
            long afkTime = 20000;

            if(time - lastIn > afkTime){
                lastInput.remove(p.getUniqueId());
                p.kickPlayer("AFK");
            }
        }
    }
}
