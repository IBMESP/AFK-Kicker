package com.gmail.ibmesp1.events;

import com.gmail.ibmesp1.AFK;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

public class Events implements Listener {

    private AFK plugin;
    private HashMap<UUID,Long> lastInput;

    public Events(AFK plugin,HashMap<UUID,Long> lastInput) {
        this.plugin = plugin;
        this.lastInput = lastInput;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        //if(player.hasPermission("afk.bypass")){return;}

        lastInput.put(player.getUniqueId(),System.currentTimeMillis());

        //System.out.println("On Join: " + System.currentTimeMillis());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        long quit = System.currentTimeMillis();
        long join = lastInput.get(player.getUniqueId());

        //if(player.hasPermission("afk.bypass")){return;}
        //System.out.println("Join: " + join);
        //System.out.println("Quit: " + quit);

        //System.out.println("Millis interval: " + (quit - join));

        lastInput.remove(player.getUniqueId());

    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player player = e.getPlayer();
        long eventTime = System.currentTimeMillis();
        long lastIn = lastInput.get(player.getUniqueId());
        long interval = eventTime - lastIn;
        long afkTime = 20000;

        if(interval < afkTime){
            return;
        }

        lastInput.replace(player.getUniqueId(),eventTime);
    }
}
