package com.rankgui.commands;

import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.system.OptionalArg;
import com.rankgui.RankGUIPlugin;
import com.rankgui.storage.Rank;

import java.util.concurrent.CompletableFuture;

/**
 * /rank create <name> [prefix] - Creates a new rank
 */
public class RankCreateCommand extends AbstractAsyncCommand {

    private final RequiredArg<String> nameArg;
    private final OptionalArg<String> prefixArg;

    public RankCreateCommand() {
        super("create", "Create a new rank");

        nameArg = withRequiredArg("name", "The name of the rank", ArgTypes.STRING);
        prefixArg = withOptionalArg("prefix", "The display prefix for the rank", ArgTypes.STRING);
    }

    @Override
    protected CompletableFuture<Void> executeAsync(CommandContext context) {
        return CompletableFuture.runAsync(() -> {
            String name = nameArg.get(context);
            String prefix = prefixArg.get(context);

            if (prefix == null) {
                prefix = "[" + name + "]";
            }

            var rankManager = RankGUIPlugin.getInstance().getRankManager();

            if (rankManager.hasRank(name)) {
                context.sender().sendMessage("§cRank '" + name + "' already exists!");
                return;
            }

            Rank rank = new Rank(name, prefix);
            rankManager.addRank(rank);
            rankManager.save();

            context.sender().sendMessage("§aSuccessfully created rank: " + name + " " + prefix);
        });
    }
}
