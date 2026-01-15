package com.rankgui.commands;

import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.rankgui.RankGUIPlugin;
import com.rankgui.storage.Rank;

import java.util.concurrent.CompletableFuture;

/**
 * /rank info <name> - Shows info about a rank
 */
public class RankInfoCommand extends AbstractAsyncCommand {

    private final RequiredArg<String> nameArg;

    public RankInfoCommand() {
        super("info", "Show information about a rank");

        nameArg = withRequiredArg("name", "The rank name", ArgTypes.STRING);
    }

    @Override
    protected boolean canGeneratePermission() {
        return false;
    }

    @Override
    protected CompletableFuture<Void> executeAsync(CommandContext context) {
        return CompletableFuture.runAsync(() -> {
            String name = nameArg.get(context);
            var rankManager = RankGUIPlugin.getInstance().getRankManager();

            if (!rankManager.hasRank(name)) {
                context.sender().sendMessage(Message.raw("Rank '" + name + "' does not exist!").color("red"));
                return;
            }

            Rank rank = rankManager.getRank(name);
            context.sender().sendMessage(Message.raw("=== Rank: " + name + " ===").color("gold"));
            context.sender().sendMessage(Message.raw("Prefix: " + rank.getPrefix()).color("gray"));
            context.sender()
                    .sendMessage(Message.raw("Permissions: " + String.join(", ", rank.getPermissions())).color("gray"));
        });
    }
}
