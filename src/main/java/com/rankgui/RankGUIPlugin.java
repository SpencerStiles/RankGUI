package com.rankgui;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.rankgui.commands.RankCommand;
import com.rankgui.storage.RankManager;
import javax.annotation.Nonnull;

/**
 * RankGUI - A permission and rank management plugin for Hytale servers.
 * 
 * Features:
 * - Create and manage ranks with custom permissions
 * - Assign ranks to players
 * - Pre-built rank templates (Owner, Admin, Mod, VIP, Default)
 * 
 * Commands:
 * - /rank list - List all ranks
 * - /rank create <name> [prefix] - Create a new rank
 * - /rank delete <name> - Delete a rank
 * - /rank set <player> <rank> - Assign a rank to a player
 * - /rank info <name> - Show rank details
 * 
 * @author Spencer
 * @version 1.0.0
 */
public class RankGUIPlugin extends JavaPlugin {

    private static RankGUIPlugin instance;
    private RankManager rankManager;

    public RankGUIPlugin(@Nonnull JavaPluginInit init) {
        super(init);
        instance = this;
        System.err.println("[RankGUI] Constructor called");
    }

    @Override
    protected void setup() {
        System.err.println("[RankGUI] setup - Initializing...");

        // Initialize rank manager with data directory
        this.rankManager = new RankManager(getDataDirectory());

        // Register commands
        getCommandRegistry().registerCommand(new RankCommand());

        System.err.println("[RankGUI] Plugin loaded successfully!");
        System.err.println("[RankGUI] Loaded " + rankManager.getRanks().size() + " ranks");
    }

    public static RankGUIPlugin getInstance() {
        return instance;
    }

    public RankManager getRankManager() {
        return rankManager;
    }
}
