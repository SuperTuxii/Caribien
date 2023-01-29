package server_plugin.caribien.Lobbysafety;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import server_plugin.caribien.Caribien;

public class AntiDamage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player & e.getEntity().getWorld().getName().equals(Caribien.world)) {
            e.setCancelled(true);
        }

    }
}
