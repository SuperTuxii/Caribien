package server_plugin.caribien;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.Objects;

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
        if (!e.getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent e) {
        e.setFoodLevel(20);
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent e) {
        e.setCancelled(true);
    }
    
    @EventHandler
    public void onEntityInteract(EntityInteractEvent event) {
        Block block = event.getBlock();
        if(block.getType() == Material.FARMLAND) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(event.getAction() == Action.PHYSICAL) {
            if(Objects.requireNonNull(event.getClickedBlock()).getType() == Material.FARMLAND) {
                event.setCancelled(true);
            }
        }
    }
}
