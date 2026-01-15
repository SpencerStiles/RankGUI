package com.rankgui.commands;

import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand;
import com.rankgui.RankGUIPlugin;
import com.rankgui.storage.RankManager;

import java.util.concurrent.CompletableFuture;

/**
 * /rank list - Lists all available ranks
 */
public class RankListCommand extends AbstractAsyncCommand {

    public RankListCommand() {
        super("list", "List all available ranks");
    }

    @Override
    protected CompletableFuture<Void> executeAsync(CommandContext context) {
        return CompletableFuture.runAsync(() -> {
            RankManager rankManager = RankGUIPlugin.getInstance().getRankManager();

            context.sender().sendMessage("§6=== Available Ranks ===");

            if (rankManager.getRanks().isEmpty()) {
                context.sender().sendMessage("§7No ranks configured. Use /rank create <name> to create one.");
            } else {
                for (String rankName : rankManager.getRanks().keySet()) {
                    var rank = rankManager.getRank(rankName);
                    context.sender().sendMessage("§a" + rankName + " §7- " + rank.getPrefix());
                }
            }
        });
    }
}
