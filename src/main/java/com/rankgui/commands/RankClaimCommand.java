package com.rankgui.commands;

import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand;
import com.rankgui.RankGUIPlugin;

import java.util.concurrent.CompletableFuture;

/**
 * /rank claim - Allows first user to claim Owner rank if no admins exist
 */
public class RankClaimCommand extends AbstractAsyncCommand {

    public RankClaimCommand() {
        super("claim", "Claim Owner rank (only works if no admins exist)");
    }

    @Override
    protected boolean canGeneratePermission() {
        return false;
    }

    @Override
    protected CompletableFuture<Void> executeAsync(CommandContext context) {
        return CompletableFuture.runAsync(() -> {
            var rankManager = RankGUIPlugin.getInstance().getRankManager();

            // Check if any admin exists
            if (rankManager.hasAnyAdmin()) {
                context.sender()
                        .sendMessage(Message.raw("An admin already exists. You cannot claim ownership.").color("red"));
                return;
            }

            // Get player name from context
            if (!context.isPlayer()) {
                context.sender().sendMessage(Message.raw("Only players can claim ownership.").color("red"));
                return;
            }

            // Get player name from sender
            String playerName = context.sender().getDisplayName();

            // Promote to Owner
            rankManager.promoteToOwner(playerName);

            context.sender().sendMessage(
                    Message.raw("Congratulations! You are now the server Owner!").color("green").bold(true));
            context.sender()
                    .sendMessage(Message.raw("You have full access to all rank management commands.").color("gray"));

            System.err.println("[RankGUI] " + playerName + " claimed Owner rank (first admin)");
        });
    }
}
