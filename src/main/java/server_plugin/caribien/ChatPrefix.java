package server_plugin.caribien;


import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;


public class ChatPrefix implements Listener {


    @EventHandler
    public void onChat(PlayerChatEvent e) {
        final String message = e.getMessage().replace("%", "%%");

        Player p = e.getPlayer();
        User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p);
        Group group = LuckPermsProvider.get().getGroupManager().getGroup(user.getPrimaryGroup());
        assert group != null;
        String groupName;
        if (group.getDisplayName() == null) {
            groupName = group.getName();
        } else {
            groupName = group.getDisplayName();
        }

        e.setFormat(groupName + "" + " | " + e.getPlayer().getName() + " >> " + e.getMessage());

        Bukkit.getOnlinePlayers().stream().filter(player -> message.contains(p.getName())).forEach(player -> {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
        });
    }

    @Deprecated
    public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
        sendTitle(player, fadeIn, stay, fadeOut, title, subtitle);
    }

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage("");
        Player p = e.getPlayer();
        sendTitle(p, 30, 100, 10, "§7§lWelcome " + p.getName() + " to", "§5§l§oOP§f§l§oHub");


    }

}
