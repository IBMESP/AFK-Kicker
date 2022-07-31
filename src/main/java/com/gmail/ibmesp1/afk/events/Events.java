package com.gmail.ibmesp1.afk.events;

import com.gmail.ibmesp1.afk.AFKKicker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

public class Events implements Listener {

    private final AFKKicker plugin;
    private HashMap<UUID,Long> lastInput;
    private long afkCheck;

    public Events(AFKKicker plugin, HashMap<UUID,Long> lastInput) {
        this.plugin = plugin;
        this.lastInput = lastInput;

        afkCheck = plugin.getConfig().getLong("secondsInterval")*1000;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        if(player.hasPermission("afk.bypass")){return;}

        lastInput.put(player.getUniqueId(),System.currentTimeMillis());

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();

        if(player.hasPermission("afk.bypass")){
            if(!lastInput.containsKey(player.getUniqueId())){
                return;
            }
            lastInput.remove(player.getUniqueId());
            return;
        }

        lastInput.remove(player.getUniqueId());

    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player player = e.getPlayer();

        if(player.hasPermission("afk.bypass")){
            if(!lastInput.containsKey(player.getUniqueId())){
                return;
            }
            lastInput.remove(player.getUniqueId());
            return;
        }

        updater(player);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();

        if(player.hasPermission("afk.bypass")){
            if(!lastInput.containsKey(player.getUniqueId())){
                return;
            }
            lastInput.remove(player.getUniqueId());
            return;
        }

        updater(player);
    }

    private void updater(Player player){
        long eventTime = System.currentTimeMillis();

        if(!lastInput.containsKey(player.getUniqueId())){
            lastInput.put(player.getUniqueId(),eventTime);
            return;
        }

        long lastIn = lastInput.get(player.getUniqueId());
        long interval = eventTime - lastIn;

        if(interval < afkCheck){
            return;
        }

        lastInput.replace(player.getUniqueId(),eventTime);
    }
}
