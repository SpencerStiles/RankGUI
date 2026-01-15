package com.rankgui.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages ranks and player-rank assignments with JSON persistence.
 */
public class RankManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final Path dataDirectory;
    private Map<String, Rank> ranks;
    private Map<String, String> playerRanks; // playerName -> rankName

    public RankManager(Path dataDirectory) {
        this.dataDirectory = dataDirectory;
        this.ranks = new HashMap<>();
        this.playerRanks = new HashMap<>();

        load();
    }

    public void load() {
        try {
            Files.createDirectories(dataDirectory);

            // Load ranks
            Path ranksFile = dataDirectory.resolve("ranks.json");
            if (Files.exists(ranksFile)) {
                String json = Files.readString(ranksFile);
                Type type = new TypeToken<Map<String, Rank>>() {
                }.getType();
                ranks = GSON.fromJson(json, type);
                if (ranks == null)
                    ranks = new HashMap<>();
            } else {
                // Create default ranks
                createDefaultRanks();
                save();
            }

            // Load player assignments
            Path playersFile = dataDirectory.resolve("player_ranks.json");
            if (Files.exists(playersFile)) {
                String json = Files.readString(playersFile);
                Type type = new TypeToken<Map<String, String>>() {
                }.getType();
                playerRanks = GSON.fromJson(json, type);
                if (playerRanks == null)
                    playerRanks = new HashMap<>();
            }

        } catch (IOException e) {
            System.err.println("[RankGUI] Error loading data: " + e.getMessage());
        }
    }

    public void save() {
        try {
            Files.createDirectories(dataDirectory);

            // Save ranks
            Path ranksFile = dataDirectory.resolve("ranks.json");
            Files.writeString(ranksFile, GSON.toJson(ranks));

            // Save player assignments
            Path playersFile = dataDirectory.resolve("player_ranks.json");
            Files.writeString(playersFile, GSON.toJson(playerRanks));

        } catch (IOException e) {
            System.err.println("[RankGUI] Error saving data: " + e.getMessage());
        }
    }

    private void createDefaultRanks() {
        // Owner rank
        Rank owner = new Rank("Owner", "§4[Owner]");
        owner.setPriority(100);
        owner.addPermission("*");
        ranks.put("Owner", owner);

        // Admin rank
        Rank admin = new Rank("Admin", "§c[Admin]");
        admin.setPriority(90);
        admin.addPermission("rankgui.admin");
        admin.addPermission("server.admin");
        ranks.put("Admin", admin);

        // Moderator rank
        Rank mod = new Rank("Moderator", "§9[Mod]");
        mod.setPriority(50);
        mod.addPermission("server.moderate");
        ranks.put("Moderator", mod);

        // VIP rank
        Rank vip = new Rank("VIP", "§a[VIP]");
        vip.setPriority(20);
        ranks.put("VIP", vip);

        // Default rank
        Rank defaultRank = new Rank("Default", "§7");
        defaultRank.setPriority(0);
        ranks.put("Default", defaultRank);
    }

    public Map<String, Rank> getRanks() {
        return ranks;
    }

    public Rank getRank(String name) {
        return ranks.get(name);
    }

    public boolean hasRank(String name) {
        return ranks.containsKey(name);
    }

    public void addRank(Rank rank) {
        ranks.put(rank.getName(), rank);
    }

    public void removeRank(String name) {
        ranks.remove(name);
        // Remove from players who had this rank
        playerRanks.entrySet().removeIf(entry -> entry.getValue().equals(name));
    }

    public void setPlayerRank(String playerName, String rankName) {
        playerRanks.put(playerName, rankName);
    }

    public String getPlayerRank(String playerName) {
        return playerRanks.getOrDefault(playerName, "Default");
    }

    public Rank getPlayerRankObject(String playerName) {
        String rankName = getPlayerRank(playerName);
        return ranks.getOrDefault(rankName, ranks.get("Default"));
    }
}
