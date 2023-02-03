package server_plugin.caribien;


import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("lobby.build")) {
                if(args.length == 0) {
                    if(Caribien.build.contains(p)) {
                        Caribien.build.remove(p);
                        p.getInventory().clear();
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage(Caribien.prefix + "§7Der §aBaumodus §7wurde für dich §cdeaktiviert§7.");
                    } else {
                        Caribien.build.add(p);
                        p.getInventory().clear();
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage(Caribien.prefix + "§7Der §aBaumodus §7wurde für dich §aaktiviert§7.");
                    }
                } else if(args.length == 1) {
                    Player t = Bukkit.getPlayer(args[0]);
                    if(t != null) {
                        if(Caribien.build.contains(t)) {
                            Caribien.build.remove(t);
                            t.getInventory().clear();

                            t.setGameMode(GameMode.SURVIVAL);
                            t.sendMessage(Caribien.prefix + "§7Der Spieler §6" + p.getName() + "§7 hat dir den §aBaumodus §cdeaktiviert§7.");
                            p.sendMessage(Caribien.prefix + "§7Du hast dem Spieler §6" + t.getName() + "§c verboten §7zu §abauen§7.");
                        } else {
                            Caribien.build.add(t);
                            t.getInventory().clear();
                            t.setGameMode(GameMode.CREATIVE);
                            t.sendMessage(Caribien.prefix + "§7Der Spieler §6" + p.getName() + "§7 hat dir den §aBaumodus aktiviert§7.");
                            p.sendMessage(Caribien.prefix + "§7Du hast dem Spieler §6" + t.getName() + "§a erlaubt §7zu §abauen§7.");
                        }
                    } else
                        p.sendMessage(Caribien.notfound);
                } else
                    p.sendMessage(Caribien.prefix + "§7Benutze §8-> §6/build §8|| §6/build <Spieler>");
            } else
                p.sendMessage(Caribien.noperms);
        } else
            sender.sendMessage(Caribien.prefix + "§7Du musst ein Spieler sein.");



        return false;
    }
}