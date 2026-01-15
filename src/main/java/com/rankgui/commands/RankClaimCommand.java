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
    protected CompletableFuture<Void> executeAsync(CommandContext context) {
        return CompletableFuture.runAsync(() -> {
            var rankManager = RankGUIPlugin.getInstance().getRankManager();

            // Check if any admin exists
            if (rankManager.hasAnyAdmin()) {
                context.sender().sendMessage(Message.raw("§cAn admin already exists. You cannot claim ownership."));
                return;
            }

            // Get player name from context
            if (!context.isPlayer()) {
                context.sender().sendMessage(Message.raw("§cOnly players can claim ownership."));
                return;
            }

            // Get player name - for now use a placeholder since we need to find the right
            // API
            String playerName = "Player"; // Will be updated with actual player name API
            try {
                // Try to get the player's name from the sender
                var playerRef = context.senderAsPlayerRef();
                if (playerRef != null) {
                    playerName = playerRef.toString(); // Best effort
                }
            } catch (Exception e) {
                // Fallback
            }

            // Promote to Owner
            rankManager.promoteToOwner(playerName);

            context.sender().sendMessage(Message.raw("§a§lCongratulations! §r§aYou are now the server Owner!"));
            context.sender().sendMessage(Message.raw("§7You have full access to all rank management commands."));

            System.err.println("[RankGUI] " + playerName + " claimed Owner rank (first admin)");
        });
    }
}
