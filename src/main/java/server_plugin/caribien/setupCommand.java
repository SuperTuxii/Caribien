package server_plugin.caribien;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Objects;

public class setupCommand implements CommandExecutor {

    public final Caribien main;

    public setupCommand(Caribien caribien) {
        this.main = caribien;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Scoreboard mainScoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (Bukkit.getOperators().contains(Bukkit.getOfflinePlayer(p.getUniqueId()))) {
                if (args.length == 0) {
                    MainHelp(p);
                } else if (args.length == 1 && args[0].equalsIgnoreCase("scoreboard")) {
                    ScoreboardHelp(p);
                } else if (args[0].equalsIgnoreCase("scoreboard") && args.length > 1) {
                    if (args[1].equalsIgnoreCase("visibility") && args.length > 2) {
                        if (args[2].equalsIgnoreCase("always")) {
                            Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("Visibility").setScore(1);
                        } else if (args[2].equalsIgnoreCase("never")) {
                            Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("Visibility").setScore(0);
                        } else {
                            ScoreboardHelp(p);
                        }
                    } else if (args[1].equalsIgnoreCase("serverip") && args.length > 2) {
                        if (args[2].equalsIgnoreCase("show")) {
                            Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("ServerIP").setScore(1);
                        } else if (args[2].equalsIgnoreCase("hide")) {
                            Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("ServerIP").setScore(0);
                        } else {
                            ScoreboardHelp(p);
                        }
                    } else if (args[1].equalsIgnoreCase("rank") && args.length > 2) {
                        if (args[2].equalsIgnoreCase("show")) {
                            Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("Rank").setScore(1);
                        } else if (args[2].equalsIgnoreCase("hide")) {
                            Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("Rank").setScore(0);
                        } else {
                            ScoreboardHelp(p);
                        }
                    } else if (args[1].equalsIgnoreCase("spielzeit") && args.length > 2) {
                        if (args[2].equalsIgnoreCase("show")) {
                            Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("Spielzeit").setScore(1);
                        } else if (args[2].equalsIgnoreCase("hide")) {
                            Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("Spielzeit").setScore(0);
                        } else {
                            ScoreboardHelp(p);
                        }
                    } else if (args[1].equalsIgnoreCase("coins") && args.length > 2) {
                        if (args[2].equalsIgnoreCase("show")) {
                            Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("Coins").setScore(1);
                        } else if (args[2].equalsIgnoreCase("hide")) {
                            Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("Coins").setScore(0);
                        } else {
                            ScoreboardHelp(p);
                        }
                    } else {
                        ScoreboardHelp(p);
                    }
                }else if (args[0].equalsIgnoreCase("jump'nrun") && args.length > 1) {
                    if (args[1].equalsIgnoreCase("reset") && args.length == 3) {
                        Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(args[2]).setScore(0);
                    }

                }
            }else {
                p.sendMessage(format("§b§lCaribien " + "§a§l| " + ChatColor.RED + "Dazu hast du keine Rechte "));
            }
            return true;
        }
        return true;
    }

    public void MainHelp(Player p) {
        p.sendMessage(format("&e---------&f Help: Setup &e-------------------\n&7Below is a list of all Setup commands:"));
        p.sendMessage(format("&e/setup scoreboard: &fsetup commands for scoreboard"));
    }

    public void ScoreboardHelp(Player p) {
        p.sendMessage(format("&e---------&f Help: Setup &e-------------------\n&7Below is a list of all Setup commands:"));
        p.sendMessage(format("&e/setup scoreboard visibility always/never: &fVisibility for scoreboard"));
        p.sendMessage(format("&e/setup scoreboard serverip show/hide: &fVisibility for ServerIP"));
        p.sendMessage(format("&e/setup scoreboard rank show/hide: &fVisibility for Rank"));
        p.sendMessage(format("&e/setup scoreboard spielzeit show/hide: &fVisibility for Playtime"));
        p.sendMessage(format("&e/setup scoreboard coins show/hide: &fVisibility for Coins"));
    }

    public String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
