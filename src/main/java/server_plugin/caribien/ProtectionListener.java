package server_plugin.caribien;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class ProtectionListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if(!Caribien.build.contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if(!Caribien.build.contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(!Caribien.build.contains((Player) e.getWhoClicked())) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onPickup(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player) {
            if (!Caribien.build.contains((Player) e.getEntity())) {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if(!Caribien.build.contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent e) {
        e.setCancelled(true);
    }
}