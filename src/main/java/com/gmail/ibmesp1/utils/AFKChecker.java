package com.gmail.ibmesp1.utils;

import com.gmail.ibmesp1.AFKKicker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class AFKChecker extends BukkitRunnable {

    private final AFKKicker plugin;
    private HashMap<UUID,Long> lastInput;
    private long afkTime;

    public AFKChecker(AFKKicker plugin, HashMap<UUID, Long> lastInput) {
        this.plugin = plugin;
        this.lastInput = lastInput;

        afkTime = plugin.getConfig().getLong("minutesAFK") * 60000;
    }

    @Override
    public void run() {
        for (Player p: Bukkit.getOnlinePlayers()) {
            if(p.hasPermission("afk.bypass")){return;}

            long lastIn = lastInput.get(p.getUniqueId());
            long time = System.currentTimeMillis();
            long interval = time - lastIn;

            if(interval > afkTime){

                lastInput.remove(p.getUniqueId());

                Bukkit.getScheduler().runTaskLater(plugin, ()-> p.sendMessage(ChatColor.YELLOW + "You will be kicked on" + ChatColor.GOLD + " 5 " + ChatColor.YELLOW + "seconds"),0);

                Bukkit.getScheduler().runTaskLater(plugin, ()-> p.sendMessage(ChatColor.YELLOW + "You will be kicked on" + ChatColor.GOLD + " 4 " + ChatColor.YELLOW + "seconds"),20);

                Bukkit.getScheduler().runTaskLater(plugin, ()-> p.sendMessage(ChatColor.YELLOW + "You will be kicked on" + ChatColor.GOLD + " 3 " + ChatColor.YELLOW + "seconds"),2*20);

                Bukkit.getScheduler().runTaskLater(plugin, ()-> p.sendMessage(ChatColor.YELLOW + "You will be kicked on" + ChatColor.GOLD + " 2 " + ChatColor.YELLOW + "seconds"),3*20);

                Bukkit.getScheduler().runTaskLater(plugin, ()-> p.sendMessage(ChatColor.YELLOW + "You will be kicked on" + ChatColor.GOLD + " 1 " + ChatColor.YELLOW + "seconds"),4*20);

                Bukkit.getScheduler().runTaskLater(plugin, ()-> p.kickPlayer("AFK"),5*20);
            }
        }
    }
}
