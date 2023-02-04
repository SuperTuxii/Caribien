package server_plugin.caribien;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.util.Objects;

public class Jump_N_Run implements Listener {

    Scoreboard mainScoreboard;

    Caribien main;
    public Jump_N_Run(Caribien caribien) {
        main = caribien;
    }

    public int[][] Checkpoints = {{847, 136, -149, 140, 30}, {829, 137, -147, 0, 10}, {851, 140, -141, 210, 10}, {848, 142, -151, 140, 20}, {839, 159, -138, 280, 15}, {841, 164, -145, 90, 10}, {839, 169, -138, 240, 15}};

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();

        if (event.isCancelled() || event.getFrom().getBlock().getLocation() == event.getFrom().getBlock().getLocation()) {
            return;
        }

        Block block = p.getLocation().getBlock().getRelative(BlockFace.DOWN);

        if(block.getType().equals(Material.LAPIS_BLOCK)) {
            //Start
            if (!p.getScoreboardTags().contains("Jump'n Run")) {
                p.addScoreboardTag("Jump'n Run");
                Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).setScore(0);
                p.getInventory().clear();
                ItemStack abbrechen = new ItemStack(Material.BARRIER, 1);
                ItemMeta abbrechenMeta = abbrechen.getItemMeta();
                assert abbrechenMeta != null;
                abbrechenMeta.setDisplayName(format("&cAbbrechen"));
                abbrechen.setItemMeta(abbrechenMeta);
                p.getInventory().setItem(6, abbrechen);
                ItemStack Checkpoint = new ItemStack(Material.MAGENTA_GLAZED_TERRACOTTA, 1);
                ItemMeta CheckpointMeta = Checkpoint.getItemMeta();
                assert CheckpointMeta != null;
                CheckpointMeta.setDisplayName(format("&eBack"));
                Checkpoint.setItemMeta(CheckpointMeta);
                p.getInventory().setItem(2, Checkpoint);
                Jump_N_Run_Timer(p);
            }
        }else if (block.getType().equals(Material.GOLD_BLOCK) && p.getScoreboardTags().contains("Jump'n Run")) {
            //Checkpoint
            if (block.getLocation().equals(new Location(p.getWorld(), Checkpoints[0][0], Checkpoints[0][1], Checkpoints[0][2]))) {
                if (!p.getScoreboardTags().contains("Checkpoint 1") && !p.getScoreboardTags().contains("Checkpoint 2") && !p.getScoreboardTags().contains("Checkpoint 3") && !p.getScoreboardTags().contains("Checkpoint 4") && !p.getScoreboardTags().contains("Checkpoint 5") && !p.getScoreboardTags().contains("Checkpoint 6") && !p.getScoreboardTags().contains("Checkpoint 7")) {
                    p.addScoreboardTag("Checkpoint 1");
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 2f / 7 * 7);
                    p.sendTitle("", format("&e&lCheckpoint"), 5, 20, 5);
                }
            }else if (block.getLocation().equals(new Location(p.getWorld(), Checkpoints[1][0], Checkpoints[1][1], Checkpoints[1][2]))) {
                if (!p.getScoreboardTags().contains("Checkpoint 2") && !p.getScoreboardTags().contains("Checkpoint 3") && !p.getScoreboardTags().contains("Checkpoint 4") && !p.getScoreboardTags().contains("Checkpoint 5") && !p.getScoreboardTags().contains("Checkpoint 6") && !p.getScoreboardTags().contains("Checkpoint 7")) {
                    p.addScoreboardTag("Checkpoint 2");
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 2f / 7 * 6);
                    p.sendTitle("", format("&e&lCheckpoint"), 5, 20, 5);
                }
            }else if (block.getLocation().equals(new Location(p.getWorld(), Checkpoints[2][0], Checkpoints[2][1], Checkpoints[2][2]))) {
                if (!p.getScoreboardTags().contains("Checkpoint 3") && !p.getScoreboardTags().contains("Checkpoint 4") && !p.getScoreboardTags().contains("Checkpoint 5") && !p.getScoreboardTags().contains("Checkpoint 6") && !p.getScoreboardTags().contains("Checkpoint 7")) {
                    p.addScoreboardTag("Checkpoint 3");
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 2f / 7 * 5);
                    p.sendTitle("", format("&e&lCheckpoint"), 5, 20, 5);
                }
            }else if (block.getLocation().equals(new Location(p.getWorld(), Checkpoints[3][0], Checkpoints[3][1], Checkpoints[3][2]))) {
                if (!p.getScoreboardTags().contains("Checkpoint 4") && !p.getScoreboardTags().contains("Checkpoint 5") && !p.getScoreboardTags().contains("Checkpoint 6") && !p.getScoreboardTags().contains("Checkpoint 7")) {
                    p.addScoreboardTag("Checkpoint 4");
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 2f / 7 * 4);
                    p.sendTitle("", format("&e&lCheckpoint"), 5, 20, 5);
                }
            }else if (block.getLocation().equals(new Location(p.getWorld(), Checkpoints[4][0], Checkpoints[4][1], Checkpoints[4][2]))) {
                if (!p.getScoreboardTags().contains("Checkpoint 5") && !p.getScoreboardTags().contains("Checkpoint 6") && !p.getScoreboardTags().contains("Checkpoint 7")) {
                    p.addScoreboardTag("Checkpoint 5");
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 2f / 7 * 3);
                    p.sendTitle("", format("&e&lCheckpoint"), 5, 20, 5);
                }
            }else if (block.getLocation().equals(new Location(p.getWorld(), Checkpoints[5][0], Checkpoints[5][1], Checkpoints[5][2]))) {
                if (!p.getScoreboardTags().contains("Checkpoint 6") && !p.getScoreboardTags().contains("Checkpoint 7")) {
                    p.addScoreboardTag("Checkpoint 6");
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 2f / 7 * 2);
                    p.sendTitle("", format("&e&lCheckpoint"), 5, 20, 5);
                }
            }else if (block.getLocation().equals(new Location(p.getWorld(), Checkpoints[6][0], Checkpoints[6][1], Checkpoints[6][2]))) {
                if (!p.getScoreboardTags().contains("Checkpoint 7")) {
                    p.addScoreboardTag("Checkpoint 7");
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 2f / 7 * 1);
                    p.sendTitle("", format("&e&lCheckpoint"), 5, 20, 5);
                }
            }
        }else if (block.getType().equals(Material.DIAMOND_BLOCK) && p.getScoreboardTags().contains("Jump'n Run")) {
            //Ende
            p.removeScoreboardTag("Jump'n Run");
            p.removeScoreboardTag("Checkpoint 1");
            p.removeScoreboardTag("Checkpoint 2");
            p.removeScoreboardTag("Checkpoint 3");
            p.removeScoreboardTag("Checkpoint 4");
            p.removeScoreboardTag("Checkpoint 5");
            p.removeScoreboardTag("Checkpoint 6");
            p.removeScoreboardTag("Checkpoint 7");
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 0f);
            if (Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() <= Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() || Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() == 0) {
                Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).setScore(Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore());
            }
            String BestZeit = "00:00";
            if (Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() < 20) {
                BestZeit = format("&e" + "00" + ":" + Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore());
            }else if (Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() < 1200) {
                int seconds = Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() / 20;
                if (seconds < 10) {
                    BestZeit = format("&e" + "0" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() % 20) * 3));
                }else {
                    BestZeit = format("&e" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() % 20) * 3));
                }
            } else if (Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() < 72000) {
                int seconds = Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() % 1200;
                seconds /= 20;
                int minutes = Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() / 1200;
                if (seconds < 10 && minutes < 10) {
                    BestZeit = format("&e" + "0" + minutes + ":0" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() % 20) * 3));
                }else if (seconds < 10) {
                    BestZeit = format("&e" + minutes + ":0" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() % 20) * 3));
                }else if (minutes < 10) {
                    BestZeit = format("&e" + "0" + minutes + ":" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() % 20) * 3));
                }else {
                    BestZeit = format("&e" + minutes + ":" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() % 20) * 3));
                }
            } else if (Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() > 72000) {
                int seconds = Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() % 1200;
                seconds /= 20;
                int minutes = Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() % 72000;
                minutes /= 1200;
                int hours = Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() / 72000;
                if (seconds < 10 && minutes < 10 && hours < 10) {
                    BestZeit = format("&e" + "0" + hours + ":0" + minutes + ":0" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() % 20) * 3));
                }else if (seconds >= 10 && minutes < 10 && hours < 10) {
                    BestZeit = format("&e" + "0" + hours + ":0" + minutes + ":" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() % 20) * 3));
                }else if (seconds < 10 && minutes >= 10 && hours < 10) {
                    BestZeit = format("&e" + "0" + hours + ":" + minutes + ":0" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() % 20) * 3));
                }else if (seconds >= 10 && minutes >= 10 && hours < 10) {
                    BestZeit = format("&e" + "0" + hours + ":" + minutes + ":" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() % 20) * 3));
                }else if (seconds < 10 && minutes < 10) {
                    BestZeit = format("&e" + hours + ":0" + minutes + ":0" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() % 20) * 3));
                }else if (seconds >= 10 && minutes < 10) {
                    BestZeit = format("&e" + hours + ":0" + minutes + ":" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() % 20) * 3));
                }else if (seconds < 10) {
                    BestZeit = format("&e" + hours + ":" + minutes + ":0" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() % 20) * 3));
                }else {
                    BestZeit = format("&e" + hours + ":" + minutes + ":" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore() % 20) * 3));
                }
            }
            String Zeit = "00:00";
            if (Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() < 20) {
                Zeit = format("&e" + "00" + ":" + Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore());
            }else if (Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() < 1200) {
                int seconds = Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() / 20;
                if (seconds < 10) {
                    Zeit = format("&e" + "0" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() % 20) * 3));
                }else {
                    Zeit = format("&e" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() % 20) * 3));
                }
            } else if (Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() < 72000) {
                int seconds = Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() % 1200;
                seconds /= 20;
                int minutes = Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() / 1200;
                if (seconds < 10 && minutes < 10) {
                    Zeit = format("&e" + "0" + minutes + ":0" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() % 20) * 3));
                }else if (seconds < 10) {
                    Zeit = format("&e" + minutes + ":0" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() % 20) * 3));
                }else if (minutes < 10) {
                    Zeit = format("&e" + "0" + minutes + ":" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() % 20) * 3));
                }else {
                    Zeit = format("&e" + minutes + ":" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() % 20) * 3));
                }
            } else if (Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() > 72000) {
                int seconds = Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() % 1200;
                seconds /= 20;
                int minutes = Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() % 72000;
                minutes /= 1200;
                int hours = Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() / 72000;
                if (seconds < 10 && minutes < 10 && hours < 10) {
                    Zeit = format("&e" + "0" + hours + ":0" + minutes + ":0" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() % 20) * 3));
                }else if (seconds >= 10 && minutes < 10 && hours < 10) {
                    Zeit = format("&e" + "0" + hours + ":0" + minutes + ":" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() % 20) * 3));
                }else if (seconds < 10 && minutes >= 10 && hours < 10) {
                    Zeit = format("&e" + "0" + hours + ":" + minutes + ":0" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() % 20) * 3));
                }else if (seconds >= 10 && minutes >= 10 && hours < 10) {
                    Zeit = format("&e" + "0" + hours + ":" + minutes + ":" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() % 20) * 3));
                }else if (seconds < 10 && minutes < 10) {
                    Zeit = format("&e" + hours + ":0" + minutes + ":0" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() % 20) * 3));
                }else if (seconds >= 10 && minutes < 10) {
                    Zeit = format("&e" + hours + ":0" + minutes + ":" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() % 20) * 3));
                }else if (seconds < 10) {
                    Zeit = format("&e" + hours + ":" + minutes + ":0" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() % 20) * 3));
                }else {
                    Zeit = format("&e" + hours + ":" + minutes + ":" + seconds + ":" + ((Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() % 20) * 3));
                }
            }
            p.sendMessage(format("&aZeit: " + Zeit + " &6Bestzeit: " + BestZeit));
            p.getInventory().clear();
        }

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (p.getScoreboardTags().contains("Jump'n Run") && event.getItem() != null && event.getItem().getItemMeta() != null) {
                if (event.getItem().getType().equals(Material.BARRIER) && event.getItem().getItemMeta().getDisplayName().equals(format("&cAbbrechen"))) {
                    p.removeScoreboardTag("Jump'n Run");
                    p.removeScoreboardTag("Checkpoint 1");
                    p.removeScoreboardTag("Checkpoint 2");
                    p.removeScoreboardTag("Checkpoint 3");
                    p.removeScoreboardTag("Checkpoint 4");
                    p.removeScoreboardTag("Checkpoint 5");
                    p.removeScoreboardTag("Checkpoint 6");
                    p.removeScoreboardTag("Checkpoint 7");
                    p.getInventory().clear();
                    event.setCancelled(true);
                }else if (event.getItem().getType().equals(Material.MAGENTA_GLAZED_TERRACOTTA) && event.getItem().getItemMeta().getDisplayName().equals(format("&eBack"))) {
                    if (p.getScoreboardTags().contains("Checkpoint 7")) {
                        p.teleport(new Location(p.getWorld(), Checkpoints[6][0] + 0.5, Checkpoints[6][1] + 1, Checkpoints[6][2] + 0.5, Checkpoints[6][3], Checkpoints[6][4]));
                    }else if (p.getScoreboardTags().contains("Checkpoint 6")) {
                        p.teleport(new Location(p.getWorld(), Checkpoints[5][0] + 0.5, Checkpoints[5][1] + 1, Checkpoints[5][2] + 0.5, Checkpoints[5][3], Checkpoints[5][4]));
                    }else if (p.getScoreboardTags().contains("Checkpoint 5")) {
                        p.teleport(new Location(p.getWorld(), Checkpoints[4][0] + 0.5, Checkpoints[4][1] + 1, Checkpoints[4][2] + 0.5, Checkpoints[4][3], Checkpoints[4][4]));
                    }else if (p.getScoreboardTags().contains("Checkpoint 4")) {
                        p.teleport(new Location(p.getWorld(), Checkpoints[3][0] + 0.5, Checkpoints[3][1] + 1, Checkpoints[3][2] + 0.5, Checkpoints[3][3], Checkpoints[3][4]));
                    }else if (p.getScoreboardTags().contains("Checkpoint 3")) {
                        p.teleport(new Location(p.getWorld(), Checkpoints[2][0] + 0.5, Checkpoints[2][1] + 1, Checkpoints[2][2] + 0.5, Checkpoints[2][3], Checkpoints[2][4]));
                    }else if (p.getScoreboardTags().contains("Checkpoint 2")) {
                        p.teleport(new Location(p.getWorld(), Checkpoints[1][0] + 0.5, Checkpoints[1][1] + 1, Checkpoints[1][2] + 0.5, Checkpoints[1][3], Checkpoints[1][4]));
                    }else if (p.getScoreboardTags().contains("Checkpoint 1")) {
                        p.teleport(new Location(p.getWorld(), Checkpoints[0][0] + 0.5, Checkpoints[0][1] + 1, Checkpoints[0][2] + 0.5, Checkpoints[0][3], Checkpoints[0][4]));
                    }else {
                        p.teleport(new Location(p.getWorld(), 841.5, 121, -129.5, 190, -10));
                    }
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player p = event.getPlayer();
        if (p.getScoreboardTags().contains("Jump'n Run")) {
            event.setCancelled(true);
        }
    }

    public void Jump_N_Run_Timer(Player p) {
        if (!Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).isScoreSet()) {
            Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).setScore(0);
        }
        setScoreboard(p);
        new BukkitRunnable() {
            int old_time = Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore();

            @Override
            public void run() {
                if (!p.isOnline()) {
                    p.removeScoreboardTag("Jump'n Run");
                    cancel();
                }
                if (!p.getScoreboardTags().contains("Jump'n Run")) {
                    cancel();
                }else {
                    ScoreboardManager manager = Bukkit.getScoreboardManager();
                    assert manager != null;
                    Scoreboard scoreboard = p.getScoreboard();
                    Objective objective = scoreboard.getObjective(p.getName());
                    assert objective != null;
                    Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).setScore(Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() + 1);
                    updateScoreboard(objective, Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore(), old_time);
                }
                old_time = Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore();
            }
        }.runTaskTimer(main, 1L, 1L);
    }

    public void setScoreboard(Player p) {
        Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).setScore(Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore() + 1);
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        assert manager != null;
        Scoreboard scoreboard = p.getScoreboard();
        Objective objective = scoreboard.getObjective(p.getName());
        if (objective != null) {
            objective.unregister();
        }
        objective = scoreboard.registerNewObjective(p.getName(), "DUMMY", format("&b&lCaribien.de"));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        //Scoreboard Anzeige
        Score s0 = objective.getScore(format("&0 "));
        s0.setScore(6);
        Score s1 = objective.getScore(format("&6&lBestzeit:"));
        s1.setScore(5);
        Score s2 = objective.getScore(formatTime(Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run bZeit")).getScore(p.getName()).getScore()));
        s2.setScore(4);
        Score s3 = objective.getScore(format("&1 "));
        s3.setScore(3);
        Score s7 = objective.getScore(format("&6&lZeit:"));
        s7.setScore(2);
        Score s8 = objective.getScore(formatTime(Objects.requireNonNull(mainScoreboard.getObjective("Jump'n Run Zeit")).getScore(p.getName()).getScore()));
        s8.setScore(1);
    }

    public void updateScoreboard(Objective objective, int time, int old_time) {
        Score SpielzeitScore = objective.getScore(formatTime(time));
        SpielzeitScore.setScore(1);
        SpielzeitScore = objective.getScore(formatTime(old_time));
        if (SpielzeitScore.isScoreSet() && SpielzeitScore.getScore() == 1) {
            Objects.requireNonNull(objective.getScoreboard()).resetScores(formatTime(old_time));
        }
    }

    public String formatTime(int time) {
        String string = format("&4ERROR");
        if (time < 20) {
            string = format("&e" + "00" + ":" + time);
        } else if (time < 1200) {
            int seconds = time / 20;
            if (seconds < 10) {
                string = format("&e" + "0" + seconds + ":" + ((time % 20) * 3));
            } else {
                string = format("&e" + seconds + ":" + ((time % 20) * 3));
            }
        } else if (time < 72000) {
            int seconds = time % 1200;
            seconds /= 20;
            int minutes = time / 1200;
            if (seconds < 10 && minutes < 10) {
                string = format("&e" + "0" + minutes + ":0" + seconds + ":" + ((time % 20) * 3));
            } else if (seconds < 10) {
                string = format("&e" + minutes + ":0" + seconds + ":" + ((time % 20) * 3));
            } else if (minutes < 10) {
                string = format("&e" + "0" + minutes + ":" + seconds + ":" + ((time % 20) * 3));
            } else {
                string = format("&e" + minutes + ":" + seconds + ":" + ((time % 20) * 3));
            }
        } else if (time > 72000) {
            int seconds = time % 1200;
            seconds /= 20;
            int minutes = time % 72000;
            minutes /= 1200;
            int hours = time / 72000;
            if (seconds < 10 && minutes < 10 && hours < 10) {
                string = format("&e" + "0" + hours + ":0" + minutes + ":0" + seconds + ":" + ((time % 20) * 3));
            } else if (seconds >= 10 && minutes < 10 && hours < 10) {
                string = format("&e" + "0" + hours + ":0" + minutes + ":" + seconds + ":" + ((time % 20) * 3));
            } else if (seconds < 10 && minutes >= 10 && hours < 10) {
                string = format("&e" + "0" + hours + ":" + minutes + ":0" + seconds + ":" + ((time % 20) * 3));
            } else if (seconds >= 10 && minutes >= 10 && hours < 10) {
                string = format("&e" + "0" + hours + ":" + minutes + ":" + seconds + ":" + ((time % 20) * 3));
            } else if (seconds < 10 && minutes < 10) {
                string = format("&e" + hours + ":0" + minutes + ":0" + seconds + ":" + ((time % 20) * 3));
            } else if (seconds >= 10 && minutes < 10) {
                string = format("&e" + hours + ":0" + minutes + ":" + seconds + ":" + ((time % 20) * 3));
            } else if (seconds < 10) {
                string = format("&e" + hours + ":" + minutes + ":0" + seconds + ":" + ((time % 20) * 3));
            } else {
                string = format("&e" + hours + ":" + minutes + ":" + seconds + ":" + ((time % 20) * 3));
            }
        }
        return string;
    }

    public String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
