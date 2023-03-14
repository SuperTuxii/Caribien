package server_plugin.caribien;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Caribien extends JavaPlugin implements Listener {

    public setupCommand setupCommand = new setupCommand(this);

    public Jump_N_Run jumpNRun = new Jump_N_Run(this);

    public ChatPrefix chatPrefix = new ChatPrefix();

    public BuildCMD buildCMD = new BuildCMD();

    public Scoreboard mainScoreboard;

    public static String world = "world";
    public static List<Player> build = new ArrayList<>();
    public static String prefix = "§b§lCaribien " + "§a§l| ";
    public static String noperms = prefix + "§cDazu hast du keine Rechte.";
    public static String notfound = prefix + "§cDieser Spieler wurde nicht gefunden.";
    
    public int[] LobbySpawn = {877, 94, -47};

    Jedis jedis;

    @Override
    public void onEnable() {
        // Plugin startup logic
        registerEvents();
        registerCommands();
        createScoreboards();

        Objects.requireNonNull(Bukkit.getWorld(world)).setPVP(false);

        getJedis();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(this, this);
        pm.registerEvents(jumpNRun, this);
        pm.registerEvents(chatPrefix, this);
        pm.registerEvents(new ProtectionListener(), this);

    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("setup")).setExecutor(setupCommand);
        Objects.requireNonNull(getCommand("build")).setExecutor(buildCMD);
    }

    private void createScoreboards() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        assert manager != null;
        mainScoreboard = manager.getMainScoreboard();
        jumpNRun.mainScoreboard = manager.getMainScoreboard();
        if (mainScoreboard.getObjective("Spielzeit'") == null) {
            mainScoreboard.registerNewObjective("Spielzeit'", "DUMMY", format("&a&lSpielzeit"));
        }
        if (mainScoreboard.getObjective("Coins") == null) {
            mainScoreboard.registerNewObjective("Coins", "DUMMY", format("&6&lCoins"));
        }
        if (mainScoreboard.getObjective("Scoreboard") == null) {
            mainScoreboard.registerNewObjective("Scoreboard", "DUMMY", format("&d&lConfig Scoreboard"));
        }
        if (!Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("Visibility").isScoreSet()) {
            Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("Visibility").setScore(1);
        }
        if (!Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("ServerIP").isScoreSet()) {
            Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("ServerIP").setScore(1);
        }
        if (!Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("Rank").isScoreSet()) {
            Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("Rank").setScore(1);
        }
        if (!Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("Spielzeit'").isScoreSet()) {
            Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("Spielzeit'").setScore(1);
        }
        if (!Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("Coins").isScoreSet()) {
            Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("Coins").setScore(1);
        }
        if (!Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("Lock").isScoreSet()) {
            Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("Lock").setScore(0);
        }
        if (mainScoreboard.getObjective("Jump'n Run Zeit") == null) {
            mainScoreboard.registerNewObjective("Jump'n Run Zeit", "DUMMY", format("&a&lJump'n Run Zeit"));
        }
        if (mainScoreboard.getObjective("Jump'n Run bZeit") == null) {
            mainScoreboard.registerNewObjective("Jump'n Run bZeit", "DUMMY", format("&6&lJump'n Run Bestzeit"));
        }

    }

    private void getJedis() {
        jedis = new Jedis("127.0.0.1", 6379, 5000);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        int coins;
        int spielzeit;
        if (jedis.get(p.getUniqueId() + "coins") == null && jedis.get(p.getUniqueId() + "spielzeit") == null) {
            if (!Objects.requireNonNull(mainScoreboard.getObjective("Coins")).getScore(p.getName()).isScoreSet()) {
                coins = 0;
                Objects.requireNonNull(mainScoreboard.getObjective("Coins")).getScore(p.getName()).setScore(0);
            }else {
                coins = Objects.requireNonNull(mainScoreboard.getObjective("Coins")).getScore(p.getName()).getScore();
            }
            if (!Objects.requireNonNull(mainScoreboard.getObjective("Spielzeit'")).getScore(p.getName()).isScoreSet()) {
                spielzeit = 0;
                Objects.requireNonNull(mainScoreboard.getObjective("Spielzeit'")).getScore(p.getName()).setScore(0);
            }else {
                spielzeit = Objects.requireNonNull(mainScoreboard.getObjective("Spielzeit'")).getScore(p.getName()).getScore();
            }
            jedis.set(p.getUniqueId() + "coins", String.valueOf(coins));
            jedis.set(p.getUniqueId() + "spielzeit", String.valueOf(spielzeit));
        }else {
            coins = Integer.parseInt(jedis.get(p.getUniqueId() + "coins"));
            spielzeit = Integer.parseInt(jedis.get(p.getUniqueId() + "spielzeit"));
        }

        Objects.requireNonNull(mainScoreboard.getObjective("Coins")).getScore(p.getName()).setScore(coins);
        Objects.requireNonNull(mainScoreboard.getObjective("Spielzeit'")).getScore(p.getName()).setScore(spielzeit);

        p.removeScoreboardTag("Jump'n Run");

        User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p);
        Group group = LuckPermsProvider.get().getGroupManager().getGroup(user.getPrimaryGroup());
        assert group != null;
        String groupName;
        if (group.getDisplayName() == null) {
            groupName = group.getName();
        } else {
            groupName = group.getDisplayName();
        }

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        assert manager != null;
        if (Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("Visibility").getScore() >= 1) {
            Scoreboard scoreboard = manager.getNewScoreboard();
            Objective objective = scoreboard.getObjective(p.getName());
            if (objective != null) {
                objective.unregister();
            }
            objective = scoreboard.registerNewObjective(p.getName(), "DUMMY", format("&b&lCaribien.de"));
            //Scoreboard Anzeige
            setScoreboard(p, objective, groupName, toSpielzeit(Objects.requireNonNull(mainScoreboard.getObjective("Spielzeit'")).getScore(p.getName()).getScore()));
            p.setScoreboard(scoreboard);
            Spielzeit_Counter(p);
        }
        p.teleport(new Location(p.getWorld(), LobbySpawn[0] + 0.5, LobbySpawn[1] + 0.5, LobbySpawn[2] + 0.5, 218, -10));
        p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 0, false, false, false));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        event.getPlayer().setScoreboard(mainScoreboard);
    }

    public void Spielzeit_Counter(Player p) {
        if (!Objects.requireNonNull(mainScoreboard.getObjective("Spielzeit'")).getScore(p.getName()).isScoreSet()) {
            Objects.requireNonNull(mainScoreboard.getObjective("Spielzeit'")).getScore(p.getName()).setScore(0);
        }
        new BukkitRunnable() {

            int oldSpielzeit = Objects.requireNonNull(mainScoreboard.getObjective("Spielzeit'")).getScore(p.getName()).getScore();
            int oldCoins = Objects.requireNonNull(mainScoreboard.getObjective("Coins")).getScore(p.getName()).getScore();
            boolean Jump_N_Run = false;

            @Override
            public void run() {
                if (!p.isOnline()) {
                    cancel();
                }
                Objects.requireNonNull(mainScoreboard.getObjective("Spielzeit'")).getScore(p.getName()).setScore(Objects.requireNonNull(mainScoreboard.getObjective("Spielzeit'")).getScore(p.getName()).getScore() + 1);
                jedis.set(p.getUniqueId() + "spielzeit", String.valueOf(Objects.requireNonNull(mainScoreboard.getObjective("Spielzeit'")).getScore(p.getName()).getScore()));
                if (Objects.requireNonNull(mainScoreboard.getObjective("Coins")).getScore(p.getName()).getScore() != Integer.parseInt(jedis.get(p.getUniqueId() + "coins"))) {
                    jedis.set(p.getUniqueId() + "coins", String.valueOf(Objects.requireNonNull(mainScoreboard.getObjective("Coins")).getScore(p.getName()).getScore()));
                }

                if (!p.getScoreboardTags().contains("Jump'n Run")) {
                    User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p);
                    Group group = LuckPermsProvider.get().getGroupManager().getGroup(user.getPrimaryGroup());
                    assert group != null;
                    String groupName;
                    if (group.getDisplayName() == null) {
                        groupName = group.getName();
                    } else {
                        groupName = group.getDisplayName();
                    }

                    ScoreboardManager manager = Bukkit.getScoreboardManager();
                    assert manager != null;
                    Scoreboard scoreboard = p.getScoreboard();
                    Objective objective = scoreboard.getObjective(p.getName());
                    if (Jump_N_Run) {
                        if (objective != null) {
                            objective.unregister();
                        }
                        objective = scoreboard.registerNewObjective(p.getName(), "DUMMY", format("&b&lCaribien.de"));
                        //Scoreboard Anzeige
                        setScoreboard(p, objective, groupName, toSpielzeit(Objects.requireNonNull(mainScoreboard.getObjective("Spielzeit'")).getScore(p.getName()).getScore()));
                        Jump_N_Run = false;
                    }else {
                        assert objective != null;
                        if (p.isOnline()) {
                            updateScoreboard(objective, toSpielzeit(Objects.requireNonNull(mainScoreboard.getObjective("Spielzeit'")).getScore(p.getName()).getScore()), toSpielzeit(oldSpielzeit), Objects.requireNonNull(mainScoreboard.getObjective("Coins")).getScore(p.getName()).getScore(), oldCoins);
                        }
                    }
                }
                if (p.getScoreboardTags().contains("Jump'n Run")) {
                    Jump_N_Run = true;
                }
                oldSpielzeit = Objects.requireNonNull(mainScoreboard.getObjective("Spielzeit'")).getScore(p.getName()).getScore();
                oldCoins = Objects.requireNonNull(mainScoreboard.getObjective("Coins")).getScore(p.getName()).getScore();
            }
        }.runTaskTimer(this, 20L, 20L);
    }

    public void setScoreboard(Player p, Objective objective, String groupName, String Spielzeit)  {
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        //Scoreboard Anzeige
        Score s0 = objective.getScore(format("&3"));
        s0.setScore(12);
        if (Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("ServerIP").getScore() == 1) {
            Score s1 = objective.getScore(format("&6&lServerIP:"));
            s1.setScore(11);
            Score s2 = objective.getScore(format("&bCaribien.de"));
            s2.setScore(10);
            Score s3 = objective.getScore(format("&0 "));
            s3.setScore(9);
        }
        if (Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("Rank").getScore() == 1) {
            Score s4 = objective.getScore(format("&6&lRank:"));
            s4.setScore(8);
            Score s5 = objective.getScore(format("&e" + groupName));
            s5.setScore(7);
            Score s6 = objective.getScore(format("&1 "));
            s6.setScore(6);
        }
        if (Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("Spielzeit'").getScore() == 1) {
            Score s7 = objective.getScore(format("&6&lSpielzeit:"));
            s7.setScore(5);
            Score s8 = objective.getScore(Spielzeit);
            s8.setScore(4);
            Score s9 = objective.getScore(format("&2 "));
            s9.setScore(3);
        }
        if (Objects.requireNonNull(mainScoreboard.getObjective("Scoreboard")).getScore("Coins").getScore() == 1) {
            Score s10 = objective.getScore(format("&6&lCoins:"));
            s10.setScore(2);
            Score s11 = objective.getScore(format("&e" + Objects.requireNonNull(mainScoreboard.getObjective("Coins")).getScore(p.getName()).getScore()));
            s11.setScore(1);
        }
    }

    public void updateScoreboard(Objective objective, String Spielzeit, String oldSpielzeit, int Coins, int oldCoins) {
        Score SpielzeitScore = objective.getScore(Spielzeit);
        SpielzeitScore.setScore(4);
        SpielzeitScore = objective.getScore(oldSpielzeit);
        if (SpielzeitScore.isScoreSet() && SpielzeitScore.getScore() == 4) {
            Objects.requireNonNull(objective.getScoreboard()).resetScores(oldSpielzeit);
        }
        if (Coins != oldCoins) {
            Score CoinsScore = objective.getScore(format("&e" + Coins));
            CoinsScore.setScore(1);
            CoinsScore = objective.getScore(format("&e" + oldCoins));
            if (CoinsScore.isScoreSet() && CoinsScore.getScore() == 1) {
                Objects.requireNonNull(objective.getScoreboard()).resetScores(format("&e" + oldCoins));
            }
        }
    }

    public String toSpielzeit(int Spielzeit_in_seconds) {
        String Spielzeit;
        if (Spielzeit_in_seconds < 3600) {
            int minutes = Spielzeit_in_seconds / 60;
            Spielzeit = format("&e" + minutes + "min " + (Spielzeit_in_seconds % 60) + "s");
        } else if (Spielzeit_in_seconds < 86400) {
            int minutes = Spielzeit_in_seconds % 3600;
            minutes /= 60;
            int hours = Spielzeit_in_seconds / 3600;
            Spielzeit = format("&e" + hours + "h " + minutes + "min " + (Spielzeit_in_seconds % 60) + "s");
        } else if (Spielzeit_in_seconds < 31536000) {
            int minutes = Spielzeit_in_seconds % 3600;
            minutes /= 60;
            int hours = Spielzeit_in_seconds % 86400;
            hours /= 3600;
            int days = Spielzeit_in_seconds / 86400;
            Spielzeit = format("&e" + days + "d " + hours + "h " + minutes + "min " + (Spielzeit_in_seconds % 60) + "s");
        } else {
            int minutes = Spielzeit_in_seconds % 3600;
            minutes /= 60;
            int hours = Spielzeit_in_seconds % 86400;
            hours /= 3600;
            int days = Spielzeit_in_seconds % 31536000;
            days /= 86400;
            int years = Spielzeit_in_seconds / 31536000;
            Spielzeit = format("&e" + years + "y " + days + "d " + hours + "h " + minutes + "min " + (Spielzeit_in_seconds % 60) + "s");
        }
        return Spielzeit;
    }

    //füllt den hunger und die Leben auf
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
        Player p = (Player) sender;
        //join state auffüllung
        if (cmd.getName().equalsIgnoreCase("Info")) {
            p.setHealth(20);
            p.setFoodLevel(20);
            return true;
        }

        return false;
    }


    public String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
