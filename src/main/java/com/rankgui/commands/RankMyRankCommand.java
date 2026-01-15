package com.rankgui.commands;

import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand;
import com.rankgui.RankGUIPlugin;
import com.rankgui.storage.Rank;
import com.rankgui.util.PermissionFormatter;

import java.util.concurrent.CompletableFuture;

/**
 * /rank myrank - Shows your current rank
 */
public class RankMyRankCommand extends AbstractAsyncCommand {

    public RankMyRankCommand() {
        super("myrank", "Show your current rank");
    }

    @Override
    protected boolean canGeneratePermission() {
        return false;
    }

    @Override
    protected CompletableFuture<Void> executeAsync(CommandContext context) {
        return CompletableFuture.runAsync(() -> {
            if (!context.isPlayer()) {
                context.sender().sendMessage(Message.raw("Only players can check their rank.").color("red"));
                return;
            }

            var rankManager = RankGUIPlugin.getInstance().getRankManager();

            // Get player name from sender
            String playerName = context.sender().getDisplayName();

            String rankName = rankManager.getPlayerRank(playerName);
            Rank rank = rankManager.getRank(rankName);

            if (rank == null) {
                rank = rankManager.getRank("Default");
                rankName = "Default";
            }

            context.sender().sendMessage(Message.raw("=== Your Rank ===").color("gold"));
            context.sender().sendMessage(Message.raw("Rank: " + rankName).color("green"));
            context.sender().sendMessage(Message.raw("Prefix: " + rank.getPrefix()).color("gray"));
            context.sender().sendMessage(Message.raw("Priority: " + rank.getPriority()).color("gray"));
            context.sender().sendMessage(
                    Message.raw("Permissions: " + PermissionFormatter.formatList(rank.getPermissions())).color("gray"));
        });
    }
}
