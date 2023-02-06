package server_plugin.caribien;


import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;


public class ChatPrefix implements Listener {



    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        final String message = e.getMessage().replace("%", "%%");

        Player p = e.getPlayer();
        User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p);
        Group group = LuckPermsProvider.get().getGroupManager().getGroup(user.getPrimaryGroup());
        assert group != null;
        String prefix = user.getCachedData().getMetaData().getPrefix();
        String groupName;
        if (group.getDisplayName() == null) {
            groupName = group.getName();
        } else {
            groupName = group.getDisplayName();
        }

        e.setFormat(format(prefix)+groupName + "" + " §8| " + ChatColor.GRAY + e.getPlayer().getName() + " §8>> " + ChatColor.GRAY + e.getMessage());

        Bukkit.getOnlinePlayers().stream().filter(player -> message.contains(p.getName())).forEach(player -> p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1));
    }

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p);
        Group group = LuckPermsProvider.get().getGroupManager().getGroup(user.getPrimaryGroup());
        assert group != null;
        String prefix = user.getCachedData().getMetaData().getPrefix();
        String groupName;
        if (group.getDisplayName() == null) {
            groupName = group.getName();
        } else {
            groupName = group.getDisplayName();
        }

        e.setJoinMessage(format(prefix) + groupName + "" + " §8| " + ChatColor.GRAY + e.getPlayer().getName() + " §bist auf der Insel gestrandet!"));
    }
    
    @EventHandler
    public void PlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p);
        Group group = LuckPermsProvider.get().getGroupManager().getGroup(user.getPrimaryGroup());
        assert group != null;
        String prefix = user.getCachedData().getMetaData().getPrefix();
        String groupName;
        if (group.getDisplayName() == null) {
            groupName = group.getName();
        } else {
            groupName = group.getDisplayName();
        }

        e.setQuitMessage(format(prefix) + groupName + "" + " §8| " + ChatColor.GRAY + e.getPlayer().getName() + " §bist dem Fluch der Insel entkommen!"));
    }
    
    @EventHandler
    public void PlayerKick(PlayerKickEvent e) {
        Player p = e.getPlayer();
        User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p);
        Group group = LuckPermsProvider.get().getGroupManager().getGroup(user.getPrimaryGroup());
        assert group != null;
        String prefix = user.getCachedData().getMetaData().getPrefix();
        String groupName;
        if (group.getDisplayName() == null) {
            groupName = group.getName();
        } else {
            groupName = group.getDisplayName();
        }

        e.setLeaveMessage(format(prefix) + groupName + "" + " §8| " + ChatColor.GRAY + e.getPlayer().getName() + " §bist über die Planke gegangen!"));
    }

    public String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
