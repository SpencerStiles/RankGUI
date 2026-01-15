package com.rankgui.commands;

import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.rankgui.RankGUIPlugin;

import java.util.concurrent.CompletableFuture;

/**
 * /rank delete <name> - Deletes a rank
 */
public class RankDeleteCommand extends AbstractAsyncCommand {

    private final RequiredArg<String> nameArg;

    public RankDeleteCommand() {
        super("delete", "Delete an existing rank");

        nameArg = withRequiredArg("name", "The name of the rank to delete", ArgTypes.STRING);
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

            rankManager.removeRank(name);
            rankManager.save();

            context.sender().sendMessage(Message.raw("Successfully deleted rank: " + name).color("green"));
        });
    }
}
