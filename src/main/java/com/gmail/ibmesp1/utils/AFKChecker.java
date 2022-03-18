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
            long test = afkTime - interval;


            if(plugin.getConfig().getLong("secondsInterval") == 1) {
                if (test > 4000 && test < 5001) {
                    Bukkit.getScheduler().runTaskLater(plugin, () -> p.sendMessage(ChatColor.YELLOW + "You will be kicked on" + ChatColor.GOLD + " 5 " + ChatColor.YELLOW + "seconds"), 0);
                    return;
                }

                if (test > 3000 && test < 4001) {
                    Bukkit.getScheduler().runTaskLater(plugin, () -> p.sendMessage(ChatColor.YELLOW + "You will be kicked on" + ChatColor.GOLD + " 4 " + ChatColor.YELLOW + "seconds"), 0);
                    return;
                }

                if (test > 2000 && test < 3001) {
                    Bukkit.getScheduler().runTaskLater(plugin, () -> p.sendMessage(ChatColor.YELLOW + "You will be kicked on" + ChatColor.GOLD + " 3 " + ChatColor.YELLOW + "seconds"), 0);
                    return;
                }

                if (test > 1000 && test < 2001) {
                    Bukkit.getScheduler().runTaskLater(plugin, () -> p.sendMessage(ChatColor.YELLOW + "You will be kicked on" + ChatColor.GOLD + " 2 " + ChatColor.YELLOW + "seconds"), 0);
                    return;
                }

                if (test > 0 && test < 1001) {
                    Bukkit.getScheduler().runTaskLater(plugin, () -> p.sendMessage(ChatColor.YELLOW + "You will be kicked on" + ChatColor.GOLD + " 1 " + ChatColor.YELLOW + "seconds"), 0);
                    return;
                }
            }

            if(interval > afkTime){
                if(plugin.getConfig().getLong("secondsInterval") > 1) {
                    lastInput.remove(p.getUniqueId());

                    Bukkit.getScheduler().runTaskLater(plugin, ()-> p.sendMessage(ChatColor.YELLOW + "You will be kicked on" + ChatColor.GOLD + " 5 " + ChatColor.YELLOW + "seconds"),0);

                    Bukkit.getScheduler().runTaskLater(plugin, ()-> p.sendMessage(ChatColor.YELLOW + "You will be kicked on" + ChatColor.GOLD + " 4 " + ChatColor.YELLOW + "seconds"),20);

                    Bukkit.getScheduler().runTaskLater(plugin, ()-> p.sendMessage(ChatColor.YELLOW + "You will be kicked on" + ChatColor.GOLD + " 3 " + ChatColor.YELLOW + "seconds"),2*20);

                    Bukkit.getScheduler().runTaskLater(plugin, ()-> p.sendMessage(ChatColor.YELLOW + "You will be kicked on" + ChatColor.GOLD + " 2 " + ChatColor.YELLOW + "seconds"),3*20);

                    Bukkit.getScheduler().runTaskLater(plugin, ()-> p.sendMessage(ChatColor.YELLOW + "You will be kicked on" + ChatColor.GOLD + " 1 " + ChatColor.YELLOW + "seconds"),4*20);

                    Bukkit.getScheduler().runTaskLater(plugin, ()-> p.kickPlayer("You have been kicked for being AFK"),5*20);
                    return;
                }
                lastInput.remove(p.getUniqueId());

                Bukkit.getScheduler().runTaskLater(plugin, ()-> p.kickPlayer("You have been kicked for being AFK"),0);
            }
        }
    }
}
