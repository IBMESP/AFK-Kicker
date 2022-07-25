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
    private double afkTime;

    public AFKChecker(AFKKicker plugin, HashMap<UUID, Long> lastInput) {
        this.plugin = plugin;
        this.lastInput = lastInput;

        afkTime = plugin.getConfig().getDouble("minutesAFK") * 60000;
    }

    @Override
    public void run() {
        for (Player p: Bukkit.getOnlinePlayers()) {
            long time = System.currentTimeMillis();

            if(!lastInput.containsKey(p.getUniqueId())){
                if(p.hasPermission("afk.bypass")){
                    return;
                }
                lastInput.put(p.getUniqueId(),time);
            }

            long lastIn = lastInput.get(p.getUniqueId());
            long interval = time - lastIn;
            double test = afkTime - interval;

            if(plugin.getConfig().getLong("secondsInterval") == 1) {
                if (test > 4000 && test < 5001) {
                    p.sendMessage(kickIn("5"));
                    return;
                }

                if (test > 3000 && test < 4001) {
                    p.sendMessage(kickIn("4"));
                    return;
                }

                if (test > 2000 && test < 3001) {
                    p.sendMessage(kickIn("3"));
                    return;
                }

                if (test > 1000 && test < 2001) {
                    p.sendMessage(kickIn("2"));
                    return;
                }

                if (test > 0 && test < 1001) {
                    p.sendMessage(kickIn("1"));
                    return;
                }
            }

            if(interval > afkTime){
                if(plugin.getConfig().getLong("secondsInterval") > 1) {
                    lastInput.remove(p.getUniqueId());

                    for(int i=5;i<1;i--)
                        Bukkit.getScheduler().runTaskLater(plugin, ()-> p.sendMessage(kickIn(n.toString())),20);

                    return;
                }
                lastInput.remove(p.getUniqueId());

                p.kickPlayer(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("kicked")));
            }
        }
    }

    private String kickIn(String seconds){
        String msg = plugin.getConfig().getString("kickIn");

        String msgR = msg.replace("%seconds",seconds);

        return ChatColor.translateAlternateColorCodes('&',msgR);
    }
}
