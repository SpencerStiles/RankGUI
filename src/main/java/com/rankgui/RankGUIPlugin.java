package com.rankgui;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import javax.annotation.Nonnull;

/**
 * RankGUI - A permission and rank management plugin for Hytale servers.
 * 
 * Features:
 * - Create and manage ranks with custom permissions
 * - Assign ranks to players
 * - GUI-based rank management for server admins
 * 
 * @author Spencer
 * @version 1.0.0
 */
public class RankGUIPlugin extends JavaPlugin {

    private static RankGUIPlugin instance;

    public RankGUIPlugin(@Nonnull JavaPluginInit init) {
        super(init);
        instance = this;
        System.err.println("[RankGUI] Plugin loaded!");
    }

    public static RankGUIPlugin getInstance() {
        return instance;
    }
}
