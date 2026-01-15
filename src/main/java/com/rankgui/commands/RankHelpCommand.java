package com.rankgui.commands;

import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand;

import java.util.concurrent.CompletableFuture;

/**
 * /rank help - Shows all available rank commands
 */
public class RankHelpCommand extends AbstractAsyncCommand {

    public RankHelpCommand() {
        super("help", "Show all available rank commands");
    }

    @Override
    protected boolean canGeneratePermission() {
        return false;
    }

    @Override
    protected CompletableFuture<Void> executeAsync(CommandContext context) {
        return CompletableFuture.runAsync(() -> {
            context.sender().sendMessage(Message.raw("=== RankGUI Commands ===").color("gold"));
            context.sender().sendMessage(Message.raw("/rank list - List all ranks (sorted by priority)").color("gray"));
            context.sender().sendMessage(Message.raw("/rank info <name> - Show details about a rank").color("gray"));
            context.sender().sendMessage(Message.raw("/rank create <name> [prefix] - Create a new rank").color("gray"));
            context.sender().sendMessage(Message.raw("/rank delete <name> - Delete a rank").color("gray"));
            context.sender()
                    .sendMessage(Message.raw("/rank set <player> <rank> - Assign a rank to a player").color("gray"));
            context.sender().sendMessage(Message.raw("/rank myrank - Show your current rank").color("gray"));
            context.sender()
                    .sendMessage(Message.raw("/rank claim - Claim Owner rank (first admin only)").color("gray"));
            context.sender().sendMessage(Message.raw("/rank help - Show this help message").color("gray"));
        });
    }
}
